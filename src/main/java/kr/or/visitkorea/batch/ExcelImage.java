package kr.or.visitkorea.batch;

public class ExcelImage {

    boolean isPrimary;
    String cid;
    String title;
    String url;

    ExcelImage(String cid, String title, String url) {
        this(cid, title, url, false);
    }

    ExcelImage(String cid, String title, String url, boolean isPrimary) {
        this.isPrimary = isPrimary;
        this.cid = cid;
        this.title = title;
        this.url = url;
    }
}