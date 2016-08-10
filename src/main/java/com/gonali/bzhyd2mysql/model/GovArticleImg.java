package com.gonali.bzhyd2mysql.model;

/**
 * Created by TianyuanPan on 8/8/16.
 */
public class GovArticleImg implements TableModel{

    private String domain;
    private String articleRawUrl;
    private String articleTitle;
    private String imgSrcUrl;
    private boolean isDownload;
    private String imgName;
    private String savePath;
    private String crawlTime;


    public GovArticleImg() {

        domain = "";
        articleRawUrl = "";
        articleTitle = "";
        imgSrcUrl = "";
        isDownload = false;
        imgName = "";
        savePath = "";
        crawlTime = "";
    }


    public GovArticleImg(String domain, String articleRawUrl, String articleTitle, String imgSrcUrl, boolean isDownload,
                         String imgName, String savePath, String crawlTime) {

        this.domain = domain;
        this.articleRawUrl = articleRawUrl;
        this.articleTitle = articleTitle;
        this.imgSrcUrl = imgSrcUrl;
        this.isDownload = isDownload;
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

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getImgSrcUrl() {
        return imgSrcUrl;
    }

    public void setImgSrcUrl(String imgSrcUrl) {
        this.imgSrcUrl = imgSrcUrl;
    }

    public boolean isDownload() {
        return isDownload;
    }

    public void setDownload(boolean download) {
        isDownload = download;
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
                "(domain,articleRawUrl,articleTitle,imgSrcUrl,isDownload,imgName,savePath,crawlTime) VALUES ('" +
                domain + "','" + articleRawUrl + "','" + articleTitle + "','" + imgSrcUrl + "'," + isDownload + ",'" +
                imgName + "','" + savePath + "','" + crawlTime + "');";

        return sql;
    }

}
