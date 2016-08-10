package com.gonali.bzhyd2mysql.app;

import com.gonali.bzhyd2mysql.mysqlClient.MysqlClient;
import com.gonali.bzhyd2mysql.mysqlClient.MysqlConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public class XmlToMysqlUI extends JFrame {

    public static void main(String[] args) {

        new XmlToMysqlUI();
    }

    private MysqlClient mysqlClient;

    private JLabel labelDataDirectory;
    private JLabel labelMysqlConfig;
    private JLabel labelTable;
    private JTextField dataDirectoryTextField;
    private JTextField configFileTextField;
    private JButton dataDirButton;
    private JButton confButton;
    private JButton startButton;
    private JComboBox jComboBoxTable;
    private JComboBox jComboBoxModelType;

    private JLabel labelDomain;
    private JTextField domainTextFeild;

    private JTextArea statusTextArea;


    public XmlToMysqlUI() {

        super("八爪鱼-XML-数据导入-MYSQL-工具--BaZhuaYuDataTool");
        super.setSize(740, 600);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        centered(this);
        this.setLayout(null);//设置布局管理器为空

        /******************************************************************/

        labelDataDirectory = new JLabel("数据所在目录:");
        labelDataDirectory.setBounds(50, 30, 120, 30);

        dataDirectoryTextField = new JTextField();
        dataDirectoryTextField.setBounds(170, 30, 400, 30);
        dataDirectoryTextField.setForeground(Color.red);

        dataDirButton = new JButton("选择...");
        dataDirButton.setBounds(572, 30, 80, 30);
        dataDirButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dataDirectoryTextField.setText(getDataDir());
            }
        });

        /*****************************************************************/

        labelMysqlConfig = new JLabel("数据库配置文件:");
        labelMysqlConfig.setBounds(50, 80, 120, 30);

        configFileTextField = new JTextField();
        configFileTextField.setBounds(170, 80, 400, 30);
        configFileTextField.setForeground(Color.BLUE);

        confButton = new JButton("选择...");
        confButton.setBounds(572, 80, 80, 30);
        confButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configFileTextField.setText(getConfigFileName());
                getMysqlInfo();
            }
        });

        /*******************************************************************/

        labelTable = new JLabel("据库表及类型:");
        labelTable.setBounds(50, 130, 120, 30);
        jComboBoxTable = new JComboBox();
        jComboBoxTable.setMaximumRowCount(10);
        jComboBoxTable.addItem("-- 请选择 --");
        jComboBoxTable.setSelectedIndex(0);
        jComboBoxTable.setBounds(170, 130, 200, 30);


        jComboBoxModelType = new JComboBox();
        jComboBoxModelType.addItem("-- 请选择 --");
        jComboBoxModelType.setBounds(380, 130, 183, 30);
        jComboBoxModelType.setMaximumRowCount(10);
        jComboBoxModelType.addItem(ModelType.DOOR_NEWS_ARTICLE);
        jComboBoxModelType.addItem(ModelType.DOOR_NEWS_COMMENT);
        jComboBoxModelType.addItem(ModelType.DOOR_NEWS_IMG);
        jComboBoxModelType.addItem(ModelType.GOV_ARTICLE);
        jComboBoxModelType.addItem(ModelType.GOV_ARTICLE_IMG);
        jComboBoxModelType.addItem(ModelType.SOGOU_WECHAT_ARTICLE);
        jComboBoxModelType.addItem(ModelType.TOP_BAIDU);
        jComboBoxModelType.addItem(ModelType.TOP_BAIDU_ARTICLE_INFO);

        /*******************************************************************/

        startButton = new JButton("开始...");
        startButton.setBounds(570, 130, 80, 70);
        startButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startUpDownload();
            }
        });

        /********************************************************************/

        labelDomain = new JLabel("请填写域名:");
        labelDomain.setBounds(50, 170, 120, 30);

        domainTextFeild = new JTextField();
        domainTextFeild.setBounds(170, 170, 390, 30);
        domainTextFeild.setBackground(Color.black);
        domainTextFeild.setForeground(Color.orange);


        /*******************************************************************/

        statusTextArea = new JTextArea("========  Hello ========");
        statusTextArea.setBounds(50, 240, 600, 260);
        statusTextArea.setLineWrap(true);
        statusTextArea.setWrapStyleWord(true);
        statusTextArea.setBackground(Color.BLACK);
        statusTextArea.setForeground(Color.green);

        /*******************************************************************/

        this.setVisible(true);
        this.add(labelDataDirectory);
        this.add(dataDirectoryTextField);
        this.add(dataDirButton);

        this.add(labelMysqlConfig);
        this.add(configFileTextField);
        this.add(confButton);

        this.add(labelTable);
        // this.add(datePickButton);
        this.add(jComboBoxTable);
        this.add(jComboBoxModelType);

        this.add(startButton);

        this.add(labelDomain);
        this.add(domainTextFeild);

        this.add(statusTextArea);

        this.setVisible(true);
        this.repaint();

    }


    //布局居中方法
    private void centered(Container container) {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int w = container.getWidth();
        int h = container.getHeight();
        container.setBounds((screenSize.width - w) / 2,
                (screenSize.height - h) / 2, w, h);
    }


    private String getDataDir() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showDialog(this, null);
        fileChooser.getToolTipText();

        return fileChooser.getSelectedFile().getPath();
    }

    private String getConfigFileName() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.showDialog(this, null);
        fileChooser.getToolTipText();

        return fileChooser.getSelectedFile().getPath();
    }

    private void getMysqlInfo() {
        if (configFileTextField.getText().trim().equals("") ||
                configFileTextField.getText().trim() == null)
            return;
        statusTextArea.setText("\n\t正在连接数据库....");
        MysqlConfig.setValue(configFileTextField.getText());
        mysqlClient = new MysqlClient(MysqlConfig.getHostname(), MysqlConfig.getPort(),
                MysqlConfig.getDatabase(), MysqlConfig.getUsername(), MysqlConfig.getPassword());

        String sql = "SELECT table_name FROM information_schema.TABLES  where table_schema = '" + MysqlConfig.getDatabase() + "';";

        if (!mysqlClient.isConnOpen())
            mysqlClient.getConnection();

        try {

            ResultSet rs = mysqlClient.executeQuerySql(sql);

            while (rs.next()) {

                jComboBoxTable.addItem(rs.getString(1));

            }

        } catch (SQLException e) {

            JLabel label = new JLabel("连接错误！！！");
            label.setForeground(Color.red);
            label.setBounds(80, 60, 40, 30);
            centered(label);
            JDialog dialog = new JDialog(this);
            dialog.setSize(160, 120);
            dialog.add(label);
            centered(dialog);
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.setTitle("数据库连接错误!!");
            dialog.show();
            return;
        }
        statusTextArea.setText("\n\t连接数据库成功。");
    }


    public void printToStatus(String msg) {

        statusTextArea.setText(msg);
    }


    public String getDataDirName() {

        return dataDirectoryTextField.getText();
    }

    public String getMysqlTableName() {

        return (String) jComboBoxTable.getSelectedItem();
    }

    private void setConponetEnabled(boolean status) {

        dataDirectoryTextField.setEnabled(status);
        configFileTextField.setEnabled(status);
        dataDirButton.setEnabled(status);
        confButton.setEnabled(status);
        startButton.setEnabled(status);
    }


    private void startUpDownload() {

        if (dataDirectoryTextField.getText() == null ||
                dataDirectoryTextField.getText().equals("") ||
                configFileTextField.getText() == null ||
                configFileTextField.getText().equals("") ||
                domainTextFeild.getText().equals("") ||
                domainTextFeild.getText() == null) {
            JLabel label = new JLabel("参数错误！！！");
            label.setForeground(Color.red);
            label.setBounds(80, 60, 40, 30);
            centered(label);
            JDialog dialog = new JDialog(this);
            dialog.setSize(160, 120);
            dialog.add(label);
            centered(dialog);
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.setTitle("参数错误!!");
            dialog.show();
            return;
        }

        if (jComboBoxTable.getSelectedItem().equals("-- 请选择 --") ||
                jComboBoxModelType.getSelectedItem().equals("-- 请选择 --")) {
            JLabel label = new JLabel("请选择数据库表及类型！！！");
            label.setForeground(Color.red);
            label.setBounds(80, 60, 40, 30);
            centered(label);
            JDialog dialog = new JDialog(this);
            dialog.setSize(160, 120);
            dialog.add(label);
            centered(dialog);
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.setTitle("参数错误!!");
            dialog.show();
            return;
        }

        startButton.setText("导入中..");
        //startButton.setForeground(Color.red);
        setConponetEnabled(false);
        Thread downloadThread = new Thread(new Action(this));
        downloadThread.setDaemon(false);

        try {
            downloadThread.start();
        } catch (Exception e) {
            JLabel label = new JLabel("内部错误！！！");
            label.setForeground(Color.red);
            label.setBounds(80, 60, 40, 30);
            centered(label);
            JDialog dialog = new JDialog(this);
            dialog.setSize(160, 120);
            dialog.add(label);
            centered(dialog);
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.setTitle("内部错误，请检查参数!!");
            dialog.show();
            e.printStackTrace();
            XmlToMysqlUI.resetWindow(this);
        }
    }

    public static void resetWindow(XmlToMysqlUI obj) {

        obj.setConponetEnabled(true);
        obj.startButton.setText("开始...");
    }


    public Object getModelType() {

        return jComboBoxModelType.getSelectedItem();
    }

    public String getDomain() {

        return domainTextFeild.getText();
    }

}
