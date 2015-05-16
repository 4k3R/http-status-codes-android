package anjithsasindran.httpstatuscodes;

/**
 * Created by Anjith Sasindran on 16-May-15.
 * Class for containing all the values of http status codes
 */
public class HttpStatusCodes {

    private String code;
    private String title;
    private String summary;
    private String wikidesc;
    private String wikilink;
    private String ietfdesc;
    private String ietflink;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getWikidesc() {
        return wikidesc;
    }

    public void setWikidesc(String wikidesc) {
        this.wikidesc = wikidesc;
    }

    public String getWikilink() {
        return wikilink;
    }

    public void setWikilink(String wikilink) {
        this.wikilink = wikilink;
    }

    public String getIetfdesc() {
        return ietfdesc;
    }

    public void setIetfdesc(String ietfdesc) {
        this.ietfdesc = ietfdesc;
    }

    public String getIetflink() {
        return ietflink;
    }

    public void setIetflink(String ietflink) {
        this.ietflink = ietflink;
    }
}
