package nvduy1997.com.easytoeic.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestPart1 implements Parcelable {

    @SerializedName("idTest")
    @Expose
    private String idTest;
    @SerializedName("tenTest")
    @Expose
    private String tenTest;
    @SerializedName("hinhTest")
    @Expose
    private String hinhTest;

    protected TestPart1(Parcel in) {
        idTest = in.readString();
        tenTest = in.readString();
        hinhTest = in.readString();
    }

    public static final Creator<TestPart1> CREATOR = new Creator<TestPart1>() {
        @Override
        public TestPart1 createFromParcel(Parcel in) {
            return new TestPart1(in);
        }

        @Override
        public TestPart1[] newArray(int size) {
            return new TestPart1[size];
        }
    };

    public String getIdTest() {
        return idTest;
    }

    public void setIdTest(String idTest) {
        this.idTest = idTest;
    }

    public String getTenTest() {
        return tenTest;
    }

    public void setTenTest(String tenTest) {
        this.tenTest = tenTest;
    }

    public String getHinhTest() {
        return hinhTest;
    }

    public void setHinhTest(String hinhTest) {
        this.hinhTest = hinhTest;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idTest);
        dest.writeString(tenTest);
        dest.writeString(hinhTest);
    }
}