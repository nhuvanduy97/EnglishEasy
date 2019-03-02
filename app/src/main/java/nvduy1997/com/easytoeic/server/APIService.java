package nvduy1997.com.easytoeic.server;

public class APIService {
    private static String url = "https://servertoiectbd.000webhostapp.com/server/";
    public static DataService getService(){
        return APIRetrofitClient.getClient(url).create(DataService.class);
    }
}
