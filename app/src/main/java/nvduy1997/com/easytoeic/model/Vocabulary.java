package nvduy1997.com.easytoeic.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vocabulary implements Serializable {
    @SerializedName("idtuvung")
    @Expose
    private String idtuvung;
    @SerializedName("hinhtuvung")
    @Expose
    private String hinhtuvung;
    @SerializedName("tuenglish")
    @Expose
    private String tuenglish;
    @SerializedName("tuvietnam")
    @Expose
    private String tuvietnam;
    @SerializedName("vdenglish")
    @Expose
    private String vdenglish;
    @SerializedName("vdvietnam")
    @Expose
    private String vdvietnam;
    @SerializedName("idchude")
    @Expose
    private String idchude;

    public String getIdtuvung() {
        return idtuvung;
    }

    public void setIdtuvung(String idtuvung) {
        this.idtuvung = idtuvung;
    }

    public String getHinhtuvung() {
        return hinhtuvung;
    }

    public void setHinhtuvung(String hinhtuvung) {
        this.hinhtuvung = hinhtuvung;
    }

    public String getTuenglish() {
        return tuenglish;
    }

    public void setTuenglish(String tuenglish) {
        this.tuenglish = tuenglish;
    }

    public String getTuvietnam() {
        return tuvietnam;
    }

    public void setTuvietnam(String tuvietnam) {
        this.tuvietnam = tuvietnam;
    }

    public String getVdenglish() {
        return vdenglish;
    }

    public void setVdenglish(String vdenglish) {
        this.vdenglish = vdenglish;
    }

    public String getVdvietnam() {
        return vdvietnam;
    }

    public void setVdvietnam(String vdvietnam) {
        this.vdvietnam = vdvietnam;
    }

    public String getIdchude() {
        return idchude;
    }

    public void setIdchude(String idchude) {
        this.idchude = idchude;
    }
}
