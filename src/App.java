import javax.swing.*;
import java.awt.*;

public class App extends JPanel implements Runnable {
    private String text;
    private int x;

    public App(String text) {
        this.text = text;
        this.x = -getFontMetrics(getFont()).stringWidth(text);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(text, x, 50);
    }

    @Override
    public void run() {
        while (true) {
            x += 5;
            if (x > getWidth()) {
                x = -getFontMetrics(getFont()).stringWidth(text);
            }
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Moving Banner");
        String inputText = JOptionPane.showInputDialog("Enter text for banner:");
        App banner = new App(inputText);

        frame.add(banner);
        frame.setSize(800, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
