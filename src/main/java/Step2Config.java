import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jan B.
 * @date 2018-10-14
 * @class This is second window class of program.
 * This class generate first program window where user can start configuration.
 */
public class Step2Config extends JFrame {

    /**
     * Variables bellow are used to forward test load configuration to TestScenarioLoad or TestScenarioLoad2 class.
     * All variables are assigned from fields.
     * @scenarioTestSummaryRow
     * @scenarioTestSummaryCell
     * @scenarioTestDescriptionRow
     * @scenarioTestDescriptionCell
     * @stepsStartRow
     * @checkboxState
     */
    public static int scenarioTestSummaryRow;
    public static int scenarioTestSummaryCell;
    public static int scenarioTestDescriptionRow;
    public static int scenarioTestDescriptionCell;
    public static int stepsStartRow;
    public static int checkboxState;

    /**
     * @WindowObjectCreation
     * Lines below represents creation of obcject used in windows in this class.
     */
    JFrame errorWindow = new JFrame();
    JFrame configWindow = new JFrame();

    JLabel summaryLabel = new JLabel("Enter row number of summary");
    JTextField summaryRow = new JTextField(5);
    JLabel summaryLabelCell = new JLabel("Enter cell number of summary txt in row");
    JTextField summaryCell = new JTextField(5);
    JLabel descriptionLabel = new JLabel("Enter row number of description");
    JTextField descriptionRow = new JTextField(5);
    JLabel descriptionLabelCell = new JLabel("Enter cell number of description txt in row");
    JTextField descriptionCell = new JTextField(5);

    JCheckBox testData = new JCheckBox("<html>Do you use test data in zapi?<br/> check this box only if your test case" +
            " files looks like:<br/> |step number|step instruction|step data|step resoult|</html>");
    JLabel stepsStartsLabel = new JLabel("<html>Enter first steps row." +
            "<br/>If they first row is not 3 in your file (optional)</html>");
    JTextField stepsStarts = new JTextField(5);

    /**
     * @getDataFormFieldInStep2Class this method get data from field in configWindowStep1.
     */
    public void getDataFromField() {
        try {
            scenarioTestSummaryRow = Integer.parseInt(summaryRow.getText());
        } catch (NumberFormatException f) {
            errorWindow("In the field: " + "\n" + summaryLabel.getText() +
                    "\nYOU MUST ENTER NUMERIC VALUE!\nTHIS FIELD CANT BE EMPTY!");
        }
        try {
            scenarioTestSummaryCell = Integer.parseInt(summaryCell.getText());
        } catch (NumberFormatException f) {
            errorWindow("In the field: " + "\n" + summaryLabelCell.getText() +
                    "\nYOU MUST ENTER NUMERIC VALUE!\nTHIS FIELD CANT BE EMPTY!");
        }
        try {
            scenarioTestDescriptionRow = Integer.parseInt(descriptionRow.getText());
        } catch (NumberFormatException f) {
            errorWindow("In the field: " + "\n" + descriptionLabel.getText() +
                    "\nYOU MUST ENTER NUMERIC VALUE!\nTHIS FIELD CANT BE EMPTY!");
        }
        try {
            scenarioTestDescriptionCell = Integer.parseInt(descriptionCell.getText());
        } catch (NumberFormatException f) {
            errorWindow("In the field: " + "\n" + descriptionLabelCell.getText() +
                    "\nYOU MUST ENTER NUMERIC VALUE!\nTHIS FIELD CANT BE EMPTY!");
        }
        if (!(stepsStarts.getText().equals(""))) {
            try {
                stepsStartRow = Integer.parseInt(stepsStarts.getText());
            } catch (NumberFormatException f) {
                errorWindow("In the field: " + "\n" + stepsStarts.getText() +
                        "\nYOU MUST ENTER NUMERIC VALUE!");
            }
        }
    }

    /**
     * @configWindowInStep2ConfigClass configurate object JFrame configWindow.
     * In this method to configWindow is added textField panel and nextButton.
     */
    public void configWindow() {
        configWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configWindow.setSize(900, 400);
        configWindow.add(txtField());
        configWindow.add(nextButton(), BorderLayout.SOUTH);
        configWindow.setVisible(true);
        configWindow.setTitle("Config panel step 2");
        configWindow.setLocationRelativeTo(null);
    }

    /**
     * @textFieldInStep1ConfigClass generate JPanel object with JLabels:
     * summaryLabel - label with describe summaryRow field.
     * summaryLabelCell - label with describe summaryCell field.
     * descriptionLabel - label with describe descriptionRow field.
     * descriptionLabelCell - label with describe descriptionCell field.
     * stepsStartsLabel- label with describe stepsStarts field.
     * and JFields this fields accepts only integer value:
     * summaryRow - field which specifies row number of summary.
     * summaryCell - field which specifies cell number of summary.
     * descriptionRow - field which specifies row number of description.
     * descriptionCell - field which specifies cell number of description.
     * stepsStarts - field which specifies row where start test steps.
     * and JCheckbox with change state of checkboxState variable:
     * testData - Checkbox which specifies how many columns progrm load to Zephira.
     * @return textField JPanel
     */
    public JPanel txtField() {
        JPanel textField = new JPanel();
        textField.add(summaryLabel, 0);
        textField.add(summaryRow, 1);
        textField.add(summaryLabelCell, 2);
        textField.add(summaryCell, 3);
        textField.add(descriptionLabel, 4);
        textField.add(descriptionRow, 5);
        textField.add(descriptionLabelCell, 6);
        textField.add(descriptionCell, 7);
        textField.add(stepsStartsLabel, 8);
        textField.add(stepsStarts, 9);
        textField.add(testData, 10);
        textField.setLayout(new GridLayout(7, 2));
        return textField;
    }

    /**
     * @errorWindowInStep2ConfigClass configurate object JFrame errorWindow.
     * @param messageText this parameter will be display in error window.
     * @return
     */
    public JFrame errorWindow(String messageText) {
        JTextPane message = new JTextPane();
        message.setEditable(false);
        message.setBackground(Color.red);
        errorWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        errorWindow.setSize(300, 200);
        errorWindow.add(message, BorderLayout.CENTER);
        message.setText(messageText);
        errorWindow.add(backButton(), BorderLayout.SOUTH);
        errorWindow.setVisible(true);
        errorWindow.setTitle("!Warning!");
        errorWindow.setLocationRelativeTo(null);
        errorWindow.repaint();
        return errorWindow;
    }

    /**
     * @nextButtonInStep2 this method generate button used in configWindow in this class
     * @return nextButton
     */
    public JButton nextButton() {
        JButton nextButton = new JButton("Click to go");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                getDataFromField();
                WindowGui step3 = new WindowGui();
                if (errorWindow.isShowing()) {
                } else {
                    configWindow.dispose();
                    if (testData.isSelected()) {
                        checkboxState = 1;
                        System.out.println(checkboxState);
                    }
                    step3.mainWindow();
                }
            }
        });
        return nextButton;
    }

    /**
     * @backButtonInStep2 this methods generate button used in error window in this class.
     * Action performed by this button dispose errorWindow.
     * @return backButton
     */
    public JButton backButton() {
        JButton backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                errorWindow.dispose();
            }
        });
        return backButton;
    }
}
