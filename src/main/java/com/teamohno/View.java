package com.teamohno;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class View extends JFrame {
    private JLabel pracIDLabel;
    private JLabel monitorCholLabel;
    private JLabel selectedLabel;
    private JPanel selectedMonitoredPanel;
    private JTextField pracIDfield;
    private JButton updatePracButton;
    private JLabel freqLabel;
    private JTextField freqField;
    private JButton updateFreqButton;
    private JScrollPane monitorScrollPane;
    private JScrollPane patientListScrollPane;
    private JPanel mainParentPanel;
    private JList patientJList;
    private JButton monitorCholButton;
    private JButton stopMonitorButton;
    private JLabel freqValueLabel;
    private JPanel displayPatient;
    private JLabel patientGender;
    private JLabel patientAddress;
    private JLabel patientBirthDate;
    private JLabel patientName;
    private JLabel monitorBPLabel;
    private JButton monitorBPButton;
    private JScrollPane monitorBPScrollPane;
    private JButton stopMonitorBPButton;
    private JPanel frequencyPanel;
    private JTextField dbpMinField;
    private JTextField sbpMinField;
    private JButton updateDbpMinButton;
    private JButton updateSbpMinButton;
    private JPanel bloodPressureMinPanel;
    private JLabel dbpMinLabel;
    private JLabel sbpMinLabel;
    private JLabel dbpMinVal;
    private JLabel sbpMinVal;
    private JLabel histMonLabel;
    private JPanel historicalPanel;
    private JPanel historicalMonitorPanel;
    private JButton addPanelButton;
    private JScrollPane historicalScrollPane;
    private JPanel histPanel;

    private DefaultListModel listModel;
    private JTable cholMonitorTable, bpMonitorTable;

    // Empty constructor
    public View() {

    }

    public View(Model model) {
        super("FHIR Patient Monitor");

        //Instantiating JList object
        patientJList = new JList(model.getPatientListModel());
        patientJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        patientJList.setLayoutOrientation(JList.VERTICAL);
        patientListScrollPane.setViewportView(patientJList);

        //Creating a JTable and adding it to the scroll pane
        cholMonitorTable = new JTable(model.getMonitorTable(MeasurementType.Type.CHOLESTEROL));
        cholMonitorTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        monitorScrollPane.setViewportView(cholMonitorTable);
        cholMonitorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Creating a JTable and adding it to the scroll pane
        bpMonitorTable = new JTable(model.getMonitorTable(MeasurementType.Type.BLOODPRESSURE));
        bpMonitorTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        monitorBPScrollPane.setViewportView(bpMonitorTable);
        bpMonitorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // instantiate the view
        setContentPane(mainParentPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void createHistTablePanel(AbstractTableModel newTableModel){
        JTable patientTable = new JTable(newTableModel);
        // configure dimensions
        patientTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        historicalScrollPane.setViewportView(patientTable);
    }

    public JTextField getPracIDfield() {
        return pracIDfield;
    }

    public JTextField getFreqField() {
        return freqField;
    }

    public JButton getUpdatePracButton() {
        return updatePracButton;
    }

    public JList getPatientJList() {
        return patientJList;
    }

    public void setPatientJList(JList patientJList) {
        this.patientJList = patientJList;
    }

    public JPanel getDisplayPatient() {
        return displayPatient;
    }

    public JButton getUpdateFreqButton() {
        return updateFreqButton;
    }


    public JButton getMonitorCholButton() {
        return monitorCholButton;
    }

    public JButton getStopMonitorButton() {
        return stopMonitorButton;
    }

    public JTable getCholMonitorTable() {
        return cholMonitorTable;
    }


    public JButton getMonitorBPButton() {
        return monitorBPButton;
    }

    public JButton getStopMonitorBPButton() { return stopMonitorBPButton; }

    public JTable getBpMonitorTable() {
        return bpMonitorTable;
    }

    // could replace with just one?
    public JButton getUpdateDbpMinButton(){return updateDbpMinButton;}
    public JButton getUpdateSbpMinButton(){return updateSbpMinButton;}

    public JTextField getDbpMinField() {
        return dbpMinField;
    }

    public JTextField getSbpMinField() {
        return sbpMinField;
    }

    public JLabel getDbpMinLabel(){return dbpMinVal;}

    public JLabel getSbpMinLabel(){return sbpMinVal;}

    public JLabel getFreqValueLabel() {
        return freqValueLabel;
    }

    public JLabel getPatientGenderLabel() {
        return patientGender;
    }

    public JLabel getPatientAddressLabel() {
        return patientAddress;
    }

    public JLabel getPatientBirthDateLabel() {
        return patientBirthDate;
    }

    public JLabel getPatientNameLabel() {
        return patientName;
    }

    public JButton getAddPanelButton(){return addPanelButton;}

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainParentPanel = new JPanel();
        mainParentPanel.setLayout(new GridLayoutManager(9, 4, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainParentPanel.add(panel1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pracIDLabel = new JLabel();
        pracIDLabel.setText("Practitoner Identifier");
        pracIDLabel.setVerticalAlignment(0);
        panel1.add(pracIDLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pracIDfield = new JTextField();
        panel1.add(pracIDfield, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        updatePracButton = new JButton();
        updatePracButton.setText("Update");
        panel1.add(updatePracButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientListScrollPane = new JScrollPane();
        mainParentPanel.add(patientListScrollPane, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        patientJList = new JList();
        patientJList.setLayoutOrientation(0);
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        patientJList.setModel(defaultListModel1);
        patientJList.setSelectionBackground(new Color(-11833681));
        patientListScrollPane.setViewportView(patientJList);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainParentPanel.add(panel2, new GridConstraints(0, 3, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        monitorCholLabel = new JLabel();
        monitorCholLabel.setText("Cholesterol Monitor");
        panel2.add(monitorCholLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        monitorScrollPane = new JScrollPane();
        panel2.add(monitorScrollPane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        stopMonitorButton = new JButton();
        stopMonitorButton.setText("Stop Monitoring");
        panel2.add(stopMonitorButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainParentPanel.add(panel3, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainParentPanel.add(panel4, new GridConstraints(4, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel4.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        selectedMonitoredPanel = new JPanel();
        selectedMonitoredPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainParentPanel.add(selectedMonitoredPanel, new GridConstraints(6, 3, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        selectedMonitoredPanel.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        selectedLabel = new JLabel();
        selectedLabel.setText("Additional Patient Information");
        selectedMonitoredPanel.add(selectedLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        displayPatient = new JPanel();
        displayPatient.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        selectedMonitoredPanel.add(displayPatient, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        patientGender = new JLabel();
        patientGender.setText("Gender: ");
        displayPatient.add(patientGender, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientAddress = new JLabel();
        patientAddress.setText("Birthdate:");
        displayPatient.add(patientAddress, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientBirthDate = new JLabel();
        patientBirthDate.setText("Address: ");
        displayPatient.add(patientBirthDate, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientName = new JLabel();
        patientName.setText("Name: ");
        displayPatient.add(patientName, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        monitorBPButton = new JButton();
        monitorBPButton.setText("Monitor Blood Pressure");
        mainParentPanel.add(monitorBPButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        monitorCholButton = new JButton();
        monitorCholButton.setText("Monitor Cholesterol");
        mainParentPanel.add(monitorCholButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainParentPanel.add(panel5, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        monitorBPLabel = new JLabel();
        monitorBPLabel.setText("Blood Pressure Monitor");
        panel5.add(monitorBPLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        monitorBPScrollPane = new JScrollPane();
        panel5.add(monitorBPScrollPane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        stopMonitorBPButton = new JButton();
        stopMonitorBPButton.setText("Stop Monitoring");
        panel5.add(stopMonitorBPButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        frequencyPanel = new JPanel();
        frequencyPanel.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainParentPanel.add(frequencyPanel, new GridConstraints(6, 0, 3, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        freqLabel = new JLabel();
        freqLabel.setText("Frequency of updates (seconds):");
        frequencyPanel.add(freqLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(276, 16), null, 0, false));
        freqField = new JTextField();
        freqField.setText("");
        frequencyPanel.add(freqField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(90, -1), null, 0, false));
        updateFreqButton = new JButton();
        updateFreqButton.setText("Update");
        frequencyPanel.add(updateFreqButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        freqValueLabel = new JLabel();
        freqValueLabel.setText("3");
        frequencyPanel.add(freqValueLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainParentPanel;
    }

}
