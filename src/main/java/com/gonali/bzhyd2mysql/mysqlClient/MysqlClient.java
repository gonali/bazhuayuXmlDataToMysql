package com.gonali.bzhyd2mysql.mysqlClient;



import com.gonali.bzhyd2mysql.utils.MySqlPoolUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by TianyuanPan on 6/2/16.
 */
public class MysqlClient {

    private Statement myStatement;
    private MySqlPoolUtils pool;
    private boolean connOpen;
    private Connection connection;


    private void setConnOpen(boolean connOpen) {
        this.connOpen = connOpen;
    }


    public MysqlClient(String hostname, int port, String dbName, String usrname, String password) {

        this.pool = MySqlPoolUtils.getMySqlPoolUtils(hostname, port, dbName, usrname, password);
        this.myStatement = null;
        this.connOpen = false;
    }

    public boolean isConnOpen() {
        return connOpen;
    }

    public Connection getConnection() {

        try {
            this.connection = this.pool.getConnection();
            this.myStatement = this.connection.createStatement();
            if (this.connection != null)
                this.setConnOpen(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            this.setConnOpen(false);
            return null;
        }

        return this.connection;
    }


    public void closeConnection() {

        try {

            this.pool.releaseConnection(this.connection);

            this.setConnOpen(false);

        } catch (Exception ex) {

            this.setConnOpen(false);
            ex.printStackTrace();

        }
    }


    public ResultSet executeQuerySql(String sql) throws SQLException {

        return this.myStatement.executeQuery(sql);
    }


    public int excuteUpdateSql(String sql) throws SQLException {

        return this.myStatement.executeUpdate(sql);
    }
}
