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
import java.util.List;

/**
 * Created by TianyuanPan on 8/10/16.
 */
public class Action implements Runnable {

    private XmlToMysqlUI ui;
    private MysqlClient mysqlClient;
    private String dataDir;
    private String tableName;
    private String domain;
    private ModelType modelType;
    private ParseXmlToModel parser;
    private String topicMap;
    private List<TableModel> tableModelList;

    private String printerFileName;
    private File printerFile;
    private OutputStream printerOurStream;

    public static void openFilePrinter(Action action) {

        action.printerFileName = action.dataDir + "/logs/";
        try {
            action.printerFile = new File(action.printerFileName);
            if (!action.printerFile.exists())
                action.printerFile.mkdirs();
            action.printerFileName = action.dataDir + "/logs/bazhuayuXmlDataToMysql-SQL" +
                    action.tableName + "-pid-" +
                    ManagementFactory.getRuntimeMXBean().getName().split("@")[0] + "-log.txt";
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
    }

    @Override
    public void run() {

        Action.openFilePrinter(this);
        mysqlClient.getConnection();
        List<File> files = FilesOperations.getFileListByDirectory(this.dataDir);
        action(files);
        mysqlClient.closeConnection();
        Action.closeFilePrinter(this);
        this.ui.printToStatus("\n\t操作完成！！！");
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
                continue;
            }

        }
    }

    private void doInsert(List<TableModel> tableModelList) {

        for (TableModel t : tableModelList) {
            String sql = t.getMysqlInsertSql(tableName);
            String msg = "插入数据 SQL:\n\t" + sql;
            this.ui.printToStatus(msg);
            try {
                if (mysqlClient.excuteUpdateSql(sql) > 0)
                    this.ui.printToStatus(msg + "\n\t插入成功!");
            } catch (SQLException e) {
                e.printStackTrace();
                this.ui.printToStatus(msg + "\n\t插入失败!");
            }
        }
    }
}
