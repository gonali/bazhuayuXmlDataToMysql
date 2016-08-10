package com.gonali.bzhyd2mysql.model;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public class TopBaiduArticleInfo implements TableModel{

    private String articleTitle;
    private String essential;
    private String publishTime;
    private String keyWords;
    private String articleRawUrl;
    private String crawlTime;

    public TopBaiduArticleInfo() {

        articleTitle = "";
        essential = "";
        publishTime = "";
        keyWords = "";
        articleRawUrl = "";
        crawlTime = "";
    }

    public TopBaiduArticleInfo(String articleTitle, String essential, String publishTime, String keyWords,
                               String articleRawUrl, String crawlTime) {

        this.articleTitle = articleTitle;
        this.essential = essential;
        this.publishTime = publishTime;
        this.keyWords = keyWords;
        this.articleRawUrl = articleRawUrl;
        this.crawlTime = crawlTime;

    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getEssential() {
        return essential;
    }

    public void setEssential(String essential) {
        this.essential = essential;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getArticleRawUrl() {
        return articleRawUrl;
    }

    public void setArticleRawUrl(String articleRawUrl) {
        this.articleRawUrl = articleRawUrl;
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
                "(articleTitle,essential,publishTime,keyWords,articleRawUrl,crawlTime)" +
                "VALUES('" + articleTitle + "','" + essential + "','" + publishTime + "','" + keyWords + "','"
                + articleRawUrl + "','" + crawlTime + "');";

        return sql;
    }
}
