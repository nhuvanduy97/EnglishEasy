
package nvduy1997.com.easytoeic.server;

import java.util.List;

import nvduy1997.com.easytoeic.model.DetailGrammar;
import nvduy1997.com.easytoeic.model.Grammar;
import nvduy1997.com.easytoeic.model.Question;
import nvduy1997.com.easytoeic.model.TestPart1;
import nvduy1997.com.easytoeic.model.TopicVocabulary;
import nvduy1997.com.easytoeic.model.Vocabulary;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("allQuestion.php")
    Call<List<Question>> getQuestionP5Test1();

    @GET("ChuDeTuVung.php")
    Call<List<TopicVocabulary>> getTopicVocabulary();

    @FormUrlEncoded
    @POST("TuVung.php")
    Call<List<Vocabulary>> getVocabulary(@Field("idChuDe") String idChuDe);

    @GET("allGrammar.php")
    Call<List<Grammar>> getAllGrammar();

    @FormUrlEncoded
    @POST("detailGrammar.php")
    Call<List<DetailGrammar>> getDetailgrammar(@Field("idgrammar") String idgrammar);

    @GET("TestPart1.php")
    Call<List<TestPart1>> getTestPart1();
}