import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;
import java.util.Timer;

import static javax.swing.JOptionPane.showMessageDialog;


public class GamePanel extends JPanel {
    public static final int WIDTH = 120;
    public static final int HEIGHT = 80;
    private Food food;
    private Snake snake;
    private boolean playing;
    private Timer timer;
    private static final int DELAY = 400;
    private static final int SCORE_TO_WIN = 30;

    GamePanel() {
        this.playing = true;
        this.snake = new Snake();
        setLayout(new FlowLayout());
        setBackground(Color.black);
        this.food = new Food();
        newFood();
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (snake.getLength() == SCORE_TO_WIN) {
                    playing = false;
                    gameWin();

                    return;
                }
                if (collision()) {
                    playing = false;
                    timer.cancel();
                    gameOver();
                }
                if (foundFood()) {
                    System.out.println(snake.getXs());
                    System.out.println(snake.getYs());
                    System.out.println("=================");
                    growUp();
                    newFood();
                }
                if (playing) {
                    move();
                    setBackground(Color.black);
                    updateUI();
                }
            }
        }, DELAY, DELAY);
    }

    public void changeDirection(int dir) {
        if (Math.abs(snake.getDirection() - dir) != 2)
            snake.setDirection(dir);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(food.getX() * 120, food.getY() * 80, GamePanel.WIDTH, GamePanel.HEIGHT);
        g.setColor(Color.WHITE);
        for (int i = 1; i < 10; i++) {
            g.drawLine(i * 120, 0, i * 120, 800);
            g.drawLine(0, i * 80, 1200, i * 80);
        }
        g.setColor(Color.CYAN);
        for (int i = 0; i < snake.getLength(); i++) {
            g.fillRect(snake.getXs().get(i) * WIDTH, snake.getYs().get(i) * HEIGHT, WIDTH, HEIGHT);
        }
        g.setColor(Color.GREEN);
        g.fillRect(snake.getXs().get(0) * WIDTH, snake.getYs().get(0) * HEIGHT, WIDTH, HEIGHT);
    }

    private boolean collision() {
        if (snake.getXs().get(0) < 0 ||
                snake.getXs().get(0) > 9 ||
                snake.getYs().get(0) < 0 ||
                snake.getYs().get(0) > 9)
            return true;
        for (int i = 1; i < snake.getLength(); i++) {
            if (snake.getXs().get(0) == snake.getXs().get(i) && snake.getYs().get(0) == snake.getYs().get(i)) {
                return true;
            }
        }
        return false;
    }

    private void gameWin() {

        showMessageDialog(null, "Ты выиграл");
        timer.cancel();
    }

    private void gameOver() {
        showMessageDialog(null, "Ты проиграл");
        timer.cancel();
    }

    private void move() {
        int x, y;
        switch (snake.getDirection()) {
            case 0 -> {
                x = snake.getXs().get(0);
                y = snake.getYs().get(0) - 1;
            }
            case 1 -> {
                x = snake.getXs().get(0) + 1;
                y = snake.getYs().get(0);
            }
            case 2 -> {
                x = snake.getXs().get(0);
                y = snake.getYs().get(0) + 1;
            }
            case 3 -> {
                x = snake.getXs().get(0) - 1;
                y = snake.getYs().get(0);
            }
            default -> throw new IllegalStateException("Unexpected value: " + snake.getDirection());
        }

        for (int i = snake.getLength() - 1; i > 0; i--) {
            snake.getXs().set(i, snake.getXs().get(i-1));
            snake.getYs().set(i, snake.getYs().get(i-1));
        }

        snake.getXs().set(0, x);
        snake.getYs().set(0, y);
    }

    public boolean foundFood() {
        return snake.getXs().get(0) == food.getX() && snake.getYs().get(0) == food.getY();
    }

    public void growUp() {
        snake.increaseLength();
        snake.getXs().add(snake.getXs().get(snake.getLength()-2));
        snake.getYs().add(snake.getYs().get(snake.getLength()-2));
    }

    public void newFood() {
        int x, y;
        do {
            x = (int) Math.floor(Math.random() * 10);
            y = (int) Math.floor(Math.random() * 10);
        } while (isTakenPlace(x, y));
        food.setCoords(x, y);
    }

    public boolean isTakenPlace(int x, int y) {
        for (int i = 0; i < snake.getLength(); i++) {
            if (snake.getXs().get(i) == x && snake.getYs().get(i) == y) {
                return true;
            }
        }
        return false;
    }
}


