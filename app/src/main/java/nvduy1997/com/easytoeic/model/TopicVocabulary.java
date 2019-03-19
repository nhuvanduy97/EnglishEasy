package nvduy1997.com.easytoeic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TopicVocabulary implements Serializable {

    @SerializedName("idchude")
    @Expose
    private String idchude;
    @SerializedName("tenchude")
    @Expose
    private String tenchude;
    @SerializedName("hinhchude")
    @Expose
    private String hinhchude;

    public String getIdchude() {
        return idchude;
    }

    public void setIdchude(String idchude) {
        this.idchude = idchude;
    }

    public String getTenchude() {
        return tenchude;
    }

    public void setTenchude(String tenchude) {
        this.tenchude = tenchude;
    }

    public String getHinhchude() {
        return hinhchude;
    }

    public void setHinhchude(String hinhchude) {
        this.hinhchude = hinhchude;
    }

}