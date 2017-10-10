package ivi.tests.models;

/**
 * Created by vk on 10.10.17.
 */
public class Photo {
    public int height;
    public int width;
    public String photo_reference;

    @Override
    public String toString() {
        return "Photo{" +
                "height=" + height +
                ", width=" + width +
                ", photo_reference='" + photo_reference + '\'' +
                '}';
    }
}
