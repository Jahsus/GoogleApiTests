package ivi.tests.models;

/**
 * Created by vk on 10.10.17.
 */
public class Result {

    public Geometry geometry;
    public String icon;
    public String id;
    public String name;
    public OpeningHours opening_hours;
    public Photo [] photos;
    public String place_id;
    public int price_level;
    public float rating;
    public String reference;
    public String scope;
    public String vicinity;

    @Override
    public String toString() {
        return "Result{" +
                "geometry=" + geometry +
                ", icon='" + icon + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", opening_hours=" + opening_hours +
                ", photos=" + photos +
                ", place_id='" + place_id + '\'' +
                ", price_level=" + price_level +
                ", rating=" + rating +
                ", reference='" + reference + '\'' +
                ", scope='" + scope + '\'' +
                ", vicinity='" + vicinity + '\'' +
                '}';
    }
}
