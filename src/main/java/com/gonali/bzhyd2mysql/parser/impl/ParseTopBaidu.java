package com.gonali.bzhyd2mysql.parser.impl;

import com.gonali.bzhyd2mysql.model.TopBaidu;
import com.gonali.bzhyd2mysql.parser.BaseParserAbstract;
import com.sun.istack.internal.Nullable;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public class ParseTopBaidu extends BaseParserAbstract<TopBaidu> {
    @Override
    public List<TopBaidu> parseToModel(@Nullable String domain, @Nullable String topicMap, String xmlFileName) {
        List<TopBaidu> modelList = new ArrayList<>();

        if (!initDocument(xmlFileName))
            return modelList;

        Elements dataListElements = document.select("DataList").select("Root");

        for (Element element : dataListElements) {

            TopBaidu topBaidu = new TopBaidu();
            try {

                try {
                    topBaidu.setTopNo(Integer.parseInt(element.select("topNo").text()));
                } catch (NumberFormatException e) {
                    topBaidu.setTopNo(-1);
                }

                topBaidu.setKeyWords(element.select("keywords").text());
                topBaidu.setSearchIndex(element.select("searchIndex").text());
                topBaidu.setSearchUrl(element.select("searchUrl").text());
                topBaidu.setCrawlTime(element.select("crawlTime").text());

            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            modelList.add(topBaidu);
        }

        return modelList;
    }
}
