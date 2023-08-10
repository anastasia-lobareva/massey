import javax.swing.*;
import java.awt.*;

public class LoginLogoutPanel extends JPanel {
    private JLabel pictureLabel;
    public JButton loginButton;
    public JButton logoutButton;

    public LoginLogoutPanel(Store store) {
        setLayout(new GridBagLayout());
        pictureLabel = new JLabel(new ImageIcon("PictureLabel.jpeg"));
        loginButton = new JButton("Click to login");
        logoutButton = new JButton("Click to logout");

        // the text label constraints are position and insets
        GridBagConstraints pictureLabelConstraints = new GridBagConstraints();
        pictureLabelConstraints.gridx = 0;
        pictureLabelConstraints.gridy = 0;
        pictureLabelConstraints.ipadx = 1;
        pictureLabelConstraints.ipady = 1;
        pictureLabelConstraints.anchor = GridBagConstraints.CENTER;
        pictureLabelConstraints.insets = new Insets(4, 4, 4, 4);
        add(pictureLabel, pictureLabelConstraints);

        GridBagConstraints loginButtonConstraints = new GridBagConstraints();
        loginButtonConstraints.gridx = 1;
        loginButtonConstraints.gridy = 0;
        loginButtonConstraints.ipadx = 1;
        loginButtonConstraints.ipady = 1;
        loginButtonConstraints.anchor = GridBagConstraints.CENTER;
        loginButtonConstraints.insets = new Insets(4, 4, 4, 4);
        add(loginButton, loginButtonConstraints);

        GridBagConstraints logoutButtonConstraints = new GridBagConstraints();
        logoutButtonConstraints.gridx = 1;
        logoutButtonConstraints.gridy = 0;
        logoutButtonConstraints.ipadx = 1;
        logoutButtonConstraints.ipady = 1;
        logoutButtonConstraints.anchor = GridBagConstraints.CENTER;
        logoutButtonConstraints.insets = new Insets(4, 4, 4, 4);
        add(logoutButton, logoutButtonConstraints);

        update(store);
    }

    // refresh login panel
    public void update(Store store) {
        loginButton.setVisible(store.currentUser == null);
        logoutButton.setVisible(store.currentUser != null);
    }
}
