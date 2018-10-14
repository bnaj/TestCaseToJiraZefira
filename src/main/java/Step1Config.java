import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Step1Config extends JFrame {

    public static String wwwAddress;
    public static String jiraLogin;
    public static String jiraPass;
    public static String jiraProjectKey;

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

    public void getDataFromField() {
        wwwAddress = wwwAddressField.getText();
        jiraLogin = loginField.getText();
        jiraProjectKey = projectField.getText();
        jiraPass = passField.getText();
        System.out.println(wwwAddress);
        System.out.println(jiraLogin);
        System.out.println(jiraProjectKey);
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
        textField.setLayout(new GridLayout(4, 2));
        return textField;
    }

    public void configWindow() {
        configWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configWindow.setSize(900, 400);
        configWindow.add(txtField());
        configWindow.add(nextButton(), BorderLayout.SOUTH);
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

