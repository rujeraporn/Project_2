import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    private PanelGame panelGame = new PanelGame();

    public Menu() {
        setTitle("PLANE GAME");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JButton startButton = new JButton();
        ImageIcon startIcon = new ImageIcon("C:\\Users\\hp pc\\OneDrive\\Desktop\\Plane Game\\src\\image\\START.png");
        Image img = startIcon.getImage();
        Image resizedImg = img.getScaledInstance(800, 400, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(resizedImg);
        startButton.setIcon(startIcon);
        startButton.setPreferredSize(new Dimension(startIcon.getIconWidth(), startIcon.getIconHeight()));
        startButton.setBorderPainted(false);
        startButton.addActionListener(new StartButtonListener());
        panel.add(startButton);

        JButton exitButton = new JButton();
        ImageIcon exitIcon = new ImageIcon("C:\\Users\\hp pc\\OneDrive\\Desktop\\Plane Game\\src\\image\\EXIT.png");
        Image imgExit = exitIcon.getImage();
        Image resizedImgExit = imgExit.getScaledInstance(800, 400, Image.SCALE_SMOOTH);
        exitIcon = new ImageIcon(resizedImgExit);
        exitButton.setIcon(exitIcon);
        exitButton.setPreferredSize(new Dimension(exitIcon.getIconWidth(), exitIcon.getIconHeight()));
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(new ExitButtonListener());
        panel.add(exitButton);
        add(panel);

    }

    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            getContentPane().removeAll();
            getContentPane().add(panelGame);
            panelGame.requestFocusInWindow();
            revalidate();
            repaint();

        }
    }

    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
