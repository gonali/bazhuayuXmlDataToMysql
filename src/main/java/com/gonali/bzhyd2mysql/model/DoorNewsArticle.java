package com.gonali.bzhyd2mysql.model;

/**
 * Created by TianyuanPan on 8/8/16.
 */
public class DoorNewsArticle implements TableModel{

    private String domain;
    private String topicMap;
    private String articleTitle;
    private String rawTitle;
    private String publishTime;
    private String author;
    private String articleContents;
    private int commentCount;
    private String articleRawUrl;
    private String crawlTime;

    public DoorNewsArticle() {

        domain = "";
        topicMap = "";
        articleTitle = "";
        rawTitle = "";
        publishTime = "";
        author = "";
        articleContents = "";
        commentCount = 0;
        articleRawUrl = "";
        crawlTime = "";
    }

    public DoorNewsArticle(String domain, String topicMap, String articleTitle, String rawTitle,
                           String publishTime, String author, String articleContents, int commentCount,
                           String articleRawUrl, String crawlTime) {
        this.domain = domain;
        this.topicMap = topicMap;
        this.articleTitle = articleTitle;
        this.rawTitle = rawTitle;
        this.publishTime = publishTime;
        this.author = author;
        this.articleContents = articleContents;
        this.commentCount = commentCount;
        this.articleRawUrl = articleRawUrl;
        this.crawlTime = crawlTime;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTopicMap() {
        return topicMap;
    }

    public void setTopicMap(String topicMap) {
        this.topicMap = topicMap;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getRawTitle() {
        return rawTitle;
    }

    public void setRawTitle(String rawTitle) {
        this.rawTitle = rawTitle;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArticleContents() {
        return articleContents;
    }

    public void setArticleContents(String articleContents) {
        this.articleContents = articleContents;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
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
                "(domain,topicMap,articleTitle,rawTitle,publishTime,author,articleContents,commentCount,articleRawUrl,crawlTime)" +
                "VALUES('" + domain + "','" + topicMap + "','" + articleTitle +
                "','" + rawTitle + "','" + publishTime + "','" +
                author + "','" + articleContents + "'," + commentCount + ",'" + articleRawUrl + "','" + crawlTime + "');";

        return sql;
    }
}
