import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Step1Config extends JFrame {

    public static String wwwAddress;
    public static String jiraLogin;
    public static String jiraPass;
    public static String jiraProjectKey;

    JFrame configWindow = new JFrame();
    JLabel WwwAddressLabel = new JLabel("<html>Enter your Jira organization name<br/> " +
            "like http://192.168.0.116:8080</html>");
    JCheckBox wwwCheckbox = new JCheckBox();
    JTextField wwwAddressField = new JTextField(30);
    JLabel projectLabel = new JLabel("Enter project key");
    JCheckBox projectCheckbox = new JCheckBox();
    JTextField projectField = new JTextField(30);
    JLabel loginLabel = new JLabel("Enter loginLabel");
    JCheckBox loginCheckbox = new JCheckBox();
    JTextField loginField = new JTextField(30);
    JLabel passLabel = new JLabel("Enter password");
    JCheckBox passCheckbox = new JCheckBox();
    JPasswordField passField = new JPasswordField(30);

    public JButton nextButton() {
        JButton nextButton = new JButton("Click to go");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowGui step2 = new WindowGui();
                configWindow.dispose();
                step2.mainWindow();
            }
        });
        return nextButton;
    }

    public JCheckBox wwwCheckbox() {
        wwwCheckbox.setSelected(false);
        wwwCheckbox.isVisible();
        wwwCheckbox.revalidate();
        wwwCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (wwwCheckbox.isSelected()) {
                    wwwAddress = wwwAddressField.getText();
                    System.out.println(wwwAddress);
                }
            }
        });
        return wwwCheckbox;
    }

    public JCheckBox projectCheckbox() {
        projectCheckbox.setSelected(false);
        projectCheckbox.isVisible();
        projectCheckbox.revalidate();
        projectCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (projectCheckbox.isSelected()) {
                    jiraProjectKey = projectField.getText();
                    System.out.println(jiraProjectKey);
                }
            }
        });
        return projectCheckbox;
    }

    public JCheckBox loginCheckbox() {
        loginCheckbox.setSelected(false);
        loginCheckbox.isVisible();
        loginCheckbox.revalidate();
        loginCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loginCheckbox.isSelected()) {
                    jiraLogin = loginField.getText();
                    System.out.println(jiraLogin);
                }
            }
        });
        return loginCheckbox;
    }

    public JCheckBox passCheckbox() {
        passCheckbox.setSelected(false);
        passCheckbox.isVisible();
        passCheckbox.revalidate();
        passCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (passCheckbox.isSelected()) {
                    jiraPass = passField.getText();
                }
            }
        });
        return passCheckbox;
    }

    public JPanel txtField() {
        JPanel textField = new JPanel();
        textField.add(WwwAddressLabel);
        textField.add(wwwAddressField);
        textField.add(wwwCheckbox());
        textField.add(projectLabel);
        textField.add(projectField);
        textField.add(projectCheckbox());
        textField.add(loginLabel);
        textField.add(loginField);
        textField.add(loginCheckbox());
        textField.add(passLabel);
        textField.add(passField);
        textField.add(passCheckbox());
        textField.add(nextButton());
        textField.setLayout(new GridLayout(5, 3));
        return textField;
    }

    public void configWindow() {
        configWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configWindow.setSize(800, 300);
        configWindow.add(txtField());
        configWindow.setVisible(true);
        configWindow.setTitle("Step 1 config");
        configWindow.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Step1Config starter = new Step1Config();
        starter.configWindow();

    }
}

