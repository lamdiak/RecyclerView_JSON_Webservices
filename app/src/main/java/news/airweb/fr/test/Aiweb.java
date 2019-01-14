package news.airweb.fr.test;

public class Aiweb {


    private String title;
    private String shortdesc;
    private String image;

    public Aiweb(String title, String shortdesc, String image) {

        this.title = title;
        this.shortdesc = shortdesc;
        this.image = image;
    }


    public String getTitle() {
        return title;
    }

    public  String getShortdesc() {
        return shortdesc;
    }

    public String getImage() {
        return image;
    }
}
