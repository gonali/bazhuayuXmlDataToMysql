package com.gonali.bzhyd2mysql.parser.impl;

import com.gonali.bzhyd2mysql.model.SogouWeChatArticle;
import com.gonali.bzhyd2mysql.parser.BaseParserAbstract;
import com.sun.istack.internal.Nullable;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public class ParseSogouWeChatArticle extends BaseParserAbstract<SogouWeChatArticle> {
    public ParseSogouWeChatArticle(){}
    @Override
    public List<SogouWeChatArticle> parseToModel(@Nullable String domain, @Nullable String topicMap, String xmlFileName) {
        List<SogouWeChatArticle> modelList = new ArrayList<>();

        if (!initDocument(xmlFileName))
            return modelList;

        Elements dataListElements = document.select("DataList").select("Root");

        for (Element element : dataListElements) {

            SogouWeChatArticle article = new SogouWeChatArticle();

            try {

                article.setWeChatName(element.select("weChatName").text());
                article.setWeChatNo(element.select("weChatNo").text());
                article.setMainGroup(element.select("mainGroup").text());
                article.setEssential(element.select("essential").text());
                article.setCrawlTime(element.select("crawlTime").text());
                article.setArticleTitle(element.select("articleTitle").text());
                article.setPublishTime(element.select("publishTime").text());
                article.setArticleStyleContents(element.select("articleStyleContents").text().replace("\'", "\\\'"));

            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            modelList.add(article);
        }

        return modelList;
    }
}
