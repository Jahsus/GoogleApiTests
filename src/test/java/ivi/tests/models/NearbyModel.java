package ivi.tests.models;

public class NearbyModel {

    private Float lat;
    private Float lng;
    private String icon;
    private String id;
    private String name;
    private Boolean open_now;
    private Integer height;
    private Integer width;
    private String photo_reference;
    private String place_id;
    private Integer price_level;
    private Float rating;
    private String reference;
    private String scope;
    private String vicinity;

public NearbyModel(){

}

    public NearbyModel(Float lat, Float lng, String icon, String id, String name, Boolean open_now, Integer height, Integer width, String photo_reference, String place_id, Integer price_level, Float rating, String reference, String scope, String vicinity) {
        this.lat = lat;
        this.lng = lng;
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.open_now = open_now;
        this.height = height;
        this.width = width;
        this.photo_reference = photo_reference;
        this.place_id = place_id;
        this.price_level = price_level;
        this.rating = rating;
        this.reference = reference;
        this.scope = scope;
        this.vicinity = vicinity;
    }

    @Override
    public String toString() {
        return "NearbyModel{" +
                "lat=" + lat +
                ", lng=" + lng +
                ", icon='" + icon + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", open_now=" + open_now +
                ", height=" + height +
                ", width=" + width +
                ", photo_reference='" + photo_reference + '\'' +
                ", place_id='" + place_id + '\'' +
                ", price_level=" + price_level +
                ", rating=" + rating +
                ", reference='" + reference + '\'' +
                ", scope='" + scope + '\'' +
                ", vicinity='" + vicinity + '\'' +
                '}';
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpen_now() {
        return open_now;
    }

    public void setOpen_now(Boolean open_now) {
        this.open_now = open_now;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public Integer getPrice_level() {
        return price_level;
    }

    public void setPrice_level(Integer price_level) {
        this.price_level = price_level;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
}
