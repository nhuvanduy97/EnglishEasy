package nvduy1997.com.easytoeic.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailGrammar implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cachsh")
    @Expose
    private String cachsh;
    @SerializedName("dinhnghia")
    @Expose
    private String dinhnghia;
    @SerializedName("idgrammar")
    @Expose
    private String idgrammar;
    @SerializedName("vidu")
    @Expose
    private String vidu;
    @SerializedName("cautruc")
    @Expose
    private String cautruc;

    protected DetailGrammar(Parcel in) {
        id = in.readString();
        cachsh = in.readString();
        dinhnghia = in.readString();
        idgrammar = in.readString();
        vidu = in.readString();
        cautruc = in.readString();
    }

    public static final Creator<DetailGrammar> CREATOR = new Creator<DetailGrammar>() {
        @Override
        public DetailGrammar createFromParcel(Parcel in) {
            return new DetailGrammar(in);
        }

        @Override
        public DetailGrammar[] newArray(int size) {
            return new DetailGrammar[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCachsh() {
        return cachsh;
    }

    public void setCachsh(String cachsh) {
        this.cachsh = cachsh;
    }

    public String getDinhnghia() {
        return dinhnghia;
    }

    public void setDinhnghia(String dinhnghia) {
        this.dinhnghia = dinhnghia;
    }

    public String getIdgrammar() {
        return idgrammar;
    }

    public void setIdgrammar(String idgrammar) {
        this.idgrammar = idgrammar;
    }

    public String getVidu() {
        return vidu;
    }

    public void setVidu(String vidu) {
        this.vidu = vidu;
    }

    public String getCautruc() {
        return cautruc;
    }

    public void setCautruc(String cautruc) {
        this.cautruc = cautruc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(cachsh);
        dest.writeString(dinhnghia);
        dest.writeString(idgrammar);
        dest.writeString(vidu);
        dest.writeString(cautruc);
    }
}