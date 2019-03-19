package nvduy1997.com.easytoeic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Grammar implements Serializable {

@SerializedName("Id")
@Expose
private String id;
@SerializedName("Ten")
@Expose
private String ten;
@SerializedName("HinhAnh")
@Expose
private String hinhAnh;

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

public String getHinhAnh() {
return hinhAnh;
}

public void setHinhAnh(String hinhAnh) {
this.hinhAnh = hinhAnh;
}

}