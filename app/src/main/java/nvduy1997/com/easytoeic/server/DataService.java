
package nvduy1997.com.easytoeic.server;

import java.util.List;

import nvduy1997.com.easytoeic.model.DetailGrammar;
import nvduy1997.com.easytoeic.model.Grammar;
import nvduy1997.com.easytoeic.model.QuestionPart1;
import nvduy1997.com.easytoeic.model.QuestionPart3;
import nvduy1997.com.easytoeic.model.QuestionPart5;
import nvduy1997.com.easytoeic.model.QuestionPart7;
import nvduy1997.com.easytoeic.model.TestPart1;
import nvduy1997.com.easytoeic.model.TestPart3;
import nvduy1997.com.easytoeic.model.TestPart5;
import nvduy1997.com.easytoeic.model.TestPart6;
import nvduy1997.com.easytoeic.model.TestPart7;
import nvduy1997.com.easytoeic.model.TopicVocabulary;
import nvduy1997.com.easytoeic.model.Vocabulary;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

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

    @FormUrlEncoded
    @POST("QuestionPart1.php")
    Call<List<QuestionPart1>> getQuestionPart1(@Field("idTest") String idTest);

    @GET("allTestPart5.php")
    Call<List<TestPart5>> getTestPart5();

    @FormUrlEncoded
    @POST("QuestionPart5.php")
    Call<List<QuestionPart5>> getQuestionPart5(@Field("idTest") String idTest);

    @GET("TestPart3.php")
    Call<List<TestPart3>> getTestPart3();

    @FormUrlEncoded
    @POST("QuestionPart3.php")
    Call<List<QuestionPart3>> getQuestionPart3(@Field("idTest") String idTest);

    @GET("TestPart6.php")
    Call<List<TestPart6>> getTestPart6();

    @GET("TestPart7.php")
    Call<List<TestPart7>> getTestPart7();

    @FormUrlEncoded
    @POST("QuestionPart7.php")
    Call<List<QuestionPart7>> getQuestionPart7(@Field("idTest") String idTest);


}