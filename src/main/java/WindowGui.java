import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WindowGui extends JFrame {
    public static String filesPath;
    public static ArrayList<String> selectedScenarios = new ArrayList<String>();
    public List<JCheckBox> checkBoxesList = new ArrayList<JCheckBox>();
    JPanel checkboxPanel = new JPanel();
    TestScenarioLoad testCaseLoad = new TestScenarioLoad();
    TestScenarioLoad2 testCaseLoad2 = new TestScenarioLoad2();

    public void mainWindow() {
        WindowGui mainwindow = new WindowGui();
        mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainwindow.setSize(400, 200);
        mainwindow.add(buttons(), BorderLayout.WEST);
        mainwindow.add(jspCheckbox());
        mainwindow.setVisible(true);
        mainwindow.setTitle("Add scenario");
        mainwindow.setLocationRelativeTo(null);
    }

    public JScrollPane jspCheckbox() {
        JScrollPane jspCheckbox = new JScrollPane(checkboxPanel);
        jspCheckbox.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspCheckbox.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        return jspCheckbox;
    }

    public JPanel buttons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(addFile());
        buttonPanel.add(runProgram());
        buttonPanel.add(deleteTestCase());
        return buttonPanel;
    }

    public void ActionFileChoser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        int status = fileChooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {

            File[] selectedFile = fileChooser.getSelectedFiles();
            for (File files : selectedFile) {
                selectedScenarios.add(files.getName());
                checkBoxesList.add(new JCheckBox(files.getName()));
            }
            filesPath = selectedFile[0].getAbsolutePath().replace(selectedFile[0].getName(), "");
        }
        testCaseCheckbox();
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

    public JButton runProgram() {
        JButton run = new JButton("Click to run");
        run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Step2Config.stepsStartRow > 0) {
                    testCaseLoad2.allStuff();
                } else {
                    testCaseLoad.allStuff();
                }
            }
        });
        return run;
    }

    public JButton deleteTestCase() {
        JButton deleteSt = new JButton("Click to delete");
        deleteSt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JCheckBox box : checkBoxesList) {
                    if (box.isSelected()) {
                        for (int x = 0; x < selectedScenarios.size(); x++) {
                            if (box.getText().equals(selectedScenarios.get(x)))
                                selectedScenarios.remove(x);
                            System.out.println(WindowGui.filesPath);
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

    public void testCaseCheckbox() {
        for (JCheckBox box : checkBoxesList) {
            checkboxPanel.add(box);
            box.setSelected(false);
            box.isVisible();
            checkboxPanel.revalidate();
        }
    }
}
