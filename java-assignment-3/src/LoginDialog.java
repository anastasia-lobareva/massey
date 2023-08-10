import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog extends JDialog {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField textFieldUsername;
    private JPasswordField textFieldPassword;
    private JButton loginButton;
    private JButton cancelButton;
    private JLabel incorrectLoginPassword;

    public LoginDialog(Store store) {
        setTitle("Sales Person Login");
        setLayout(new GridBagLayout());
        setSize(400, 200);

        usernameLabel = new JLabel("Username:");
        textFieldUsername = new JTextField(25);
        passwordLabel = new JLabel("Password:");
        textFieldPassword = new JPasswordField(25);
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        incorrectLoginPassword = new JLabel("Incorrect login or password");
        incorrectLoginPassword.setForeground(Color.red);
        incorrectLoginPassword.setVisible(false);

        GridBagConstraints usernameLabelConstraints = new GridBagConstraints();
        usernameLabelConstraints.gridx = 0;
        usernameLabelConstraints.gridy = 0;
        usernameLabelConstraints.ipadx = 1;
        usernameLabelConstraints.ipady = 1;
        usernameLabelConstraints.anchor = GridBagConstraints.WEST;
        usernameLabelConstraints.insets = new Insets(4, 4, 4, 4);
        add(usernameLabel, usernameLabelConstraints);

        GridBagConstraints textFieldUsernameConstraints = new GridBagConstraints();
        textFieldUsernameConstraints.gridx = 0;
        textFieldUsernameConstraints.gridy = 1;
        textFieldUsernameConstraints.ipadx = 1;
        textFieldUsernameConstraints.ipady = 1;
        textFieldUsernameConstraints.anchor = GridBagConstraints.EAST;
        textFieldUsernameConstraints.insets = new Insets(4, 4, 4, 4);
        textFieldUsernameConstraints.fill = GridBagConstraints.HORIZONTAL;
        textFieldUsernameConstraints.weightx = 300;
        add(textFieldUsername, textFieldUsernameConstraints);

        GridBagConstraints passwordLabelConstraints = new GridBagConstraints();
        passwordLabelConstraints.gridx = 1;
        passwordLabelConstraints.gridy = 0;
        passwordLabelConstraints.ipadx = 1;
        passwordLabelConstraints.ipady = 1;
        passwordLabelConstraints.anchor = GridBagConstraints.WEST;
        passwordLabelConstraints.insets = new Insets(4, 4, 4, 4);
        add(passwordLabel, passwordLabelConstraints);

        GridBagConstraints textFieldPasswordConstraints = new GridBagConstraints();
        textFieldPasswordConstraints.gridx = 1;
        textFieldPasswordConstraints.gridy = 1;
        textFieldPasswordConstraints.ipadx = 1;
        textFieldPasswordConstraints.ipady = 1;
        textFieldPasswordConstraints.anchor = GridBagConstraints.EAST;
        textFieldPasswordConstraints.insets = new Insets(4, 4, 4, 4);
        textFieldPasswordConstraints.fill = GridBagConstraints.HORIZONTAL;
        textFieldPasswordConstraints.weightx = 300;
        add(textFieldPassword, textFieldPasswordConstraints);

        GridBagConstraints loginButtonConstraints = new GridBagConstraints();
        loginButtonConstraints.gridx = 0;
        loginButtonConstraints.gridy = 2;
        loginButtonConstraints.ipadx = 1;
        loginButtonConstraints.ipady = 1;
        loginButtonConstraints.anchor = GridBagConstraints.CENTER;
        loginButtonConstraints.insets = new Insets(4, 4, 4, 4);
        add(loginButton, loginButtonConstraints);

        GridBagConstraints cancelButtonConstraints = new GridBagConstraints();
        cancelButtonConstraints.gridx = 1;
        cancelButtonConstraints.gridy = 2;
        cancelButtonConstraints.ipadx = 1;
        cancelButtonConstraints.ipady = 1;
        cancelButtonConstraints.anchor = GridBagConstraints.CENTER;
        cancelButtonConstraints.insets = new Insets(4, 4, 4, 4);
        add(cancelButton, cancelButtonConstraints);

        GridBagConstraints incorrectLoginPasswordConstraints = new GridBagConstraints();
        incorrectLoginPasswordConstraints.gridx = 0;
        incorrectLoginPasswordConstraints.gridy = 3;
        incorrectLoginPasswordConstraints.ipadx = 1;
        incorrectLoginPasswordConstraints.ipady = 1;
        incorrectLoginPasswordConstraints.anchor = GridBagConstraints.CENTER;
        incorrectLoginPasswordConstraints.insets = new Insets(4, 4, 4, 4);
        add(incorrectLoginPassword, incorrectLoginPasswordConstraints);

        // login action
        ActionListener actionListenerLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameInput = textFieldUsername.getText();
                char[] passwordInputChar = textFieldPassword.getPassword();
                String passwordInput = new String(passwordInputChar);

                Boolean verification = store.authUser(usernameInput, passwordInput);

                if (verification == true) {
                    dispose();
                } else {
                    incorrectLoginPassword.setVisible(true);
                    textFieldUsername.setText("");
                    textFieldPassword.setText("");
                }
            }
        };

        // cancel action
        ActionListener actionListenerCancel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };
        loginButton.addActionListener(actionListenerLogin);
        cancelButton.addActionListener(actionListenerCancel);
    }
}
