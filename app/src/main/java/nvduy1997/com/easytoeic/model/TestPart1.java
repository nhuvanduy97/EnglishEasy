package nvduy1997.com.easytoeic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestPart1 {

@SerializedName("idTest")
@Expose
private String idTest;
@SerializedName("tenTest")
@Expose
private String tenTest;
@SerializedName("hinhTest")
@Expose
private String hinhTest;

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

}