package com.gonali.bzhyd2mysql.parser.impl;

import com.gonali.bzhyd2mysql.model.DoorNewsArticle;
import com.gonali.bzhyd2mysql.model.DoorNewsImg;
import com.gonali.bzhyd2mysql.parser.BaseParserAbstract;
import com.sun.istack.internal.Nullable;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public class ParseDoorNewsImg extends BaseParserAbstract<DoorNewsImg> {

    public ParseDoorNewsImg(){}

    @Override
    public List<DoorNewsImg> parseToModel(@Nullable String domain, @Nullable String topicMap, String xmlFileName) {
        List<DoorNewsImg> modelList = new ArrayList<>();

        if (!initDocument(xmlFileName))
            return modelList;

        Elements dataListElements = document.select("DataList").select("Root");

        for (Element element : dataListElements) {

            DoorNewsImg img = new DoorNewsImg();
            img.setDomain(domain);
            try {

                img.setArticleRawUrl(element.select("articleRawUrl").text());
                img.setArticleTitle(element.select("articleTitle").text());
                img.setCrawlTime(element.select("crawlTime").text());
                img.setImgSrcUrl(element.select("imgSrcUrl").text());

            } catch (Exception e) {

                e.printStackTrace();
                continue;
            }

            modelList.add(img);
        }

        return modelList;
    }
}
