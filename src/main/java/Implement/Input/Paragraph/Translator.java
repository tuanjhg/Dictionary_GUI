package Implement.Input.Paragraph;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.net.URLEncoder;

public class Translator {

  public static String translate(String text, String langFrom, String langTo) {
    HttpClient httpclient = HttpClients.createDefault();
    try {
      String encodedText = URLEncoder.encode(text, "UTF-8");
      String urlStr = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=" +
          langFrom + "&tl=" + langTo + "&dt=t&q=" + encodedText;

      HttpGet httpGet = new HttpGet(urlStr);
      HttpResponse response = httpclient.execute(httpGet);
      HttpEntity entity = response.getEntity();

      if (entity != null) {
        String result = EntityUtils.toString(entity);
        JSONArray jsonArray = new JSONArray(result);
        if (jsonArray != null && jsonArray.length() > 0) {
          StringBuilder translatedTextBuilder = new StringBuilder();
          JSONArray translations = jsonArray.getJSONArray(0);
          for (int i = 0; i < translations.length(); i++) {
            JSONArray translation = translations.getJSONArray(i);
            String translatedSegment = translation.getString(0);
            translatedTextBuilder.append(translatedSegment).append(" ");
          }
          return translatedTextBuilder.toString().trim();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
