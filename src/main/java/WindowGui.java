import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class WindowGui extends JFrame {

    public static ArrayList<String> selectedScenarios = new ArrayList<String>();

    public static void ActionFileChoser() {
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
            }
            System.out.println(selectedScenarios.get(1));
            System.out.println(selectedScenarios.get(0));
        }
    }

    public static JButton addFile() {
        JButton addFile = new JButton("Click to add scenario");
        addFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ActionFileChoser();
            }
        });
        return addFile;
    }

    public static JButton run() {
        JButton run = new JButton("Click to run");
        run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TestScenarioLoad.allStuff();
            }
        });
        return run;
    }

    public static void zzzz() {
      //  Jira.driver().get("");
        WindowGui wg = new WindowGui();
        wg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wg.setSize(400, 200);
        JPanel panel = new JPanel();
        panel.add(addFile());
        panel.add(run());
        wg.getContentPane().add(panel);
        wg.setVisible(true);
        wg.setTitle("How many of doc to slut?");
    }

    public static void main(String[] args) {
        zzzz();
    }
}
