package com.gonali.bzhyd2mysql.parser.impl;

import com.gonali.bzhyd2mysql.model.GovArticleImg;
import com.gonali.bzhyd2mysql.parser.BaseParserAbstract;
import com.sun.istack.internal.Nullable;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public class ParseGovArticleImg extends BaseParserAbstract<GovArticleImg> {

    public ParseGovArticleImg(){}

    @Override
    public List<GovArticleImg> parseToModel(@Nullable String domain, @Nullable String topicMap, String xmlFileName) {

        List<GovArticleImg> modelList = new ArrayList<>();

        if (!initDocument(xmlFileName))
            return modelList;

        Elements dataListElements = document.select("DataList").select("Root");

        for (Element element : dataListElements) {

            GovArticleImg img = new GovArticleImg();
            img.setDomain(domain);
            try {
                img.setArticleTitle(element.select("articleTitle").text());
                img.setArticleRawUrl(element.select("articleRawUrl").text());
                img.setImgSrcUrl(element.select("imgSrcUrl").text());
                img.setCrawlTime(element.select("crawlTime").text());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            modelList.add(img);
        }

        return modelList;
    }
}
