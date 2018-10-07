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
    JLabel labelWww = new JLabel("Enter your organization name Jira");
    JCheckBox wwwCh = new JCheckBox();
    JTextField wwAdd = new JTextField(20);

    JLabel projectLabel = new JLabel("Enter project key");
    JCheckBox projectCh = new JCheckBox();
    JTextField projectField = new JTextField(20);

    JLabel login = new JLabel("Enter login");
    JCheckBox loginCh = new JCheckBox();
    JTextField loginField = new JTextField(20);

    JLabel pass = new JLabel("Enter password");
    JCheckBox passCh = new JCheckBox();
    JTextField passField = new JTextField(20);

    public JButton next() {
        JButton nextB = new JButton("Click to go");
        nextB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowGui step2 = new WindowGui();
                configWindow.dispose();
                step2.mainWindow();
            }
        });
        return nextB;
    }

    public JPanel txtF() {
        JPanel textField = new JPanel();
        textField.add(labelWww);
        textField.add(wwAdd);
        textField.add(wwwCh());
        textField.add(projectLabel);
        textField.add(projectField);
        textField.add(projectCh());
        textField.add(login);
        textField.add(loginField);
        textField.add(loginCh());
        textField.add(pass);
        textField.add(passField);
        textField.add(passCh());
        textField.add(next());
        textField.setLayout(new GridLayout(5, 3));
        return textField;
    }

    public void configWindow() {
        configWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configWindow.setSize(600, 300);
        configWindow.add(txtF());
        configWindow.setVisible(true);
        configWindow.setTitle("Config");

    }

    public JCheckBox wwwCh() {
        wwwCh.setSelected(false);
        wwwCh.isVisible();
        wwwCh.revalidate();
        wwwCh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (wwwCh.isSelected()) {
                    wwwAddress = wwAdd.getText();
                    System.out.println(wwwAddress);
                }
            }
        });
        return wwwCh;
    }

    public JCheckBox projectCh() {
        projectCh.setSelected(false);
        projectCh.isVisible();
        projectCh.revalidate();
        projectCh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (projectCh.isSelected()) {
                    jiraProjectKey = projectField.getText();
                    System.out.println(jiraProjectKey);
                }
            }
        });
        return projectCh;
    }

    public JCheckBox loginCh() {
        loginCh.setSelected(false);
        loginCh.isVisible();
        loginCh.revalidate();
        loginCh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loginCh.isSelected()) {
                    jiraLogin = loginField.getText();
                    System.out.println(jiraLogin);
                }
            }
        });
        return loginCh;
    }

    public JCheckBox passCh() {
        passCh.setSelected(false);
        passCh.isVisible();
        passCh.revalidate();
        passCh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (passCh.isSelected()) {
                    jiraPass = passField.getText();
                    System.out.println(jiraPass);
                }
            }
        });
        return passCh;
    }

    public static void main(String[] args) {
        Step1Config starter = new Step1Config();
        starter.configWindow();

    }
}

