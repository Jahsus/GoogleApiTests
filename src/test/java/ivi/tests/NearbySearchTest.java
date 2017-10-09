package ivi.tests;

import ivi.tests.models.NearbyModel;
import ivi.tests.models.NearbySearch;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.web.client.RestTemplate;

public class NearbySearchTest {

    public String key = "AIzaSyByNV-ywpGFBhAiAlJAAOGZftpSvdB-rs8";
    public String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/";
    public String request = "https://maps.googleapis.com/maps/api/place/nearbysearch" +
            "/json?location=55.9345801,37.4959949&radius=500&type=restaurant&key=AIzaSyByNV-ywpGFBhAiAlJAAOGZftpSvdB-rs8";

    @Test

    public void NearbySearchResponse(){

        RestTemplate restTemplate = new RestTemplate();
        NearbyModel nearbyModel = restTemplate.getForObject(request, NearbyModel.class);
        System.out.println(nearbyModel);
        assertTrue();


    }
}
