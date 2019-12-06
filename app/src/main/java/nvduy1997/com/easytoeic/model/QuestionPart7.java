package nvduy1997.com.easytoeic.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionPart7 implements Parcelable {
    @SerializedName("idQuestion")
    @Expose
    private String idQuestion;

    @SerializedName("tenQuestion")
    @Expose
    private String tenQuestion;

    @SerializedName("daA")
    @Expose
    private String daA;

    @SerializedName("daB")
    @Expose
    private String daB;

    @SerializedName("daC")
    @Expose
    private String daC;

    @SerializedName("daD")
    @Expose
    private String daD;

    @SerializedName("resultQuestion")
    @Expose
    private String resultQuestion;

    @SerializedName("idTest")
    @Expose
    private String idTest;

    @SerializedName("traLoi")
    @Expose

    private String traLoi = "";

    public int choiceID = -1; // hỗ trợ check id của radiogroup

    protected QuestionPart7(Parcel in) {
        idQuestion = in.readString();
        tenQuestion = in.readString();
        daA = in.readString();
        daB = in.readString();
        daC = in.readString();
        daD = in.readString();
        resultQuestion = in.readString();
        idTest = in.readString();
        traLoi = in.readString();
        choiceID = in.readInt();
    }

    public static final Creator<QuestionPart7> CREATOR = new Creator<QuestionPart7>() {
        @Override
        public QuestionPart7 createFromParcel(Parcel in) {
            return new QuestionPart7(in);
        }

        @Override
        public QuestionPart7[] newArray(int size) {
            return new QuestionPart7[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idQuestion);
        dest.writeString(tenQuestion);
        dest.writeString(daA);
        dest.writeString(daB);
        dest.writeString(daC);
        dest.writeString(daD);
        dest.writeString(resultQuestion);
        dest.writeString(idTest);
        dest.writeString(traLoi);
        dest.writeInt(choiceID);
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getTenQuestion() {
        return tenQuestion;
    }

    public void setTenQuestion(String tenQuestion) {
        this.tenQuestion = tenQuestion;
    }

    public String getDaA() {
        return daA;
    }

    public void setDaA(String daA) {
        this.daA = daA;
    }

    public String getDaB() {
        return daB;
    }

    public void setDaB(String daB) {
        this.daB = daB;
    }

    public String getDaC() {
        return daC;
    }

    public void setDaC(String daC) {
        this.daC = daC;
    }

    public String getDaD() {
        return daD;
    }

    public void setDaD(String daD) {
        this.daD = daD;
    }

    public String getResultQuestion() {
        return resultQuestion;
    }

    public void setResultQuestion(String resultQuestion) {
        this.resultQuestion = resultQuestion;
    }

    public String getIdTest() {
        return idTest;
    }

    public void setIdTest(String idTest) {
        this.idTest = idTest;
    }

    public String getTraLoi() {
        return traLoi;
    }

    public void setTraLoi(String traLoi) {
        this.traLoi = traLoi;
    }

    public int getChoiceID() {
        return choiceID;
    }

    public void setChoiceID(int choiceID) {
        this.choiceID = choiceID;
    }

    public static Creator<QuestionPart7> getCREATOR() {
        return CREATOR;
    }
}
