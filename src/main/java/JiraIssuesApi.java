import org.apache.xmlbeans.impl.util.Base64;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * @Class this class is used for communication with serwer.
 */
public class JiraIssuesApi {

    public static String issueID;
    public static Step1Config errorWindow = new Step1Config();

    /**
     * @createIssue this method is used to create issues in Jira projest.
     * @param projectKey - jira projectKey is suplied in allStuff methods in classes
     * TestScenarioLoad and TestScenarioLoad2 from variable in Step1Config.
     * @param summary - project key is supplied from getJSON_Body method.
     * @param description - project key is supplied from getJSON_Body method.
     * User login, password and jira wwwAddress are provided by variable from Step1Config class:
     * jiraLogin
     * jiraPass
     * wwwAddress
     * This method also supplied data to issueID variable.
     */
    public static void createIssue(String projectKey, String summary, String description) {
        try {
            URL url = new URL(Step1Config.wwwAddress + "/rest/api/2/issue/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            String login = Step1Config.jiraLogin;
            String password = Step1Config.jiraPass;
            String loginPassword = login + ":" + password;
            byte[] encoded = Base64.encode(loginPassword.getBytes());
            String credentials = new String(encoded);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + credentials);
            conn.setRequestProperty("Content-Type", "application/json");
            String encodedData = getJSON_Body(projectKey, summary, description);
            System.out.println(getJSON_Body(projectKey, summary, description));
            conn.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));
            conn.getOutputStream().write(encodedData.getBytes());
            int responseCode = conn.getResponseCode();

            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String[] inputLine;
                inputLine = in.readLine().split(":");
                String inputline2 = inputLine[2].replaceAll("[\",self]", "").trim();
                String inputline1 = inputLine[1].replaceAll("[\",key]", "").trim();
                issueID = inputline1;
                System.out.println(responseCode + " New issues id " + inputline1);
                System.out.println(responseCode + " New issues key " + inputline2);
                in.close();
            } catch (IOException e) {
                if(responseCode==403||responseCode==401) {
                    errorWindow.errorWindow(e.getMessage() + " " + responseCode +
                            "\n!PROBABLY YOU ARE UNAUTORIZED!\n!START PROGRAM ONE MORE TIME AND ENTER VALID CREDENTIAL!");
                }if (responseCode==404){
                    errorWindow.errorWindow(e.getMessage() + " " + responseCode +
                            "\n!PROBABLY YOU ENTER INWALID URL!\n!START PROGRAM ONE MORE TIME AND ENTER VALID ADDRESS!");
                }
                System.out.println(e.getMessage() + " " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @getJSON_Body generate json body file used to send request.
     * @param projectKey - jira projectKey is suplied in allStuff methods in classes
     * TestScenarioLoad and TestScenarioLoad2 from variable in Step1Config.
     * @param summary - is supplied in allStuff methods in classes
     * TestScenarioLoad and TestScenarioLoad2.
     * @param description - is supplied in allStuff methods in classes
     * TestScenarioLoad and TestScenarioLoad2.
     * @issuetype - this is parameter from json body and is permanently assigned to Test.
     * @return createIssue.toString()
     */
    public static String getJSON_Body(String projectKey, String summary, String description) {
        JsonObject createIssue = Json.createObjectBuilder()
                .add("fields",
                        Json.createObjectBuilder().add("project",
                                Json.createObjectBuilder().add("key", projectKey))
                                .add("summary", summary)
                                .add("description", description)
                                .add("issuetype",
                                        Json.createObjectBuilder().add("name", "Test"))).build();
        return createIssue.toString();
    }

    /**
     * @zephyr This methods field ssteps and steps resoult in Zephyr test case.
     * @param step - project key is supplied from getBodyForZephyr method.
     * @param resoult - project key is supplied from getBodyForZephyr method.
     * User login, password and jira wwwAddress are provided by variable from Step1Config class:
     * jiraLogin
     * jiraPass
     * wwwAddress
     * issueID - is supplied from varibale issueID in this class.
     */
    public static void zephyr(String step, String resoult) {
        try {
            URL url = new URL(Step1Config.wwwAddress + "/rest/zapi/latest/teststep/" + issueID);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            String login = Step1Config.jiraLogin;
            String password = Step1Config.jiraPass;
            String loginPassword = login + ":" + password;
            byte[] encoded = Base64.encode(loginPassword.getBytes());
            String credentials = new String(encoded);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + credentials);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            String encodedData = getBodyForZephyr(step, resoult);
            conn.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));
            conn.getOutputStream().write(encodedData.getBytes());
            System.out.println(getBodyForZephyr(step, resoult));
            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } catch (IOException e) {
                System.out.println(e.getMessage() + " " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @getBodyForZephyr generate json body file used to send request.
     * @param step - is supplied in allStuff methods in classes
     * TestScenarioLoad and TestScenarioLoad2.
     * @param resoult - is supplied in allStuff methods in classes
     * TestScenarioLoad and TestScenarioLoad2.
     * @return createSteps.toString()
     */
    public static String getBodyForZephyr(String step, String resoult) {
        JsonObject createSteps = Json.createObjectBuilder()
                .add("step", step)
                .add("data", "")
                .add("result", resoult)
                .build();
        return createSteps.toString();
    }

    /**
     * @zephyr This methods field ssteps and steps resoult in Zephyr test case.
     * @param step - project key is supplied from getBodyForZephyr method.
     * @param testData - project key is supplied from getBodyForZephyr method.
     * @param resoult - project key is supplied from getBodyForZephyr method.
     * @issueId - is supplied from issueID variables in this method
     * User login, password and jira wwwAddress are provided by variable from Step1Config class:
     * jiraLogin
     * jiraPass
     * wwwAddress
     * issueID - is supplied from varibale issueID in this class.
     */
    public static void zephyr(String step, String testData, String resoult) {
        try {
            URL url = new URL(Step1Config.wwwAddress + "/rest/zapi/latest/teststep/" + issueID);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            String login = Step1Config.jiraLogin;
            String password = Step1Config.jiraPass;
            String loginPassword = login + ":" + password;
            byte[] encoded = Base64.encode(loginPassword.getBytes());
            String credentials = new String(encoded);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + credentials);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            String encodedData = getBodyForZephyr(step, testData, resoult);
            conn.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));
            conn.getOutputStream().write(encodedData.getBytes());
            System.out.println(getBodyForZephyr(step, testData, resoult));
            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } catch (IOException e) {
                System.out.println(e.getMessage() + " " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @getBodyForZephyr generate json body file used to send request.
     * @param step - is supplied in allStuff methods in classes
     * TestScenarioLoad and TestScenarioLoad2.
     * @param testData - is supplied in allStuff methods in classes
     * TestScenarioLoad and TestScenarioLoad2.
     * @param resoult - is supplied in allStuff methods in classes
     * TestScenarioLoad and TestScenarioLoad2.
     * @return createSteps.toString()
     */
    public static String getBodyForZephyr(String step, String testData, String resoult) {
        JsonObject createSteps = Json.createObjectBuilder()
                .add("step", step)
                .add("data", testData)
                .add("result", resoult)
                .build();
        return createSteps.toString();
    }
}