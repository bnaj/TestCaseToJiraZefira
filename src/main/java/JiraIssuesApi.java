import org.apache.xmlbeans.impl.util.Base64;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class JiraIssuesApi {

    public static void jiraSendRequest() {
        try {
            URL url = new URL("http://192.168.0.116:8080/rest/api/2/issue/");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            String login = "janbnoir";
            String password = "xep624";
            String loginPassword = login + ":" + password;

            byte[] encoded = Base64.encode(loginPassword.getBytes());
            String credentials = new String(encoded);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + credentials);
            conn.setRequestProperty("Content-Type", "application/json");
            String encodedData = getJSON_Body();
            System.out.println(getJSON_Body());
            conn.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));
            conn.getOutputStream().write(encodedData.getBytes());
            int responseCode = conn.getResponseCode();

            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String[] inputLine;
              //  StringBuffer response = new StringBuffer();
                inputLine = in.readLine().split(":");
                String inputline1 = inputLine[1].replaceAll("[\",key]", "").trim();
                System.out.println(responseCode + " New issues id " + inputline1);
                in.close();
            } catch (IOException e) {
                System.out.println(e.getMessage() + " " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getJSON_Body() {
        JsonObject createIssue = Json.createObjectBuilder()
                .add("fields",
                        Json.createObjectBuilder().add("project",
                                Json.createObjectBuilder().add("key", "PROJ"))
                                .add("summary", "Test issue")
                                .add("description", "Test Issue")
                                .add("issuetype",
                                        Json.createObjectBuilder().add("name", "Test"))
                ).build();

        return createIssue.toString();
    }


    public static void zephyr() {
        try {
            URL url = new URL("http://192.168.0.116:8080/jira/rest/zapi/latest/teststep/10124/");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            String login = "janbnoir";
            String password = "xep624";
            String loginPassword = login + ":" + password;

            byte[] encoded = Base64.encode(loginPassword.getBytes());
            String credentials = new String(encoded);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + credentials);
            conn.setRequestProperty("Content-Type", "application/json");
            String encodedData = getBodyForZephyr();
            conn.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));
            conn.getOutputStream().write(encodedData.getBytes());
            System.out.println(getBodyForZephyr());
            int responseCode = conn.getResponseCode();

            System.out.println(responseCode);
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                System.out.println(inputLine);
                in.close();
            } catch (IOException e) {
                System.out.println(e.getMessage() + " " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getBodyForZephyr() {
        JsonObject createSteps = Json.createObjectBuilder()
                .add("step", "Check for schedule count")
                .add("result", "Count should be equal to schedules returned by this filter.")
                 .add("data", "filter id")
                .build();

        return createSteps.toString();
    }

    public static void main(String[] args) {
      // jiraSendRequest();
        zephyr();
    }
}
