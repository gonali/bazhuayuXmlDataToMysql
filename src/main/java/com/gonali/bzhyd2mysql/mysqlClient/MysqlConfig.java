package com.gonali.bzhyd2mysql.mysqlClient;

import com.gonali.bzhyd2mysql.utils.FilesOperations;

/**
 * Created by TianyuanPan on 8/9/16.
 */
public class MysqlConfig {

    private static String hostname;
    private static int    port;
    private static String database;
    private static String username;
    private static String password;

    public static void setValue(String configFile){

        String confs[] = FilesOperations.readFileLineByLine(configFile);

        for (String s : confs){

            switch (s.split("=")[0].trim()){
                case "hostname":
                    hostname = s.split("=")[1].trim();
                    break;
                case "port":
                    port = Integer.parseInt(s.split("=")[1].trim());
                    break;
                case "database":
                    database = s.split("=")[1].trim();
                    break;
                case "username":
                    username = s.split("=")[1].trim();
                    break;
                case "password":
                    password = s.split("=")[1].trim();
                    break;
                default:
                    break;
            }
        }

    }

    public static String getHostname() {
        return hostname;
    }

    public static int getPort() {
        return port;
    }

    public static String getDatabase() {
        return database;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
