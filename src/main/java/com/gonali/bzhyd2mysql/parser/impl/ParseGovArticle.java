package com.gonali.bzhyd2mysql.parser.impl;

import com.gonali.bzhyd2mysql.model.GovArticle;
import com.gonali.bzhyd2mysql.parser.BaseParserAbstract;
import com.sun.istack.internal.Nullable;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public class ParseGovArticle extends BaseParserAbstract<GovArticle> {


    public ParseGovArticle() {
    }

    @Override
    public List<GovArticle> parseToModel(@Nullable String domain, @Nullable String topicMap, String xmlFileName) {
        List<GovArticle> modelList = new ArrayList<>();

        if (!initDocument(xmlFileName))
            return modelList;

        Elements dataListElements = document.select("DataList").select("Root");

        for (Element element : dataListElements) {

            GovArticle article = new GovArticle();
            article.setDomain(domain);
            article.setTopicMap(topicMap);
            try {
                article.setArticleTitle(element.select("articleTitle").text());
                article.setPublishTime(element.select("publishTime").text());
                article.setAuthor(element.select("author").text());
                article.setArticleContents(element.select("articleContents").text().replace("\'", "\\\'"));
                article.setArticleRawUrl(element.select("articleRawUrl").text());
                article.setCrawlTime(element.select("crawlTime").text());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            modelList.add(article);
        }

        return modelList;
    }
}
