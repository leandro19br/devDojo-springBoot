package br.com.devdojo.javaClient;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URL;
/**
 * consumindo uma API com java puro como Client
 */
public class JavaClientTest {
    public static void main(String[] args) {

        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        String user = "lcsantiago";
        String pass = "Santiago";

        try {

            URL url = new URL("http://localhost:8080/v1/protected/students/2");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Authorization","Basic" + encondeUserNamePass(user,pass));
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder jsonSb = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {

                jsonSb.append(line);

            }
            System.out.println(jsonSb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(bufferedReader);
            if (connection != null) {
                connection.disconnect();
            }
        }

    }

    private static String encondeUserNamePass(String user,String pass){
        String userPass = user + ":" + pass;
        return new String(Base64.encodeBase64(userPass.getBytes()));
    }
}
