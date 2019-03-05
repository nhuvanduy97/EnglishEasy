package nvduy1997.com.easytoeic.model;

public class VideoYoutube {
    private String title;
    private String thumbnail;
    private String idVideo;


    public VideoYoutube(String title, String thumbnail, String idVideo) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.idVideo = idVideo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    @Override
    public String toString() {
        return "VideoYoutube{" +
                "title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", idVideo='" + idVideo + '\'' +
                '}';
    }
}
