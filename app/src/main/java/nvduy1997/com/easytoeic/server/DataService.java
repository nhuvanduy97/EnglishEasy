package nvduy1997.com.easytoeic.server;

import java.util.List;

import nvduy1997.com.easytoeic.model.Question;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("allQuestion.php")
    Call<List<Question>> getQuestionP5Test1();
}