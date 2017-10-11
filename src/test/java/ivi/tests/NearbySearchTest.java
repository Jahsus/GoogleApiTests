package ivi.tests;

import com.google.gson.Gson;
import ivi.tests.models.NearbyResponse;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import static org.junit.Assert.*;

public class NearbySearchTest {

    public String scheme = "https";
    public String host = "maps.googleapis.com";
    public String path = "/maps/api/place/nearbysearch/json";
    public String location = "55.9345801,37.4959949";
    public String radius = "2500";
    public String radiusMin = "1";
    public String radiusMax = "50000";
    public String key = "AIzaSyByNV-ywpGFBhAiAlJAAOGZftpSvdB-rs8";
    public String name = "King";
    public String keyword = "конфитюр";
    public String typeFood = "food";
    public String typeSpa = "spa";


    OkHttpClient client = new OkHttpClient();

    public URL buildUrl(Pair... args) {
        HttpUrl.Builder builder = new HttpUrl.Builder()
                .scheme(scheme)
                .host(host)
                .addPathSegments(path);
        for (Pair arg : args) {
            builder.addQueryParameter(arg.getName(), arg.getValue());
        }
        return builder.build().url();
    }

    public NearbyResponse execute(URL url) {
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

    public void checkStatus(NearbyResponse response) {
        assertEquals("OK", response.mStatus);
    }


    @Test
    public void searchByLocation() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location)
                , new Pair("radius", radius)
                , new Pair("key", key)));
        assertNotNull(nearbyResponse);
        System.out.println(nearbyResponse.toString());
    }

    @Test
    public void searchByName() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location)
                , new Pair("radius", radius)
                , new Pair("key", key)
                , new Pair("name", name)));
        assertNotNull(nearbyResponse);
        System.out.println(nearbyResponse.toString());
        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            assertTrue(nearbyResponse.mResults[i].name.contains(name));
        }
    }

    @Test
    public void searchWithoutLocation() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("radius", radius)
                , new Pair("key", key)));
        assertNotNull(nearbyResponse);
        System.out.println(nearbyResponse.toString());
    }

    @Test
    public void searchWithoutRadius() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location)
                , new Pair("key", key)
                , new Pair("name", name)));
        assertNotNull(nearbyResponse);
        System.out.println(nearbyResponse.toString());
    }

    @Test
    public void searchWithoutKey() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location)
                , new Pair("radius", radius)));

        assertNotNull(nearbyResponse);
        System.out.println(nearbyResponse.toString());
    }


    //TODO нужно разобраться, почему в результаты поиска попадают объекты которые не содержат результаты поиска
    //TODO (или почему не выводятся поля которые эти данные содержат)
    @Test
    public void searchByKeyword() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location)
                , new Pair("radius", radius)
                , new Pair("key", key)
                , new Pair("keyword", keyword)));
        assertNotNull(nearbyResponse);
        System.out.println(nearbyResponse.toString());
        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            assertTrue(nearbyResponse.mResults[i].name.toLowerCase().contains(keyword) ||
                    nearbyResponse.mResults[i].vicinity.toLowerCase().contains(keyword));
        }

    }

    @Test
    public void searchByMinimumRadius() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location)
                , new Pair("radius", radiusMin)
                , new Pair("key", key)
                , new Pair("keyword", keyword)));
        assertEquals("ZERO_RESULTS", nearbyResponse.mStatus);
        System.out.println(nearbyResponse.toString());
    }

    @Test
    public void searchByMaximumRadius() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location)
                , new Pair("radius", radiusMax)
                , new Pair("key", key)
                , new Pair("name", name)));
        assertEquals("OK", nearbyResponse.mStatus);
        // System.out.println(nearbyResponse.toString()); для отладки
        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            assertTrue(nearbyResponse.mResults[i].name.toLowerCase().contains(name));
        }
        System.out.println(nearbyResponse.toString());

    }

    @Test
    public void searchByType() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location)
                , new Pair("radius", radius)
                , new Pair("key", key)
                , new Pair("type", typeFood)));
        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            System.out.println(nearbyResponse.mResults[i].toString());
            Arrays.sort(nearbyResponse.mResults[i].types);
            assertTrue(Arrays.binarySearch(nearbyResponse.mResults[i].types, typeFood) >= 0);
        }
        System.out.println(nearbyResponse.toString());
    }

    @Test
    public void searchByTypes() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location)
                , new Pair("radius", radius)
                , new Pair("key", key)
                , new Pair("types", typeFood + "|" + typeSpa)));
        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            System.out.println(nearbyResponse.mResults[i].toString());
            Arrays.sort(nearbyResponse.mResults[i].types);
            assertTrue((Arrays.binarySearch(nearbyResponse.mResults[i].types, typeFood) >= 0)
                    || Arrays.binarySearch(nearbyResponse.mResults[i].types, typeSpa) >= 0);
        }
        System.out.println(nearbyResponse.toString());
    }

    @Test
    public void searchByOpenNowStatus() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location)
                , new Pair("radius", radius)
                , new Pair("key", key)
                , new Pair("opennow", "true")));
        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            assertTrue(nearbyResponse.mResults[i].opening_hours.open_now);
        }
    }

    @Test
    public void searchNextPageToken() {

        String requestUrl = buildUrl(new Pair("location", location)
                                    , new Pair("radius", radius)
                                    , new Pair("key", key)
                                    , new Pair("pagetoken", null)).toString();

        System.out.println(requestUrl);

        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location)
                , new Pair("radius", radius)
                , new Pair("key", key)
                , new Pair("pagetoken", null)));

        String nextPageToken=nearbyResponse.mToken.toString();
        assertNotNull(nextPageToken);

        System.out.println(nearbyResponse);
    }
}