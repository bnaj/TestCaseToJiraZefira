import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jan B.
 * @date 2018-10-14
 * @class This is first window class of program.
 * This class generate first program window where user can start configuration.
 */
public class Step1Config extends JFrame {

    /**
     * Variables bellow are used to forward test load configuration to JiraIssuesApi class.
     * All variables are assigned from fields.
     * @wwwAddress
     * @jiraLogin
     * @jiraPass
     * @jiraProjectKey
     */
    public static String wwwAddress;
    public static String jiraLogin;
    public static String jiraPass;
    public static String jiraProjectKey;

    /**
     * @WindowObjectCreation
     * Lines below represents creation of obcject used in windows in this class.
     */
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

    /**
     * @backButtonInStep1 this methods generate button used in error window in this class.
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

    /**
     * @nextButtonInStep1 this method generate button used in configWindow in this class.
     * Start two other methods like getDataFromField and ifValidation.
     * If statment check check visibility of errorWindow. If is showing user can't go to Step2Config
     * @return nextButton
     */
    public JButton nextButton() {
        JButton nextButton = new JButton("Click to step 2");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getDataFromField();
                ifValidation();
                Step2Config step2 = new Step2Config();
                if (errorWindow.isShowing()) {
                } else {
                    configWindow.dispose();
                    step2.configWindow();
                }
            }
        });
        return nextButton;
    }

    /**
     * @getDataFormFieldInStep1Class this method get data from field in configWindowStep1.
     */
    public void getDataFromField() {
        wwwAddress = wwwAddressField.getText();
        jiraLogin = loginField.getText();
        jiraProjectKey = projectField.getText();
        jiraPass = passField.getText();
    }

    /**
     * @ifValidation this method validate are the fields filed.
     * Validate fields:
     * wwwAddressField
     * loginField
     * projectField
     */
    public void ifValidation() {
        if (wwwAddressField.getText().equals("")) {
            errorWindow("All fields must be filled!");
        } else if (loginField.getText().equals("")) {
            errorWindow("All fields must be filled!");
        } else if (projectField.getText().equals("")) {
            errorWindow("All fields must be filled!");
        }
    }

    /**
     * @textFieldInStep1ConfigClass generate JPanel object with JLabels:
     * WwwAddressLabel - label with describe wwwAddressField field.
     * projectLabel - label with describe projectField field.
     * loginLabel - label with describe loginField field.
     * passLabel - label with describe passField field.
     * adn JFields:
     * wwwAddressField - field which Jira address like http://192.168.0.116:8080.
     * projectField - field which specifies project key name like "PROJ".
     * loginField - field which specifies user login.
     * passField - field which specifies user password.
     * @return textField JPanel
     */
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
        textField.setLayout(new GridLayout(4, 2));
        return textField;
    }

    /**
     * @configWindowInStep1ConfigClass configurate object JFrame configWindow.
     * In this method to configWindow is added textField panel and nextButton.
     */
    public void configWindow() {
        configWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configWindow.setSize(900, 400);
        configWindow.add(txtField());
        configWindow.add(nextButton(), BorderLayout.SOUTH);
        configWindow.setVisible(true);
        configWindow.setTitle("Config panel");
        configWindow.setLocationRelativeTo(null);
    }

    /**
     * @errorWindowInStep1ConfigClass configurate object JFrame errorWindow.
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
     * @Main methods who starting program.
     * @param args
     */
    public static void main(String[] args) {
        Step1Config starter = new Step1Config();
        starter.configWindow();
    }
}