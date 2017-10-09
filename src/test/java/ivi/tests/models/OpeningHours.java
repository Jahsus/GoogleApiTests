
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
        "open_now",
        "weekday_text"
})
public class OpeningHours {

    @JsonProperty("open_now")
    private Boolean openNow;
    @JsonProperty("weekday_text")
    private List<Object> weekdayText = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public OpeningHours() {
    }

    /**
     *
     * @param weekdayText
     * @param openNow
     */
    public OpeningHours(Boolean openNow, List<Object> weekdayText) {
        super();
        this.openNow = openNow;
        this.weekdayText = weekdayText;
    }

    @JsonProperty("open_now")
    public Boolean getOpenNow() {
        return openNow;
    }

    @JsonProperty("open_now")
    public void setOpenNow(Boolean openNow) {
        this.openNow = openNow;
    }

    public OpeningHours withOpenNow(Boolean openNow) {
        this.openNow = openNow;
        return this;
    }

    @JsonProperty("weekday_text")
    public List<Object> getWeekdayText() {
        return weekdayText;
    }

    @JsonProperty("weekday_text")
    public void setWeekdayText(List<Object> weekdayText) {
        this.weekdayText = weekdayText;
    }

    public OpeningHours withWeekdayText(List<Object> weekdayText) {
        this.weekdayText = weekdayText;
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

    public OpeningHours withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("openNow", openNow).append("weekdayText", weekdayText).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(weekdayText).append(additionalProperties).append(openNow).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OpeningHours) == false) {
            return false;
        }
        OpeningHours rhs = ((OpeningHours) other);
        return new EqualsBuilder().append(weekdayText, rhs.weekdayText).append(additionalProperties, rhs.additionalProperties).append(openNow, rhs.openNow).isEquals();
    }

}