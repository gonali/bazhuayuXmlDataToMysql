package com.gonali.bzhyd2mysql.parser.impl;

import com.gonali.bzhyd2mysql.model.DoorNewsComment;
import com.gonali.bzhyd2mysql.parser.BaseParserAbstract;
import com.sun.istack.internal.Nullable;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public class ParseDoorNewsComment extends BaseParserAbstract<DoorNewsComment> {

    public ParseDoorNewsComment() {
    }

    @Override
    public List<DoorNewsComment> parseToModel(@Nullable String domain, @Nullable String topicMap, String xmlFileName) {
        List<DoorNewsComment> modelList = new ArrayList<>();

        if (!initDocument(xmlFileName))
            return modelList;

        Elements dataListElements = document.select("DataList").select("Root");

        for (Element element : dataListElements) {

            DoorNewsComment comment = new DoorNewsComment();
            comment.setDomain(domain);
            try {
                comment.setArticleRawUrl(element.select("articleRawUrl").text());
                comment.setImgSrcUrl(element.select("imgSrcUrl").text());
                comment.setAuthor(element.select("author").text());
                comment.setLocal(element.select("local").text());
                comment.setTime(element.select("time").text());
                comment.setContents(element.select("contents").text());

                try {
                    comment.setLikesCount(Integer.parseInt(element.select("likesCount").text()));
                } catch (NumberFormatException e) {
                    comment.setLikesCount(0);
                }

                comment.setCrawlTime(element.select("crawlTime").text());
            } catch (Exception e) {

                e.printStackTrace();
                continue;
            }

            modelList.add(comment);
        }

        return modelList;
    }
}
