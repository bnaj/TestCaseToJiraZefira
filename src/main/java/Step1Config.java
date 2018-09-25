import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Step1Config extends JFrame  {
   public String dsadadsa = "s";
    public static String wwwAddress="sadsadas";

    JPanel textField = new JPanel();
    JCheckBox wwwCh = new JCheckBox();
    JTextField wwAdd = new JTextField(20);
    public void configWindow() {
        System.out.println(wwwAddress);
        WindowGui configWindow = new WindowGui();
        configWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configWindow.setSize(400, 200);
        textField.add(wwAdd);
        textField.add(wwwCh());
        configWindow.add(textField);
        configWindow.setVisible(true);
        configWindow.setTitle("Config");


    }




    public JCheckBox wwwCh() {

        wwwCh.setSelected(false);
        wwwCh.isVisible();
        wwwCh.revalidate();
        wwwCh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(wwwCh.isSelected()){
                wwwAddress = wwAdd.getText();
                System.out.println(wwwAddress);
            }}
        });
        return wwwCh; }


    public static void main(String[] args) {
        Step1Config starter = new Step1Config();
        starter.configWindow();

    }


    }

