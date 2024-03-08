package org.example.util;

public class Constants {
    // Database constants
    public static final String DATABASE_URL = "jdbc:mariadb://localhost:3306/test";
    public static final String DATABASE_USER = "kyoominlee@localhost";
    public static final String DATABASE_PASSWORD = "";

    // API constants
    public static final String API_KEY = "4e5a42726c6b796f35335079446466";
    public static final String API_SERVICE_NAME = "TbPublicWifiInfo";
    public static final int API_START_INDEX = 0;
    public static final int API_END_INDEX = 999;
    public static final String BASE_URL = "http://openapi.seoul.go.kr:8088";

    private Constants() {
    }
}
