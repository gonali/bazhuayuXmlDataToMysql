package com.gonali.bzhyd2mysql.model;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public class DoorNewsComment implements TableModel{

    private String domain;
    private String articleRawUrl;
    private String imgSrcUrl;
    private String author;
    private String local;
    private String time;
    private String contents;
    private int likesCount;
    private boolean isImgDownload;
    private String imgName;
    private String savePath;
    private String crawlTime;

    public DoorNewsComment() {

        domain = "";
        articleRawUrl = "";
        imgSrcUrl = "";
        author = "";
        local = "";
        time = "";
        contents = "";
        likesCount = 0;
        isImgDownload = false;
        imgName = "";
        savePath = "";
        crawlTime = "";
    }

    public DoorNewsComment(String domain, String articleRawUrl, String imgSrcUrl, String author, String local,
                           String time, String contents, int likesCount, boolean isImgDownload, String imgName,
                           String savePath, String crawlTime) {

        this.domain = domain;
        this.articleRawUrl = articleRawUrl;
        this.imgSrcUrl = imgSrcUrl;
        this.author = author;
        this.local = local;
        this.time = time;
        this.contents = contents;
        this.likesCount = likesCount;
        this.isImgDownload = isImgDownload;
        this.imgName = imgName;
        this.savePath = savePath;
        this.crawlTime = crawlTime;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getArticleRawUrl() {
        return articleRawUrl;
    }

    public void setArticleRawUrl(String articleRawUrl) {
        this.articleRawUrl = articleRawUrl;
    }

    public String getImgSrcUrl() {
        return imgSrcUrl;
    }

    public void setImgSrcUrl(String imgSrcUrl) {
        this.imgSrcUrl = imgSrcUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public boolean isImgDownload() {
        return isImgDownload;
    }

    public void setImgDownload(boolean imgDownload) {
        isImgDownload = imgDownload;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getCrawlTime() {
        return crawlTime;
    }

    public void setCrawlTime(String crawlTime) {
        this.crawlTime = crawlTime;
    }

    @Override
    public String getMysqlInsertSql(String tableName) {

        String sql;

        sql = "INSERT INTO " + tableName + " " +
                "(domain,articleRawUrl,imgSrcUrl,author,local,time,contents,likesCount,isImgDownload,imgName,savePath,crawlTime)" +
                "('" + domain + "','" + articleRawUrl + "','" + imgSrcUrl + "','" +
                author + "','" + local + "','" + time + "','" + contents + "'," + likesCount + "," + isImgDownload + ",'" +
                imgName + "','" + savePath + "','" + crawlTime + "');";

        return sql;
    }
}
