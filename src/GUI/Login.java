package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author @AbdullahShahid01
 */
public class Login {

    private final JPanel MainPanel;
    private final JButton Close_Button, Login_Button;
    private final JLabel PW_Label, UN_Label, info_Label, messageLabel;
    private final JTextField UN_TextField;
    private final JPasswordField Password_Field;

    public Login() {

        MainPanel = new JPanel();

        Close_Button = new JButton("Close");
        Login_Button = new JButton("Login");

        PW_Label = new JLabel("Password");
        UN_Label = new JLabel("Username");
        info_Label = new JLabel("Please Enter your Login Details");
        messageLabel = new JLabel(" ");

        UN_TextField = new JTextField();
        Password_Field = new JPasswordField();

        MainPanel.setLayout(new BorderLayout(0, 20));
        MainPanel.setPreferredSize(new Dimension(420, 260));
        MainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        info_Label.setFont(new Font("Consolas", Font.BOLD, 20));
        info_Label.setHorizontalAlignment(JLabel.CENTER);

        UN_Label.setFont(new Font("Consolas", Font.PLAIN, 16));
        PW_Label.setFont(new Font("Consolas", Font.PLAIN, 16));

        UN_TextField.setPreferredSize(new Dimension(200, 28));
        Password_Field.setPreferredSize(new Dimension(200, 28));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        formPanel.add(UN_Label, gbc);

        gbc.gridx = 1;
        formPanel.add(UN_TextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(PW_Label, gbc);

        gbc.gridx = 1;
        formPanel.add(Password_Field, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(Login_Button);
        buttonPanel.add(Close_Button);

        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        messageLabel.setForeground(Color.RED);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(messageLabel, BorderLayout.SOUTH);

        MainPanel.add(info_Label, BorderLayout.NORTH);
        MainPanel.add(formPanel, BorderLayout.CENTER);
        MainPanel.add(bottomPanel, BorderLayout.SOUTH);

        Login_Button.addActionListener(new LoginActionListener());
        Close_Button.addActionListener(new LoginActionListener());
    }

    /**
     * @return the MainPanel
     */
    public JPanel getMainPanel() {
        return MainPanel;
    }

    private class LoginActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Close": {
                    int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to terminate the program.\n"
                            + " Are you sure you want to continue ?", "Close Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
                    if (showConfirmDialog == 0) {
                        System.exit(0);
                    }
                    break;
                }
                case "Login": {
                    if (UN_TextField.getText().trim().equalsIgnoreCase("admin")
                            && String.valueOf(Password_Field.getPassword()).equals("123")) {
                        UN_TextField.setText("");
                        Password_Field.setText("");
                        messageLabel.setText(" ");
                        Runner.getFrame().dispose();
                        new Parent_JFrame();
                        MainMenu menu = new MainMenu();
                        JFrame mainFrame = Parent_JFrame.getMainFrame();
//                        JPanel mainPanel = menu.getMainPanel();
                        mainFrame.add(menu.getMainPanel());
                        mainFrame.setVisible(true);
                    } else {
                        messageLabel.setText("Invalid username or password. Try again.");
                    }
                    break;
                }
            }
        }
    }

}
