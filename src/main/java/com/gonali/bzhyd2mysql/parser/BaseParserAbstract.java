package com.gonali.bzhyd2mysql.parser;

import com.sun.istack.internal.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public abstract class BaseParserAbstract<T> implements ParseXmlToModel {

    protected Document document;

    protected boolean initDocument(String xmlFileName) {

        try {

            File xmlFile = new File(xmlFileName);
            document = Jsoup.parse(xmlFile, "utf-8");

        } catch (IOException e) {

            e.printStackTrace();

            return false;
        }

        return true;
    }

    public  List<T>  parseToModel(@Nullable String domain, @Nullable String topicMap, String xmlFileName){

        return null;
    }
}
