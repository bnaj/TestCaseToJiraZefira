import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan B.
 * @date 2018-10-14
 * @class This is third window class of program.
 * This class generate first program window where user can start configuration.
 */
public class WindowGui extends JFrame {

    /**
     * @filsPath variable is used to forwarding path to file.
     * Is evoked in TestScenarioLoad or TestScenarioLoad2 class.
     */
    public static String filesPath;

    /**
     * @WindowObjectCreation
     * Lines below represents creation of obcject used in windows in this class.
     */
    public static ArrayList<String> selectedScenarios = new ArrayList<String>();
    public List<JCheckBox> checkBoxesList = new ArrayList<JCheckBox>();
    JPanel checkboxPanel = new JPanel();
    TestScenarioLoad testCaseLoad = new TestScenarioLoad();
    TestScenarioLoad2 testCaseLoad2 = new TestScenarioLoad2();

    /**
     * @mainWindow configurate object JFrame mainwindow.
     * In this method to configWindow is added:
     * JPanel buttons
     * JScrollPane jspCheckbox
     * nextButton
     *
     */
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

    /**
     * @jspCheckbox configurate object JScrollPane.
     * This method is responsible for scrolling bar.
     */
    public JScrollPane jspCheckbox() {
        JScrollPane jspCheckbox = new JScrollPane(checkboxPanel);
        jspCheckbox.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspCheckbox.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        return jspCheckbox;
    }

    /**
     * @buttons configurate object JPanel.
     * This method is responsible for configuration of buttons:
     * addFile - this button evoke actionFileChoser method.
     * runProgram - this button evoke allStuff method in class testCaseLoad2 or testCaseLoad.
     * deleteTestCase - this button delete files from list.
     */
    public JPanel buttons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(addFile());
        buttonPanel.add(runProgram());
        buttonPanel.add(deleteTestCase());
        return buttonPanel;
    }

    /**
     * @actionFileChoser open new window where user can select test scenario file to load.
     * In this method program also assigns path to variable filesPath.
     */
    public void actionFileChoser() {
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

    /**
     * @addFile this method generate button which button used to evoke actionFileChoser.
     * @return JButton addFile
     */
    public JButton addFile() {
        JButton addFile = new JButton("<html>Click to add<br>scenario</html");
        addFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionFileChoser();
            }
        });
        return addFile;
    }

    /**
     * @runProgram this method generate button which evoke allStuff method in testCaseLoad
     * or testCaseLoad2 class depending on the condition
     * of if statment.
     * @return JButton run
     */
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

    /**
     * @deleteTestCase this method generate button used to delete files from list
     * @return deleteSt
     */
    public JButton deleteTestCase() {
        JButton deleteSt = new JButton("Click to delete");
        deleteSt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JCheckBox box : checkBoxesList) {
                    if (box.isSelected()) {
                        for (int x = 0; x < selectedScenarios.size(); x++) {
                            if (box.getText().equals(selectedScenarios.get(x))) selectedScenarios.remove(x);
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

    /**
     * @testCaseCheckbox this method use for each loop to adding checkbox to checkboxPanel is evoke
     * in actionFileChoser method.
     */
    public void testCaseCheckbox() {
        for (JCheckBox box : checkBoxesList) {
            checkboxPanel.add(box);
            box.setSelected(false);
            box.isVisible();
            checkboxPanel.revalidate();
        }
    }
}