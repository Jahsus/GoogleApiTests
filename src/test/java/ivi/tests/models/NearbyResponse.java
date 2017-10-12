package ivi.tests.models;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by vk on 10.10.17.
 */
public class NearbyResponse {


    @SerializedName("results")
    public Result[] mResults;
    @SerializedName("status")
    public String mStatus;
    @SerializedName("next_page_token")
    public String mToken;

    @Override
    public String toString() {
        return "NearbyResponse{" +
                "mResults=" + Arrays.toString(mResults) +
                ", mStatus='" + mStatus + '\'' +
                ", mToken='" + mToken + '\'' +
                '}';
    }


}
