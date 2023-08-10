
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class BrowseProductPanel extends JPanel {
    private JLabel computerCaregoty;
    private JLabel computerType;
    private JComboBox<String> computerCategoryMenu;
    private JComboBox<String> computerTypeMenu;
    private TableRowSorter<TableModel> sorter;
    private ArrayList<RowFilter<TableModel, Object>> filters = new ArrayList<RowFilter<TableModel, Object>>();
    private DefaultTableModel model;
    public JTable table;

    public BrowseProductPanel(Store store) {
        setLayout(new GridBagLayout());
        computerCaregoty = new JLabel("Computer Category");
        computerType = new JLabel("Computer Type");
        computerCategoryMenu = new JComboBox<>(store.getComputerCategyList());
        computerTypeMenu = new JComboBox<>(store.getComputerTypeList());

        // get table data
        String[] tableColumn = { "Category", "Type", "ID", "Brand", "CPU Family", "Price($)" };
        Object tableData[][] = store.getTableData();

        model = new DefaultTableModel(tableData, tableColumn);
        table = new JTable(model);
        table.setAutoResizeMode(1);
        sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(this.sorter);
        filters.add(RowFilter.regexFilter("(?i)" + "", 0));
        filters.add(RowFilter.regexFilter("(?i)" + "", 1));

        table.setPreferredScrollableViewportSize(new Dimension(600, 200));
        JScrollPane scrollPane = new JScrollPane(table);

        GridBagConstraints computerCaregotyConstraints = new GridBagConstraints();
        computerCaregotyConstraints.gridx = 0;
        computerCaregotyConstraints.gridy = 0;
        computerCaregotyConstraints.ipadx = 1;
        computerCaregotyConstraints.ipady = 1;
        computerCaregotyConstraints.anchor = GridBagConstraints.WEST;
        computerCaregotyConstraints.insets = new Insets(4, 4, 4, 4);
        add(computerCaregoty, computerCaregotyConstraints);

        GridBagConstraints computerTypeConstraints = new GridBagConstraints();
        computerTypeConstraints.gridx = 0;
        computerTypeConstraints.gridy = 1;
        computerTypeConstraints.ipadx = 1;
        computerTypeConstraints.ipady = 1;
        computerTypeConstraints.anchor = GridBagConstraints.WEST;
        computerTypeConstraints.insets = new Insets(4, 4, 4, 4);
        add(computerType, computerTypeConstraints);

        GridBagConstraints computerCategoryMenuConstraints = new GridBagConstraints();
        computerCategoryMenuConstraints.gridx = 1;
        computerCategoryMenuConstraints.gridy = 0;
        computerCategoryMenuConstraints.ipadx = 1;
        computerCategoryMenuConstraints.ipady = 1;
        computerCategoryMenuConstraints.anchor = GridBagConstraints.WEST;
        computerCategoryMenuConstraints.insets = new Insets(4, 4, 4, 4);
        add(computerCategoryMenu, computerCategoryMenuConstraints);

        GridBagConstraints computerTypeMenuConstraints = new GridBagConstraints();
        computerTypeMenuConstraints.gridx = 1;
        computerTypeMenuConstraints.gridy = 1;
        computerTypeMenuConstraints.ipadx = 1;
        computerTypeMenuConstraints.ipady = 1;
        computerTypeMenuConstraints.anchor = GridBagConstraints.WEST;
        computerTypeMenuConstraints.insets = new Insets(4, 4, 4, 4);
        add(computerTypeMenu, computerTypeMenuConstraints);

        GridBagConstraints scrollPaneConstraints = new GridBagConstraints();
        scrollPaneConstraints.gridx = 0;
        scrollPaneConstraints.gridy = 2;
        scrollPaneConstraints.ipadx = 1;
        scrollPaneConstraints.ipady = 1;
        scrollPaneConstraints.gridwidth = 2;
        scrollPaneConstraints.insets = new Insets(4, 4, 4, 4);
        add(scrollPane, scrollPaneConstraints);

        // category filter action
        computerCategoryMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) computerCategoryMenu.getSelectedItem();
                if (selectedOption == "All") {
                    setCategoryFilter("");
                } else {
                    setCategoryFilter(selectedOption);
                }
                applyFilter();
            }
        });

        // type filter action
        computerTypeMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) computerTypeMenu.getSelectedItem();
                if (selectedOption == "All") {
                    setTypeFilter("");
                } else {
                    setTypeFilter(selectedOption);
                }
                applyFilter();
            }
        });

        // table item select action
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int columnIndex = 2;
                        Object selectedItemId = table.getValueAt(selectedRow, columnIndex);
                        store.selectComputerById(selectedItemId);
                    }
                }
            }
        });
    }

    // reload table data
    public void updateTableData(Store store) {
        model.setRowCount(0);
        Object tableData[][] = store.getTableData();
        for (Object[] row : tableData) {
            model.addRow(row);
        }
        model.fireTableDataChanged();
    }

    // set table category filter
    private void setCategoryFilter(String searchText) {
        this.filters.set(0, RowFilter.regexFilter("(?i)" + searchText, 0));
    }

    // set table type filter
    private void setTypeFilter(String searchText) {
        this.filters.set(1, RowFilter.regexFilter("(?i)" + searchText, 1));
    }

    // apply all table filters
    private void applyFilter() {
        RowFilter<TableModel, Object> compositeFilter = RowFilter.andFilter(this.filters);
        sorter.setRowFilter(compositeFilter);
    }
}
