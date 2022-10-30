package practice;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;

public class APIs {

  public static void main(String[] args) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Washington%20DC/2021-01-01/2021-12-31?unitGroup=metric&elements=datetime%2Chumidity&include=days&key=PQ78YCQRQDMNXBS3MAEPC72AB&contentType=csv"))
            .method("GET", HttpRequest.BodyPublishers.noBody()).build();
    HttpResponse response = HttpClient.newHttpClient()
            .send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.body());
  }
}
