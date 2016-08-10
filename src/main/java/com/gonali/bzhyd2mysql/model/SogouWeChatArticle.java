package com.gonali.bzhyd2mysql.model;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public class SogouWeChatArticle implements TableModel{

    private String weChatNo;
    private String weChatName;
    private String mainGroup;
    private String essential;
    private String articleTitle;
    private String publishTime;
    private String articleStyleContents;
    private int readCount;
    private int likesCount;
    private String crawlTime;

    public SogouWeChatArticle() {

        weChatNo = "";
        weChatName = "";
        mainGroup = "";
        essential = "";
        articleTitle = "";
        publishTime = "";
        articleStyleContents = "";
        readCount = 0;
        likesCount = 0;
        crawlTime = "";
    }

    public SogouWeChatArticle(String weChatNo, String weChatName, String mainGroup, String essential,
                              String articleTitle, String publishTime, String articleStyleContents,
                              int readCount, int likesCount, String crawlTime) {

        this.weChatNo = weChatNo;
        this.weChatName = weChatName;
        this.mainGroup = mainGroup;
        this.essential = essential;
        this.articleTitle = articleTitle;
        this.publishTime = publishTime;
        this.articleStyleContents = articleStyleContents;
        this.readCount = readCount;
        this.likesCount = likesCount;
        this.crawlTime = crawlTime;

    }

    public String getWeChatNo() {
        return weChatNo;
    }

    public void setWeChatNo(String weChatNo) {
        this.weChatNo = weChatNo;
    }

    public String getWeChatName() {
        return weChatName;
    }

    public void setWeChatName(String weChatName) {
        this.weChatName = weChatName;
    }

    public String getMainGroup() {
        return mainGroup;
    }

    public void setMainGroup(String mainGroup) {
        this.mainGroup = mainGroup;
    }

    public String getEssential() {
        return essential;
    }

    public void setEssential(String essential) {
        this.essential = essential;
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

    public String getArticleStyleContents() {
        return articleStyleContents;
    }

    public void setArticleStyleContents(String articleStyleContents) {
        this.articleStyleContents = articleStyleContents;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
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
                "(weChatNo,weChatName,mainGroup,essential,articleTitle,publishTime,articleStyleContents,readCount,likesCount,crawlTime)" +
                "VALUES('" + weChatNo + "','" + weChatName + "','" + mainGroup + "','" + essential + "','" +
                articleTitle + "','" + publishTime + "','" + articleStyleContents + "'," + readCount + "," + likesCount + ",'" + crawlTime + "');";

        return sql;
    }
}
