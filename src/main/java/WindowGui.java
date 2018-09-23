import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WindowGui extends JFrame {

    public static ArrayList<String> selectedScenarios = new ArrayList<String>();
    public List<JCheckBox> checkBoxesList = new ArrayList<JCheckBox>();
    JPanel checkboxPanel = new JPanel();
    TestScenarioLoad tcLoad = new TestScenarioLoad();


    public void mainWindow() {
        //  Jira.driver().get("");
        WindowGui mainwindow = new WindowGui();
        mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainwindow.setSize(400, 200);
        mainwindow.add(buttons(), BorderLayout.WEST);
        mainwindow.add(jspCheckbox());
        mainwindow.setVisible(true);
        mainwindow.setTitle("Add scenario");

    }
    public JScrollPane jspCheckbox(){
        JScrollPane jspCheckbox = new JScrollPane(checkboxPanel);
        jspCheckbox.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspCheckbox.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
    return jspCheckbox;}

    public JPanel buttons(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(addFile());
        buttonPanel.add(run());
        buttonPanel.add(deleteSt());
        return buttonPanel;
    }

    public void ActionFileChoser() {
        JFileChooser fileChooser = new JFileChooser("/home/spike/Pulpit");
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        int status = fileChooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {

            File[] selectedFile = fileChooser.getSelectedFiles();
            for (File filesss : selectedFile) {
                selectedScenarios.add(filesss.getName());
                checkBoxesList.add(new JCheckBox(filesss.getName()));
            }
            //System.out.println(selectedScenarios.get(1));
            System.out.println(selectedScenarios.get(0));
        }
        tcCheckbox();
    }

    public JButton addFile() {
        JButton addFile = new JButton("<html>Click to add<br>scenario</html");

        addFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ActionFileChoser();
            }
        });
        return addFile;
    }

    public JButton run() {
        JButton run = new JButton("Click to run ");
        run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tcLoad.allStuff();
            }
        });
        return run;
    }

    public JButton deleteSt() {
        JButton deleteSt = new JButton("Click to delete");
        deleteSt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JCheckBox box : checkBoxesList) {
                    if (box.isSelected()) {
                        for (int x = 0; x < selectedScenarios.size(); x++) {
                            if (box.getText().equals(selectedScenarios.get(x)))
                                selectedScenarios.remove(x);
                            checkboxPanel.remove(box);
                            checkboxPanel.revalidate();
                            checkboxPanel.repaint();
                        }
                    }
                }
            }
        });
        return deleteSt;
    }

    public void tcCheckbox() {
        for (JCheckBox box : checkBoxesList) {
            checkboxPanel.add(box);
            box.setSelected(false);
            box.isVisible();
            checkboxPanel.revalidate();
        }
    }


    public static void main(String[] args) {
        WindowGui starter = new WindowGui();
        starter.mainWindow();
    }
}
