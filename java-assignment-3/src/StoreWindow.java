
import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class StoreWindow extends JFrame {

    private BrowseProductPanel browseProductPanel;
    private LoginLogoutPanel loginLogoutPanel;
    private ProductDetailPanel productDetailPanel;
    private JTabbedPane productTabs;

    public StoreWindow(Store store) {

        JFrame frame = new JFrame("Computer Products Management System");

        frame.setLayout(new GridLayout(2, 1));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        browseProductPanel = new BrowseProductPanel(store);
        productDetailPanel = new ProductDetailPanel(store);
        productTabs = new JTabbedPane();
        productTabs.add("Browse Products", browseProductPanel);
        productTabs.add("Check/Update Product Details", productDetailPanel);

        loginLogoutPanel = new LoginLogoutPanel(store);

        // login button actions
        loginLogoutPanel.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginDialog loginDialog = new LoginDialog(store);
                loginDialog.setModalityType(ModalityType.APPLICATION_MODAL);
                loginDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                loginDialog.setLocationRelativeTo(frame);
                loginDialog.setVisible(true);
                loginDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loginLogoutPanel.update(store);
                        update(store);
                    }
                });
            }
        });

        // logout button actions
        loginLogoutPanel.logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                store.logoutUser();
                loginLogoutPanel.update(store);
                update(store);
            }
        });

        // tabs refresh actions
        productTabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (store.selectedComputer != null) {
                    productDetailPanel.clearSelectedDetails();
                    productDetailPanel.setComputerDetails(store.selectedComputer);
                }
            }
        });

        // delete button actions
        productDetailPanel.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                store.deleteComputer(store.selectedComputer);
                productDetailPanel.clearSelectedDetails();
                browseProductPanel.updateTableData(store);
                JOptionPane.showMessageDialog(null, "Computer deleted!");
            }
        });

        // add button actions
        productDetailPanel.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Computer newComputer = productDetailPanel.newComputerData();
                    store.addComputer(newComputer);
                    browseProductPanel.updateTableData(store);
                    productDetailPanel.clearSelectedDetails();
                    JOptionPane.showMessageDialog(null, "New computer added!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        // update button actions
        productDetailPanel.updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Computer updatedComputer = productDetailPanel.updateComputerData(store.selectedComputer);
                if (updatedComputer != null) {
                    browseProductPanel.updateTableData(store);
                    productDetailPanel.clearSelectedDetails();
                    JOptionPane.showMessageDialog(null, "Computer updated!");
                }
            }
        });

        frame.add(productTabs);
        frame.add(loginLogoutPanel);

        frame.pack();

        frame.setVisible(true);

        update(store);
    }

    // refresh panel
    public void update(Store store) {
        productTabs.setVisible(store.currentUser != null);
        productDetailPanel.update(store);
    }
}