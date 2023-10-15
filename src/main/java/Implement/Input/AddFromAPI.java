package Implement.Input;

import Implement.Input.SingleWord.DictionaryEntry;
import com.google.gson.Gson;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class AddFromAPI {
  public static DictionaryEntry get(String str) {
    HttpClient httpClient = HttpClients.createDefault();
    String apiUrl = "https://api.dictionaryapi.dev/api/v2/entries/en/" + str;

    HttpGet httpGet = new HttpGet(apiUrl);

    try {
      HttpResponse response = httpClient.execute(httpGet);
      String jsonResponse = EntityUtils.toString(response.getEntity());
      Gson gson = new Gson();
      if (jsonResponse.startsWith("[")) {
        DictionaryEntry[] entries = gson.fromJson(jsonResponse, DictionaryEntry[].class);
        return entries[0];
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}