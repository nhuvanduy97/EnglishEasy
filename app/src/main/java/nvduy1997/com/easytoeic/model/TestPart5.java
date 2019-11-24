package nvduy1997.com.easytoeic.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestPart5 implements Parcelable {
    @SerializedName("idTest")
    @Expose
    private String idTest;
    @SerializedName("tenTest")
    @Expose
    private String tenTest;

    protected TestPart5(Parcel in) {
        idTest = in.readString();
        tenTest = in.readString();
    }

    public static final Creator<TestPart5> CREATOR = new Creator<TestPart5>() {
        @Override
        public TestPart5 createFromParcel(Parcel in) {
            return new TestPart5(in);
        }

        @Override
        public TestPart5[] newArray(int size) {
            return new TestPart5[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idTest);
        dest.writeString(tenTest);
    }
}
