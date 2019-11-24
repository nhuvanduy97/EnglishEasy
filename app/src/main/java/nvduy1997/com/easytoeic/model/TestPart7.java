package nvduy1997.com.easytoeic.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestPart7 implements Parcelable {
    @SerializedName("idTest")
    @Expose
    private String idTest;
    @SerializedName("tenTest")
    @Expose
    private String tenTest;
    @SerializedName("contentsTest")
    @Expose
    private String contentsTest;

    protected TestPart7(Parcel in) {
        idTest = in.readString();
        tenTest = in.readString();
        contentsTest = in.readString();
    }

    public static final Creator<TestPart7> CREATOR = new Creator<TestPart7>() {
        @Override
        public TestPart7 createFromParcel(Parcel in) {
            return new TestPart7(in);
        }

        @Override
        public TestPart7[] newArray(int size) {
            return new TestPart7[size];
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

    public String getContentsTest() {
        return contentsTest;
    }

    public void setContentsTest(String contentsTest) {
        this.contentsTest = contentsTest;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idTest);
        dest.writeString(tenTest);
        dest.writeString(contentsTest);
    }
}
