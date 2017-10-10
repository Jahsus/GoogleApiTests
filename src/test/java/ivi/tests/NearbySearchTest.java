package ivi.tests;

import com.google.gson.Gson;
import ivi.tests.models.NearbyResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NearbySearchTest {

    private HttpStatus status;



    public String name = "&name=King";
    public String baseUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    public String location = "location=55.9345801,37.4959949";
    public String radius = "&radius=500";
    public String key = "&key=AIzaSyByNV-ywpGFBhAiAlJAAOGZftpSvdB-rs8";



    OkHttpClient client = new OkHttpClient();

    public NearbyResponse execute(String url) {
                Request request = new Request.Builder()
                .url(url)
                .build();
        try {

            Response response = client.newCall(request).execute();
            assertTrue(response.isSuccessful() && response.body() != null);
            NearbyResponse nearbyResponse = new Gson().fromJson(response.body().string(), NearbyResponse.class);
            return nearbyResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Test
    public void searchByLocation() {
        NearbyResponse nearbyResponse = execute(baseUrl+location+radius+key);
        assertNotNull(nearbyResponse);
        System.out.println(nearbyResponse.toString());

    }

    @Test public void searchByName() {
        NearbyResponse nearbyResponse = execute(baseUrl+location+radius+key+name);
        assertNotNull(nearbyResponse);
        System.out.println(nearbyResponse.toString());
        for(int i = 0; i < nearbyResponse.mResults.length; i++) {
            assertTrue(nearbyResponse.mResults[i].name.contains(name));
        }
    }


}