package GUI;

import javax.swing.JFrame;

/**
 *
 * @author @AbdullahShahid01
 */
public class Runner {

    private static final JFrame FRAME = new JFrame("Rent-a-Car Management System");

    public static JFrame getFrame() {
        return FRAME;
    }

    public Runner() {
        
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Runner();
        Login login = new Login();
        Runner.FRAME.getContentPane().removeAll();
        Runner.FRAME.add(login.getMainPanel());
        Runner.FRAME.pack();
        Runner.FRAME.setLocationRelativeTo(null);
        Runner.FRAME.setVisible(true);
    }
}
