import org.apache.xmlbeans.impl.util.Base64;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class JiraIssuesApi {

    public static String issueID;

    public static void createIssue(String projectKey, String summary, String description) {
        try {
            //URL url = new URL("http://192.168.0.116:8080/rest/api/2/issue/");
              URL url = new URL(Step1Config.wwwAddress + "/rest/api/2/issue/");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            String login = Step1Config.jiraLogin;//"janbnoir";
            String password = Step1Config.jiraPass;//"xep624";
            String loginPassword = login + ":" + password;

            byte[] encoded = Base64.encode(loginPassword.getBytes());
            String credentials = new String(encoded);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + credentials);
            conn.setRequestProperty("Content-Type", "application/json");
            String encodedData = getJSON_Body(projectKey,summary,description);
            System.out.println(getJSON_Body(projectKey,summary,description));
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
                System.out.println(e.getMessage() + " " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getJSON_Body(String projectKey, String summary, String description) {
        JsonObject createIssue = Json.createObjectBuilder()
                .add("fields",
                        Json.createObjectBuilder().add("project",
                                Json.createObjectBuilder().add("key", projectKey))
                                .add("summary", summary)
                                .add("description", description)
                                .add("issuetype",
                                        Json.createObjectBuilder().add("name", "Test"))
                ).build();

        return createIssue.toString();
    }

    public static void zephyr(String step, String resoult) {
        try {
            //URL url = new URL("http://192.168.0.116:8080/rest/zapi/latest/teststep/"+issueID);
              URL url = new URL(Step1Config.wwwAddress + "/rest/zapi/latest/teststep/"+issueID);


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            String login = Step1Config.jiraLogin;//"janbnoir";
            String password = Step1Config.jiraPass;//"xep624";
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

    public static String getBodyForZephyr(String step, String resoult) {
        JsonObject createSteps = Json.createObjectBuilder()
                .add("step", step)
                .add("data", "")
                .add("result", resoult)
                .build();

        return createSteps.toString();
    }


    public static void main(String[] args) {
    }
}
