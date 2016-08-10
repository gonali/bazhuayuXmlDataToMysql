package com.gonali.bzhyd2mysql.parser.impl;

import com.gonali.bzhyd2mysql.model.TopBaiduArticleInfo;
import com.gonali.bzhyd2mysql.parser.BaseParserAbstract;
import com.sun.istack.internal.Nullable;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public class ParseTopBaiduArticleInfo extends BaseParserAbstract<TopBaiduArticleInfo> {

    public ParseTopBaiduArticleInfo(){}

    @Override
    public List<TopBaiduArticleInfo> parseToModel(@Nullable String domain, @Nullable String topicMap, String xmlFileName){

        List<TopBaiduArticleInfo> modelList = new ArrayList<>();

        if (!initDocument(xmlFileName))
            return modelList;

        Elements dataListElements = document.select("DataList").select("Root");

        for (Element element : dataListElements) {

            TopBaiduArticleInfo info = new TopBaiduArticleInfo();
            try {

                info.setArticleTitle(element.select("articleTitle").text());
                info.setArticleRawUrl(element.select("articleRawUrl").text());
                info.setEssential(element.select("essential").text());
                info.setPublishTime(element.select("publishTime").text());
                info.setKeyWords(element.select("keywords").text());
                info.setCrawlTime(element.select("crawlTime").text());

            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            modelList.add(info);
        }

        return modelList;
    }
}
