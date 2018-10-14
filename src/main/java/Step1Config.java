import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Step1Config extends JFrame {

    public static String wwwAddress;
    public static String jiraLogin;
    public static String jiraPass;
    public static String jiraProjectKey;
    public static int scenarioTestSummaryRow;
    public static int scenarioTestSummaryCell;
    public static int scenarioTestDescriptionRow;
    public static int scenarioTestDescriptionCell;

    JFrame errorWindow = new JFrame();
    JFrame configWindow = new JFrame();
    JLabel WwwAddressLabel = new JLabel("<html>Enter your Jira organization name<br/> " +
            "like http://192.168.0.116:8080</html>");
    JTextField wwwAddressField = new JTextField(30);
    JLabel projectLabel = new JLabel("Enter project key");
    JTextField projectField = new JTextField(30);
    JLabel loginLabel = new JLabel("Enter login");
    JTextField loginField = new JTextField(30);
    JLabel passLabel = new JLabel("Enter password");
    JPasswordField passField = new JPasswordField(30);
    JLabel summaryLabel = new JLabel("Enter row number of summary");
    JTextField summaryRow = new JTextField(5);
    JLabel summaryLabelCell = new JLabel("Enter cell number of summary txt in row");
    JTextField summaryCell = new JTextField(5);
    JLabel descriptionLabel = new JLabel("Enter row number of description");
    JTextField descriptionRow = new JTextField(5);
    JLabel descriptionLabelCell = new JLabel("Enter cell number of description txt in row");
    JTextField descriptionCell = new JTextField(5);

    public JButton backButton() {
        JButton backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                errorWindow.dispose();
            }
        });
        return backButton;
    }

    public JButton nextButton() {
        JButton nextButton = new JButton("Click to go");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                getDataFromField();
                ifValidation();
                WindowGui step2 = new WindowGui();
                if (errorWindow.isShowing()) {
                } else {
                    configWindow.dispose();
                    step2.mainWindow();
                }
            }
        });
        return nextButton;
    }

    public void getDataFromField() {
        wwwAddress = wwwAddressField.getText();
        jiraLogin = loginField.getText();
        jiraProjectKey = projectField.getText();
        jiraPass = passField.getText();

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
        System.out.println(wwwAddress);
        System.out.println(jiraLogin);
        System.out.println(jiraProjectKey);
        System.out.println(scenarioTestSummaryRow);
        System.out.println(scenarioTestSummaryCell);
        System.out.println(scenarioTestDescriptionRow);
        System.out.println(scenarioTestDescriptionCell);
    }

    public void ifValidation() {
        if (wwwAddressField.getText().equals("")) {
            errorWindow("All fields must be filled!");
        } else if (loginField.getText().equals("")) {
            errorWindow("All fields must be filled!");
        } else if (projectField.getText().equals("")) {
            errorWindow("All fields must be filled!");
        }
    }

    public JPanel txtField() {
        JPanel textField = new JPanel();
        textField.add(WwwAddressLabel, 0);
        textField.add(wwwAddressField, 1);
        textField.add(projectLabel, 2);
        textField.add(projectField, 3);
        textField.add(loginLabel, 4);
        textField.add(loginField, 5);
        textField.add(passLabel, 6);
        textField.add(passField, 7);
        textField.add(summaryLabel, 8);
        textField.add(summaryRow, 9);
        textField.add(summaryLabelCell, 10);
        textField.add(summaryCell, 11);
        textField.add(descriptionLabel, 12);
        textField.add(descriptionRow, 13);
        textField.add(descriptionLabelCell, 14);
        textField.add(descriptionCell, 15);
        textField.add(nextButton(), 16);
        textField.setLayout(new GridLayout(9, 2));
        return textField;
    }

    public void configWindow() {
        configWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configWindow.setSize(900, 400);
        configWindow.add(txtField());
        configWindow.setVisible(true);
        configWindow.setTitle("Config panel");
        configWindow.setLocationRelativeTo(null);
    }

    public JFrame errorWindow(String messageText) {
        JTextPane message = new JTextPane();
        message.setEditable(false);
        message.setBackground(Color.red);
        errorWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        errorWindow.setSize(300, 200);
        errorWindow.add(message,BorderLayout.CENTER);
        message.setText(messageText);
        errorWindow.add(backButton(),BorderLayout.SOUTH);
        errorWindow.setVisible(true);
        errorWindow.setTitle("!Warning!");
        errorWindow.setLocationRelativeTo(null);
        errorWindow.repaint();
        return errorWindow;
    }

    public static void main(String[] args) {
        Step1Config starter = new Step1Config();
        starter.configWindow();
    }
}

