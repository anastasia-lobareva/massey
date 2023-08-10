import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProductDetailPanel extends JPanel {
    private JLabel modelIdLabel;
    private JLabel categoryLabel;
    private JLabel typeLabel;
    private JLabel brandLabel;
    private JLabel cpuLabel;
    private JLabel memorySizeLabel;
    private JLabel ssdLabel;
    private JLabel screenSizeLabel;
    private JLabel priceLable;

    private JTextField modelIdField;
    // TODO dropdowns
    private JTextField categoryField;
    private JTextField typField;
    private JTextField branField;
    private JTextField cpuField;
    private JTextField memorySizeField;
    private JTextField ssdField;
    private JTextField screenSizeField;
    private JTextField priceField;

    public JButton addButton;
    public JButton deleteButton;
    public JButton updateButton;
    private JButton clearButton;

    public ProductDetailPanel(Store store) {
        setLayout(new GridLayout(11, 2));
        modelIdLabel = new JLabel("Model ID:");
        categoryLabel = new JLabel("Category:");
        typeLabel = new JLabel("Type:");
        brandLabel = new JLabel("Brand:");
        cpuLabel = new JLabel("CPU Family:");
        memorySizeLabel = new JLabel("Memory Size:");
        ssdLabel = new JLabel("SSD Capacity:");
        screenSizeLabel = new JLabel("Screen Size:");
        priceLable = new JLabel("Price:");

        modelIdField = new JTextField();
        // TODO dropdowns
        categoryField = new JTextField();
        typField = new JTextField();
        branField = new JTextField();
        cpuField = new JTextField();
        memorySizeField = new JTextField();
        ssdField = new JTextField();
        screenSizeField = new JTextField();
        priceField = new JTextField();

        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");
        clearButton = new JButton("Clear");

        add(modelIdLabel);
        add(modelIdField);
        add(categoryLabel);
        add(categoryField);
        add(typeLabel);
        add(typField);
        add(brandLabel);
        add(branField);
        add(cpuLabel);
        add(cpuField);
        add(memorySizeLabel);
        add(memorySizeField);
        add(ssdLabel);
        add(ssdField);
        add(screenSizeLabel);
        add(screenSizeField);
        add(priceLable);
        add(priceField);
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(clearButton);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearSelectedDetails();
            }
        });

        update(store);
    }

    // create new computer
    public Computer newComputerData() {
        try {
            String category = this.categoryField.getText();

            switch (category) {
                case "Desktop PC":
                    return new Desktop(category, this.typField.getText(), this.modelIdField.getText(), this.branField.getText(), this.cpuField.getText(), Float.parseFloat(this.priceField.getText()), Integer.parseInt(this.memorySizeField.getText()), Integer.parseInt(this.ssdField.getText()));
                case "Laptop":
                    return new Laptop(category, this.typField.getText(), this.modelIdField.getText(), this.branField.getText(), this.cpuField.getText(), Float.parseFloat(this.priceField.getText()), Integer.parseInt(this.memorySizeField.getText()), Integer.parseInt(this.ssdField.getText()), Float.parseFloat(this.screenSizeField.getText()));
                case "Tablet":
                    return new Tablet(category, this.typField.getText(), this.modelIdField.getText(), this.branField.getText(), this.cpuField.getText(), Float.parseFloat(this.priceField.getText()), Float.parseFloat(this.screenSizeField.getText()));
                default:
                   throw new RuntimeException("Incorrect category");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    // update existing computer
    public Computer updateComputerData(Computer computer) {
        try {
            Integer mem = 0;
            if (!this.memorySizeField.getText().equals("")) {
                mem = Integer.parseInt(this.memorySizeField.getText());
            }

            Integer ssd = 0;
            if (!this.ssdField.getText().equals("")) {
                ssd = Integer.parseInt(this.ssdField.getText());
            }

            Float screen = (float)0;
            if (!this.screenSizeField.getText().equals("")) {
                screen = Float.parseFloat(this.screenSizeField.getText());
            }
            
            computer.Update(
                this.categoryField.getText(),
                this.typField.getText(),
                this.branField.getText(), 
                this.cpuField.getText(), 
                Float.parseFloat(this.priceField.getText()), 
                mem, 
                ssd, 
                screen);
            return computer;
        } catch (Exception e) {
            return null;
        }
    }

    // set details panel computer info
    public void setComputerDetails(Computer computer) {
        this.modelIdField.setText(computer.id);
        this.modelIdField.setEnabled(false);
        this.categoryField.setText(computer.getCategory());
        this.typField.setText(computer.getType());
        this.branField.setText(computer.getBrand());
        this.cpuField.setText(computer.getCpu());

        var memSize = computer.getMemorySize();
        if (memSize > 0) {
            this.memorySizeField.setText(Integer.toString(memSize));
        }

        var ssdCap = computer.getSsdCapacity();
        if (ssdCap > 0) {
            this.ssdField.setText(Integer.toString(ssdCap));
        }

        var screenSize = computer.getScreenSize();
        if (screenSize > 0) {
            this.screenSizeField.setText(Float.toString(screenSize));
        }

        this.priceField.setText(Float.toString(computer.getPrice()));
    }

    // clear details panel computer info
    public void clearSelectedDetails() {
        this.modelIdField.setEnabled(true);
        this.modelIdField.setText("");
        this.categoryField.setText("");
        this.typField.setText("");
        this.branField.setText("");
        this.cpuField.setText("");
        this.memorySizeField.setText("");
        this.ssdField.setText("");
        this.screenSizeField.setText("");
        this.priceField.setText("");
    }

    // refresh panel interface
    public void update(Store store) {
        boolean isManager = false;

        if (store.currentUser != null) {
            isManager = store.currentUser.getRole().toLowerCase().equals("manager");
        }

        addButton.setEnabled(isManager);
        clearButton.setEnabled(isManager);
        updateButton.setEnabled(isManager);
        deleteButton.setEnabled(isManager);

        if (!modelIdField.getText().equals("")) {
            this.modelIdField.setEnabled(false);
        }
        
        this.categoryField.setEnabled(isManager);
        this.typField.setEnabled(isManager);
        this.branField.setEnabled(isManager);
        this.cpuField.setEnabled(isManager);
        this.memorySizeField.setEnabled(isManager);
        this.ssdField.setEnabled(isManager);
        this.screenSizeField.setEnabled(isManager);
        this.priceField.setEnabled(isManager);
    }
}