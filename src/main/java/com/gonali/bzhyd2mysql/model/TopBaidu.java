package com.gonali.bzhyd2mysql.model;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public class TopBaidu implements TableModel{

    private int topNo;
    private String keyWords;
    private String searchIndex;
    private String searchUrl;
    private String crawlTime;

    public TopBaidu() {

        topNo = 0;
        keyWords = "";
        searchIndex = "";
        searchUrl = "";
        crawlTime = "";
    }

    public TopBaidu(int topNo, String keyWords, String searchIndex, String searchUrl, String crawlTime) {

        this.topNo = topNo;
        this.keyWords = keyWords;
        this.searchIndex = searchIndex;
        this.searchUrl = searchUrl;
        this.crawlTime = crawlTime;

    }

    public int getTopNo() {
        return topNo;
    }

    public void setTopNo(int topNo) {
        this.topNo = topNo;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getSearchIndex() {
        return searchIndex;
    }

    public void setSearchIndex(String searchIndex) {
        this.searchIndex = searchIndex;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
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
                "(topNo,keyWords,searchIndex,searchUrl,crawlTime)" +
                "VALUES(" + topNo + ",'" + keyWords + "','" + searchIndex + "','" + searchUrl + "','" + crawlTime + "');";

        return sql;
    }

}
