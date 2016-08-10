package com.gonali.bzhyd2mysql.app;

import com.gonali.bzhyd2mysql.model.TableModel;
import com.gonali.bzhyd2mysql.mysqlClient.MysqlClient;
import com.gonali.bzhyd2mysql.mysqlClient.MysqlConfig;
import com.gonali.bzhyd2mysql.parser.ParseXmlToModel;
import com.gonali.bzhyd2mysql.parser.impl.*;
import com.gonali.bzhyd2mysql.utils.FilesOperations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by TianyuanPan on 8/10/16.
 */
public class Action implements Runnable {

    private static final int ALL_ITEMS = 0;
    private static final int SUCCESS_ITEMS = 1;
    private static final int FAILED_ITEMS = 2;

    private XmlToMysqlUI ui;
    private MysqlClient mysqlClient;
    private String dataDir;
    private String tableName;
    private String domain;
    private ModelType modelType;
    private ParseXmlToModel parser;
    private String topicMap;
    private List<TableModel> tableModelList;
    private int[] counter;

    private String printerFileName;
    private File printerFile;
    private OutputStream printerOurStream;

    public static void openFilePrinter(Action action) {

        action.printerFileName = action.dataDir + "/logs/";
        try {
            action.printerFile = new File(action.printerFileName);
            if (!action.printerFile.exists())
                action.printerFile.mkdirs();
            action.printerFileName = action.dataDir + "/logs/bazhuayuXmlDataToMysql-SQL-" +
                    action.tableName + "-pid-" +
                    ManagementFactory.getRuntimeMXBean().getName().split("@")[0] + "-"+
                    new SimpleDateFormat("YYYY-MM-dd_HH-mm-ss").format(new Date())+"-log.txt";
            action.printerFile = new File(action.printerFileName);
            action.printerOurStream = new FileOutputStream(action.printerFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeFilePrinter(Action action) {

        try {
            if (action.printerOurStream != null) {
                action.printerOurStream.flush();
                action.printerOurStream.close();
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public static void printerToFile(String msg, Action action) {

        try {
            action.printerOurStream.write(msg.getBytes(), 0, msg.getBytes().length);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Action(XmlToMysqlUI ui) {

        this.ui = ui;
        mysqlClient = new MysqlClient(MysqlConfig.getHostname(), MysqlConfig.getPort(),
                MysqlConfig.getDatabase(), MysqlConfig.getUsername(), MysqlConfig.getPassword());
        dataDir = this.ui.getDataDirName();
        tableName = this.ui.getMysqlTableName();
        modelType = (ModelType) this.ui.getModelType();
        domain = this.ui.getDomain();
        this.counter = new int[3];
    }

    @Override
    public void run() {

        long t1 = System.currentTimeMillis();
        Action.openFilePrinter(this);
        mysqlClient.getConnection();
        List<File> files = FilesOperations.getFileListByDirectory(this.dataDir);
        action(files);
        mysqlClient.closeConnection();
        Action.closeFilePrinter(this);
        long t2 = System.currentTimeMillis();
        float t = (float)((t2 - t1) / 1000.0 / 60.0);
        String msg = "\n\t操作完成！！！\n\n\t总数据: " +
                counter[ALL_ITEMS] + " 条\n\t成  功: " +
                counter[SUCCESS_ITEMS] + " 条\n\t失   败: " +
                counter[FAILED_ITEMS] + " 条\n\t耗   时: " + t + " 分钟";
        this.ui.printToStatus(msg);
        XmlToMysqlUI.resetWindow(this.ui);
    }


    private void action(List<File> files) {

        for (File f : files) {
            if (f.isDirectory())
                continue;
            try {
                String fullName = this.dataDir + "/" + f.getName();
                topicMap = f.getName().substring(0, f.getName().indexOf("_"));
                //System.out.println("fullName:" + fullName + "\ntopicMap:" + topicMap);

                switch (modelType) {

                    case DOOR_NEWS_ARTICLE:
                        parser = new ParseDoorNewsArticle();
                        tableModelList = parser.parseToModel(domain, topicMap, fullName);
                        doInsert(tableModelList);
                        break;
                    case DOOR_NEWS_COMMENT:
                        parser = new ParseDoorNewsComment();
                        tableModelList = parser.parseToModel(domain, topicMap, fullName);
                        doInsert(tableModelList);
                        break;
                    case DOOR_NEWS_IMG:
                        parser = new ParseDoorNewsImg();
                        tableModelList = parser.parseToModel(domain, topicMap, fullName);
                        doInsert(tableModelList);
                        break;
                    case GOV_ARTICLE:
                        parser = new ParseGovArticle();
                        tableModelList = parser.parseToModel(domain, topicMap, fullName);
                        doInsert(tableModelList);
                        break;
                    case GOV_ARTICLE_IMG:
                        parser = new ParseGovArticleImg();
                        tableModelList = parser.parseToModel(domain, topicMap, fullName);
                        doInsert(tableModelList);
                        break;
                    case SOGOU_WECHAT_ARTICLE:
                        parser = new ParseSogouWeChatArticle();
                        tableModelList = parser.parseToModel(domain, topicMap, fullName);
                        doInsert(tableModelList);
                        break;
                    case TOP_BAIDU:
                        parser = new ParseTopBaidu();
                        tableModelList = parser.parseToModel(domain, topicMap, fullName);
                        doInsert(tableModelList);
                        break;
                    case TOP_BAIDU_ARTICLE_INFO:
                        parser = new ParseTopBaiduArticleInfo();
                        tableModelList = parser.parseToModel(domain, topicMap, fullName);
                        doInsert(tableModelList);
                        break;
                    default:
                        this.ui.printToStatus("\n\t类型错误！！！！");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void doInsert(List<TableModel> tableModelList) {

        for (TableModel t : tableModelList) {
            counter[ALL_ITEMS] += 1;
            String sql = t.getMysqlInsertSql(tableName);
            String msg = "\n\t域名: " + domain + "\n\t第 " + String.valueOf(counter[ALL_ITEMS]) +
                    " 条数据\n\t插入数据表: " + tableName +
                    "\n\t表结构类型: " + modelType +
                    "\n\t插入成功，第 " + counter[SUCCESS_ITEMS] + " 条！" +
                    "\n\t插入失败，共 " + counter[FAILED_ITEMS] + " 条！\n\n====== SQL ======\n\n" + sql;
            Action.printerToFile(sql + "\n", this);
            this.ui.printToStatus(msg);
            try {
                if (mysqlClient.excuteUpdateSql(sql) > 0) {
                    counter[SUCCESS_ITEMS] += 1;
                } else {
                    counter[FAILED_ITEMS] += 1;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                counter[FAILED_ITEMS] += 1;
            }
        }
    }
}
