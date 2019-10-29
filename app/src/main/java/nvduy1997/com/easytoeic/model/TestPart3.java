package nvduy1997.com.easytoeic.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestPart3 implements Parcelable {

    @SerializedName("idTest")
    @Expose
    private String idTest;
    @SerializedName("tenTest")
    @Expose
    private String tenTest;
    @SerializedName("audioTest")
    @Expose
    private String audioTest;
    protected TestPart3(Parcel in) {
    }

    public static final Creator<TestPart3> CREATOR = new Creator<TestPart3>() {
        @Override
        public TestPart3 createFromParcel(Parcel in) {
            return new TestPart3(in);
        }

        @Override
        public TestPart3[] newArray(int size) {
            return new TestPart3[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

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

    public String getAudioTest() {
        return audioTest;
    }

    public void setAudioTest(String audioTest) {
        this.audioTest = audioTest;
    }
}
