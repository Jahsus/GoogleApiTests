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

    public String location = "55.7696936,37.6064516"; //Tverskaya
    //public String location = "55.9345801,37.4959949"; // Dolgoprudniy
    public String radius = "25000";
    public String radiusMin = "1";
    public String radiusMax = "50000";
    public String key = "AIzaSyByNV-ywpGFBhAiAlJAAOGZftpSvdB-rs8";

    public String invalidKey = "AIzaSyByNV-ywpAFBhAiAlJAAOGZftpSvdB-rs8";
    public String invalidLocation = "155.7696936,37.6064516";
    public String invalidRadius = "-2500";

    public String name = "king";
    public String keyword = "галлери";
    public String typeFood = "food";
    public String typeSpa = "spa";
    public String minPrice = "3";
    public String maxPrice = "4";
    public String langEs = "es";


    OkHttpClient client = new OkHttpClient();


    // Собирает URL-запроса протокола,имени хоста, путь к ресурсу и из пар "имя" и "значение" параметров
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


    //Выполняет URL-запроса возвращенного методом buildUrl()
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


    /*
    Метод вызывается первым почти во всех тестах где проверяются результаты поиска.
    Проверяет что тело ответа не пустое и статус ОК
     */

    public void checkStatus(NearbyResponse response) {
        assertNotNull(response);
        assertEquals("OK", response.mStatus);
    }


    // Тесты с неверными входными параметрами


    /*
       Тестовый сценарий: Отправить запрос с пустым значением ключа, валидным названием места, координатами, радиусом
       Ожидаемый результат: статус ответа: "REQUEST_DENIED"
       */
    @Test
    public void searchByEmptyKey() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", " "),
                new Pair("name", name)));

        assertEquals("REQUEST_DENIED", nearbyResponse.mStatus);

    }
    /*
    Тестовый сценарий: Отправить запрос с невалидным значением ключа, валидным названием места, координатами, радиусом
    Ожидаемый результат: статус ответа: "REQUEST_DENIED"
    */
    @Test
    public void searchByInvalidKey() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", invalidKey),
                new Pair("name", name)));

        assertEquals("REQUEST_DENIED", nearbyResponse.mStatus);

    }

    /*
    Тестовый сценарий: Отправить запрос с пустым значением name, валидным ключем, координатами, радиусом
    Ожидаемый результат: статус ответа: "ZERO_RESULTS"
    */
    @Test
    public void searchByEmptyName() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", key),
                new Pair("name", " ")));

        assertEquals("ZERO_RESULTS", nearbyResponse.mStatus);
    }

    /*
   Тестовый сценарий: Отправить запрос со значением name = "1=1; select * from users; " (дли имитации SQL -запроса), валидным ключем, координатами, радиусом
   Ожидаемый результат: статус ответа: "ZERO_RESULTS"
   */
    @Test
    public void searchByNameSqlInject() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", key),
                new Pair("name", "1=1; select * from users; ")));
        System.out.println(nearbyResponse);
        assertEquals("ZERO_RESULTS", nearbyResponse.mStatus);

    }


    /*
    Тестовый сценарий: Отправить запрос с пустым значением координат, валидными значениями ключа, названием места, радиусом
    Ожидаемый результат: статус ответа: "INVALID_REQUEST"
    */
    @Test
    public void searchByEmptyLocation() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", " "),
                new Pair("radius", radius),
                new Pair("key", key)));

        assertEquals("INVALID_REQUEST", nearbyResponse.mStatus);
    }

    /*
    Тестовый сценарий: Отправить запрос с невалидными координатами, валидными значениями ключа, названием места, радиусом
    Ожидаемый результат: статус ответа: "INVALID_REQUEST"
    */
    @Test
    public void searchByInvalidLocation() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", invalidLocation),
                new Pair("radius", radius),
                new Pair("key", key)));

        assertEquals("INVALID_REQUEST", nearbyResponse.mStatus);
    }

    /*
      Тестовый сценарий: Отправить запрос с невалидными пустым значением радиуса и верными координатами, валидными значениями ключа, названием места,
      Ожидаемый результат: статус ответа:  "INVALID_REQUEST"
      */
    @Test
    public void searchByEmptyRadius() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", " "),
                new Pair("key", key),
                new Pair("name", name)));

        assertEquals("INVALID_REQUEST", nearbyResponse.mStatus);
    }

    /*
    Тестовый сценарий: Отправить запрос с невалидными значением  радиуса и верными координатами, валидными значениями ключа, названием места,
    Ожидаемый результат: статус ответа:  "INVALID_REQUEST"
    */
    @Test
    public void searchByInvalidRadius() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", invalidRadius),
                new Pair("key", key),
                new Pair("name", name)));

        assertEquals("INVALID_REQUEST", nearbyResponse.mStatus);
    }


   /*
   Тестовый сценарий: Отправить запрос без координат
   Ожидаемый результат: статус ответа:  "INVALID_REQUEST"
   */
    @Test
    public void searchWithoutLocation() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("radius", radius),
                new Pair("key", key)));

        assertEquals("INVALID_REQUEST", nearbyResponse.mStatus);
    }

   /*
   Тестовый сценарий: Отправить запрос без переметра радиус
   Ожидаемый результат: статус ответа:  "INVALID_REQUEST"
   */
    @Test
    public void searchWithoutRadius() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("key", key),
                new Pair("name", name)));

        assertEquals("INVALID_REQUEST", nearbyResponse.mStatus);

    }

   /*
   Тестовый сценарий: Отправить запрос без ключа приложения
   Ожидаемый результат: статус ответа:  "REQUEST_DENIED"
   */
    @Test
    public void searchWithoutKey() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius)));

        assertEquals("REQUEST_DENIED", nearbyResponse.mStatus);

    }


   /*
   Тестовый сценарий: Отправить, указав в одном запросе, параметры rankedby и radius
   Ожидаемый результат: статус ответа:  "INVALID_REQUEST"
   */
    @Test
    public void searchByRankbyAndRadius() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", key),
                new Pair("name", name),
                new Pair("rankby", "distance")));

        assertEquals("INVALID_REQUEST", nearbyResponse.mStatus);


    }


    // Проверка запроса со всеми параметрам

  /*
  Тестовый сценарий: Отправить, указав в одном запросе, параметры все оязательные и необязательные поля (почти все).
  Ожидаемый результат: статус ответа: "ZERO_RESULTS" или (что маловероятно) "OK".
  */
    @Test
    public void searchByAllParameters() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radiusMax),
                new Pair("key", key),
                new Pair("keyword", keyword),
                new Pair("name", name),
                new Pair("type", typeFood),
                new Pair("minprice", minPrice),
                new Pair("maxprice", maxPrice),
                new Pair("language", langEs)));
        //System.out.println(nearbyResponse);

        assertTrue(nearbyResponse.mStatus.equals("ZERO_RESULTS") || nearbyResponse.mStatus.equals("OK"));

    }

  /*
  Тестовый сценарий: Отправить запрос с только обязательными полями key, location, radius
  Ожидаемый результат: статус ответа: статус "OK", в результате получены любые места в радиусе от данной точки
  */

    @Test
    public void searchByRequiredParameters() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", key)));

        checkStatus(nearbyResponse);
       // System.out.println(nearbyResponse.toString());
    }

    /*
     Тестовый сценарий: Отправить запрос параметром rankby=prominence, для сортировки результатов по убыванию рейтинга
     Ожидаемый результат: статус ответа: статус "OK", результаты отсортированы по популярности
     Фактический результат: статус: INVALID_REQUEST, пока не понял почему (пробовал параметр rankby и rankby=prominence)
    */
    //TODO Вроде должно работать, но в ответе "неверный запрос" нужно разбираться почему так.

    @Test
    public void searchByRankbyProminence() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("rankby", "prominence"),
                new Pair("key", key),
                new Pair("name", name)));

        checkStatus(nearbyResponse);
       // System.out.println(nearbyResponse.toString());

        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            assertTrue(nearbyResponse.mResults[i].name.toLowerCase().contains(name));
        }
    }

     /*
     Тестовый сценарий: Отправить запрос параметром rankby=distance, для сортировки результатов по убыванию
     удаленности от заданой точки
     Ожидаемый результат: статус ответа: статус "OK", результаты отсортированы по расстоянию
     */
    @Test
    public void searchByRankbyDistance() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("rankby", "distance"),
                new Pair("key", key),
                new Pair("name", name)));

        checkStatus(nearbyResponse);
       // System.out.println(nearbyResponse.toString());

        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            assertTrue(nearbyResponse.mResults[i].name.toLowerCase().contains(name));
        }
    }

    /*
    Тестовый сценарий: Отправить запрос c параметром имени = King, сравнить что в резултатах поле name содержит строку "king"
    Ожидаемый результат: статус ответа: статус "OK", во всех результатах поиска в поле name содиржится значение "king"
    */
    @Test
    public void searchByName() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", key),
                new Pair("name", name)));

        checkStatus(nearbyResponse);
       // System.out.println(nearbyResponse.toString());

        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            assertTrue(nearbyResponse.mResults[i].name.toLowerCase().contains(name));
        }
    }



         /*
         Тестовый сценарий: Отправить запрос параметром keyword=галлери, для сортировки результатов по убыванию
         удаленности от заданой точки
         Ожидаемый результат: статус ответа: статус "OK", результаты в поле name, vicinity или type ,
         (а так же отзывах и стороннем контенте, который API не отдает).
         Фактический результат: в результате в выдачу попадают объекты в которые данный кейворд не передается,
         поэто проверить очень будет проблематично, тем более API может отдать "Galaks" вместо "Галакс", поэтому проверить
         совпадение строки видится проблематичным :/
          */
    //TODO нужно разобраться, почему в результаты поиска попадают объекты которые не содержат результаты поиска
    //TODO (или почему не выводятся поля которые эти данные содержат), подозреваю что keyword содержится где-то в отзывах
    @Test
    public void searchByKeyword() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", key),
                new Pair("keyword", keyword)));

        assertNotNull(nearbyResponse);
      //  System.out.println(nearbyResponse.toString());

        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            assertTrue(nearbyResponse.mResults[i].name.toLowerCase().contains(keyword) ||
                    nearbyResponse.mResults[i].vicinity.toLowerCase().contains(keyword));
        }

    }


         /*
         Тестовый сценарий: Отправить запрос c параметром минимальным допустимым расстоянием радиуса = 1м от заданой точки

         Ожидаемый результат: статус ответа: Результаты не найдены, статус "ZERO_RESULTS", либо( что маловероянтно
         будут найдены результаты и статус код = OK
          */

    @Test
    public void searchByMinimumRadius() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radiusMin),
                new Pair("key", key),
                new Pair("keyword", keyword)));

        assertTrue(nearbyResponse.mStatus.equals("ZERO_RESULTS") || nearbyResponse.mStatus.equals("OK"));
      //  System.out.println(nearbyResponse.toString());
    }


         /*
         Тестовый сценарий: Найти места в радиусе = 50000м от заданой точки и именем 'king"
         Ожидаемый результат: статус ответа: Результаты найдены, статус "OK",
         */
    @Test
    public void searchByMaximumRadius() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radiusMax),
                new Pair("key", key),
                new Pair("name", name)));
        checkStatus(nearbyResponse);
        // System.out.println(nearbyResponse.toString()); для отладки

        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            assertTrue(nearbyResponse.mResults[i].name.toLowerCase().contains(name));
        }
     //   System.out.println(nearbyResponse.toString());

    }




         /*
         Тестовый сценарий: Найти места у которых тип = Food
         Ожидаемый результат: Результаты найдены, статус "OK" или ZERO_RESULTS, если ОК - можно проверять что в массиве Type есть элемент "Food"
         */


    @Test
    public void searchByType() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", key),
                new Pair("type", typeFood)));

        assertTrue(nearbyResponse.mStatus.equals("OK") || nearbyResponse.mStatus.equals("ZERO_RESULTS")); // Сначала стоит проверить что статус ОК или ZERO_RESULTS

        if (nearbyResponse.mStatus.equals("OK")) {
            for (int i = 0; i < nearbyResponse.mResults.length; i++) {
                System.out.println(nearbyResponse.mResults[i].toString());
                Arrays.sort(nearbyResponse.mResults[i].types); // Чтобы выполнить двоичный поиск нужно сначала отсортировать массив
                assertTrue(Arrays.binarySearch(nearbyResponse.mResults[i].types, typeFood) >= 0);
            }
        } else
            System.out.println(nearbyResponse.mResults.toString() + " результаты не найдены");
        //System.out.println(nearbyResponse.toString());
    }


         /*
         Тестовый сценарий: Найти места у которых тип = Food или Spa
         Ожидаемый результат: Результаты найдены, статус "OK" или ZERO_RESULTS, если ОК - можно проверять что в массиве Type есть элемент "Food"
         */
    @Test
    public void searchByTypes() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", key),
                new Pair("types", typeFood + "|" + typeSpa)));
        assertTrue(nearbyResponse.mStatus.equals("OK") || nearbyResponse.mStatus.equals("ZERO_RESULTS")); // Сначала стоит проверить что статус ОК или ZERO_RESULTS

        if (nearbyResponse.mStatus.equals("OK")) {
            for (int i = 0; i < nearbyResponse.mResults.length; i++) {
                System.out.println(nearbyResponse.mResults[i].toString());
                Arrays.sort(nearbyResponse.mResults[i].types);
                assertTrue((Arrays.binarySearch(nearbyResponse.mResults[i].types, typeFood) >= 0)
                        || Arrays.binarySearch(nearbyResponse.mResults[i].types, typeSpa) >= 0);
            }
        } else
            System.out.println(nearbyResponse.mResults.toString() + " результаты не найдены");

        //System.out.println(nearbyResponse.toString());
    }

         /*
         Тестовый сценарий: Найти места которые открыты сейчас
         Ожидаемый результат: Результаты найдены, у всех мест значение поля open_now должно быть true
         */


    @Test
    public void searchByOpenNowStatus() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", key),
                new Pair("opennow", null)));

        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            assertTrue(nearbyResponse.mResults[i].opening_hours.open_now);
        }
    }


    @Test
    public void searchNextPageToken() {

        /* Проверял как показать запрос для отладки
        String requestUrl = buildUrl(new Pair("location", location),
                                     new Pair("radius", radius),
                                     new Pair("key", key),
                                     new Pair("pagetoken", null)).toString();
        System.out.println(requestUrl);
        */

        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", key),
                new Pair("pagetoken", null)));

        String nextPageToken = nearbyResponse.mToken.toString();
        assertNotNull(nextPageToken);

        System.out.println(nearbyResponse);
    }

     /*
     Тестовый сценарий: Отправить запрос с minprice = 3
     Ожидаемый результат: Результаты найдены, у всех мест значение поля price_level должно быть больше или равно 3
     */
    @Test
    public void searchByMinPrice() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", key),
                new Pair("minprice", minPrice)));

        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            assertTrue(nearbyResponse.mResults[i].price_level >= 3);
        }
    }

    /*
    Тестовый сценарий: Отправить запрос с minprice = 4 и maxprice 4
    Ожидаемый результат: Результаты найдены, у всех мест значение поля price_level должно быть равно 4
    */
    @Test
    public void searchByMinAndMaxPrice() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", key),
                new Pair("minprice", maxPrice),
                new Pair("maxprice", maxPrice)));

        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            assertTrue(nearbyResponse.mResults[i].price_level == 4);
        }
    }


    /*
     Тестовый сценарий: Отправить запрос с language = es (испанский)
     Ожидаемый результат: Ответ не пустой, статус ОК
    */
    @Test
    public void searchNameByLanguage() {
        NearbyResponse nearbyResponse = execute(buildUrl(new Pair("location", location),
                new Pair("radius", radius),
                new Pair("key", key),
                new Pair("name", name),
                new Pair("language", langEs)));

        checkStatus(nearbyResponse);
        for (int i = 0; i < nearbyResponse.mResults.length; i++) {
            assertTrue(nearbyResponse.mResults[i].name.toLowerCase().contains(name));
        }
    }
}