package ivi.tests.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "geometry",
        "icon",
        "id",
        "name",
        "opening_hours",
        "photos",
        "place_id",
        "price_level",
        "rating",
        "reference",
        "scope",
        "types",
        "vicinity"
})
public class NearbySearch {

    @JsonProperty("geometry")
    private Geometry geometry;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("opening_hours")
    private OpeningHours openingHours;
    @JsonProperty("photos")
    private List<Photo> photos = null;
    @JsonProperty("place_id")
    private String placeId;
    @JsonProperty("price_level")
    private Integer priceLevel;
    @JsonProperty("rating")
    private Double rating;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("types")
    private List<String> types = null;
    @JsonProperty("vicinity")
    private String vicinity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public NearbySearch() {
    }

    /**
     *
     * @param icon
     * @param priceLevel
     * @param scope
     * @param openingHours
     * @param reference
     * @param geometry
     * @param id
     * @param photos
     * @param vicinity
     * @param placeId
     * @param name
     * @param rating
     * @param types
     */
    public NearbySearch(Geometry geometry, String icon, String id, String name, OpeningHours openingHours, List<Photo> photos, String placeId, Integer priceLevel, Double rating, String reference, String scope, List<String> types, String vicinity) {
        super();
        this.geometry = geometry;
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.openingHours = openingHours;
        this.photos = photos;
        this.placeId = placeId;
        this.priceLevel = priceLevel;
        this.rating = rating;
        this.reference = reference;
        this.scope = scope;
        this.types = types;
        this.vicinity = vicinity;
    }

    @JsonProperty("geometry")
    public Geometry getGeometry() {
        return geometry;
    }

    @JsonProperty("geometry")
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public NearbySearch withGeometry(Geometry geometry) {
        this.geometry = geometry;
        return this;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    @JsonProperty("icon")
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public NearbySearch withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public NearbySearch withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public NearbySearch withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("opening_hours")
    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    @JsonProperty("opening_hours")
    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public NearbySearch withOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
        return this;
    }

    @JsonProperty("photos")
    public List<Photo> getPhotos() {
        return photos;
    }

    @JsonProperty("photos")
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public NearbySearch withPhotos(List<Photo> photos) {
        this.photos = photos;
        return this;
    }

    @JsonProperty("place_id")
    public String getPlaceId() {
        return placeId;
    }

    @JsonProperty("place_id")
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public NearbySearch withPlaceId(String placeId) {
        this.placeId = placeId;
        return this;
    }

    @JsonProperty("price_level")
    public Integer getPriceLevel() {
        return priceLevel;
    }

    @JsonProperty("price_level")
    public void setPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
    }

    public NearbySearch withPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
        return this;
    }

    @JsonProperty("rating")
    public Double getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(Double rating) {
        this.rating = rating;
    }

    public NearbySearch withRating(Double rating) {
        this.rating = rating;
        return this;
    }

    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

    public NearbySearch withReference(String reference) {
        this.reference = reference;
        return this;
    }

    @JsonProperty("scope")
    public String getScope() {
        return scope;
    }

    @JsonProperty("scope")
    public void setScope(String scope) {
        this.scope = scope;
    }

    public NearbySearch withScope(String scope) {
        this.scope = scope;
        return this;
    }

    @JsonProperty("types")
    public List<String> getTypes() {
        return types;
    }

    @JsonProperty("types")
    public void setTypes(List<String> types) {
        this.types = types;
    }

    public NearbySearch withTypes(List<String> types) {
        this.types = types;
        return this;
    }

    @JsonProperty("vicinity")
    public String getVicinity() {
        return vicinity;
    }

    @JsonProperty("vicinity")
    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public NearbySearch withVicinity(String vicinity) {
        this.vicinity = vicinity;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public NearbySearch withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("geometry", geometry).append("icon", icon).append("id", id).append("name", name).append("openingHours", openingHours).append("photos", photos).append("placeId", placeId).append("priceLevel", priceLevel).append("rating", rating).append("reference", reference).append("scope", scope).append("types", types).append("vicinity", vicinity).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(icon).append(scope).append(priceLevel).append(openingHours).append(reference).append(geometry).append(photos).append(id).append(vicinity).append(placeId).append(additionalProperties).append(name).append(rating).append(types).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NearbySearch) == false) {
            return false;
        }
        NearbySearch rhs = ((NearbySearch) other);
        return new EqualsBuilder().append(icon, rhs.icon).append(scope, rhs.scope).append(priceLevel, rhs.priceLevel).append(openingHours, rhs.openingHours).append(reference, rhs.reference).append(geometry, rhs.geometry).append(photos, rhs.photos).append(id, rhs.id).append(vicinity, rhs.vicinity).append(placeId, rhs.placeId).append(additionalProperties, rhs.additionalProperties).append(name, rhs.name).append(rating, rhs.rating).append(types, rhs.types).isEquals();
    }

}