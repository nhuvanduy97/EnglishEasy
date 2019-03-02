package nvduy1997.com.easytoeic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Question implements Serializable {

@SerializedName("Id")
@Expose
private String id;
@SerializedName("Ten")
@Expose
private String ten;
@SerializedName("A")
@Expose
private String a;
@SerializedName("B")
@Expose
private String b;
@SerializedName("C")
@Expose
private String c;
@SerializedName("D")
@Expose
private String d;
@SerializedName("Check")
@Expose
private String check;
@SerializedName("Test")
@Expose
private String test;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getTen() {
return ten;
}

public void setTen(String ten) {
this.ten = ten;
}

public String getA() {
return a;
}

public void setA(String a) {
this.a = a;
}

public String getB() {
return b;
}

public void setB(String b) {
this.b = b;
}

public String getC() {
return c;
}

public void setC(String c) {
this.c = c;
}

public String getD() {
return d;
}

public void setD(String d) {
this.d = d;
}

public String getCheck() {
return check;
}

public void setCheck(String check) {
this.check = check;
}

public String getTest() {
return test;
}

public void setTest(String test) {
this.test = test;
}

}