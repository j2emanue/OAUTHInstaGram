
package gson.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Videos {

    @SerializedName("low_bandwidth")
    @Expose
    private LowBandwidth lowBandwidth;
    @SerializedName("standard_resolution")
    @Expose
    private StandardResolution_ standardResolution;
    @SerializedName("low_resolution")
    @Expose
    private LowResolution_ lowResolution;

    /**
     * 
     * @return
     *     The lowBandwidth
     */
    public LowBandwidth getLowBandwidth() {
        return lowBandwidth;
    }

    /**
     * 
     * @param lowBandwidth
     *     The low_bandwidth
     */
    public void setLowBandwidth(LowBandwidth lowBandwidth) {
        this.lowBandwidth = lowBandwidth;
    }

    /**
     * 
     * @return
     *     The standardResolution
     */
    public StandardResolution_ getStandardResolution() {
        return standardResolution;
    }

    /**
     * 
     * @param standardResolution
     *     The standard_resolution
     */
    public void setStandardResolution(StandardResolution_ standardResolution) {
        this.standardResolution = standardResolution;
    }

    /**
     * 
     * @return
     *     The lowResolution
     */
    public LowResolution_ getLowResolution() {
        return lowResolution;
    }

    /**
     * 
     * @param lowResolution
     *     The low_resolution
     */
    public void setLowResolution(LowResolution_ lowResolution) {
        this.lowResolution = lowResolution;
    }

}
