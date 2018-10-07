import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestScenarioLoad {

    public List<XWPFTable> prepareTables(String fileName) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/home/spike/Pulpit/" + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XWPFDocument xdoc = null;
        try {
            xdoc = new XWPFDocument(OPCPackage.open(fis));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        List<XWPFTable> tables = xdoc.getTables();
        return tables;
    }


    public ArrayList<String> tablesStepsIterator(String fileName) {
        ArrayList<String> stepsArray = new ArrayList<String>();

        for (XWPFTable table : prepareTables(fileName)) {

            for (XWPFTableRow row : table.getRows()) {

                if (table.getRows().indexOf(row) >= 2) {
                    stepsArray.add(row.getCell(1).getText());
                }
            }
        }
        return stepsArray;
    }

    public ArrayList<String> tablesResoultIterator(String fileName) {
        ArrayList<String> resoultArr = new ArrayList<String>();
        for (XWPFTable table : prepareTables(fileName)) {
            for (XWPFTableRow row : table.getRows()) {

                if (table.getRows().indexOf(row) >= 2) {
                    resoultArr.add(row.getCell(2).getText());
                }
            }
        }
        return resoultArr;
    }

    public String firstScenarioData(int rowPosition, int cellInRow, String fileName) {
        String getOne = null;

        for (XWPFTable table : prepareTables(fileName)) {
            getOne = table.getRow(rowPosition).getCell(cellInRow).getText();
        }
        return getOne;
    }

    public void allStuff() {
        ArrayList<String> file = WindowGui.selectedScenarios;
        for (int fileNumber = 0; fileNumber < file.size(); fileNumber++) {
            JiraIssuesApi.createIssue("PROJ",
                    firstScenarioData(0, 1, file.get(fileNumber)),
                    firstScenarioData(1, 1, file.get(fileNumber)));

            System.out.println(firstScenarioData(0, 1, file.get(fileNumber)));
            System.out.println(firstScenarioData(1, 1, file.get(fileNumber)));

            for (int x = 0; x < tablesResoultIterator(file.get(fileNumber)).size(); x++) {

                JiraIssuesApi.zephyr(tablesStepsIterator(file.get(fileNumber)).get(x),
                        tablesResoultIterator(file.get(fileNumber)).get(x));
                System.out.println(tablesStepsIterator(file.get(fileNumber)).get(x));
                System.out.println(tablesResoultIterator(file.get(fileNumber)).get(x));
            }
        }
    }
}
