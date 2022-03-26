import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame {
    Game() {
        super("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        GamePanel gp = new GamePanel();
        gp.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP ->  gp.changeDirection(0);
                    case KeyEvent.VK_RIGHT -> gp.changeDirection(1);
                    case KeyEvent.VK_DOWN -> gp.changeDirection(2);
                    case KeyEvent.VK_LEFT -> gp.changeDirection(3);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        gp.setFocusable(true);
        gp.requestFocusInWindow();
        add(gp);
        this.setSize(1200, 830);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
