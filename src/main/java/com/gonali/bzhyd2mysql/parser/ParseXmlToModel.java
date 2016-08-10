package com.gonali.bzhyd2mysql.parser;

import com.sun.istack.internal.Nullable;

import java.util.List;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public interface ParseXmlToModel<T> {

    List<T>  parseToModel(@Nullable String domain, @Nullable String topicMap, String xmlFileName);
}
