package classfour.socket.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpClientDemo {

    public static void main(String[] args){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8803");
        for (int i = 0; i < 10; i++) {
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    String content = new BufferedReader(new InputStreamReader(response.getEntity().getContent())).readLine();
                    System.out.println("content:" + content);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
