package com.gonali.bzhyd2mysql.model;

/**
 * Created by TianyuanPan on 8/8/16.
 */
public class GovArticle implements TableModel{

    private String domain;            // 域名
    private String topicMap;          // 文章栏目地图描述
    private String articleTitle;      // 文章标题
    private String publishTime;       // 文章发布时间
    private String author;            // 文章来源，文章作者
    private String articleContents;   // 文章内容
    private String articleRawUrl;     // 文章链接
    private String crawlTime;         // 爬取时间


    public GovArticle() {

        domain = "";
        topicMap = "";
        articleTitle = "";
        publishTime = "";
        author = "";
        articleContents = "";
        articleRawUrl = "";
        crawlTime = "";

    }

    public GovArticle(String domain, String topicMap, String articleTitle, String publishTime,
                      String author, String articleContents, String articleRawUrl, String crawlTime) {
        this.domain = domain;
        this.topicMap = topicMap;
        this.articleTitle = articleTitle;
        this.publishTime = publishTime;
        this.author = author;
        this.articleContents = articleContents;
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
                "(domain,topicMap,articleTitle,publishTime,author,articleContents,articleRawUrl,crawlTime) VALUES ('" +
                domain + "','" + topicMap + "','" + articleTitle + "','" + publishTime + "','" + author + "','" + articleContents
                + "','" + articleRawUrl + "','" + crawlTime + "');";
        return sql;
    }
}
