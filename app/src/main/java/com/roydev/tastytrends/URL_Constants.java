package com.roydev.tastytrends;

public class URL_Constants {
    private String sTable = "/testers";
    private final String BASE_URL = "http://192.168.172.197:80/api";

    public void setTable(String table) {
        this.sTable = table;
    }

    public String getTable() {
        return sTable;
    }

    public String getLoginUrl() {
        return BASE_URL + sTable + "/login";
    }

    public String getRegisterUrl() {
        return BASE_URL + sTable + "/register";
    }

    public String getUpdateUrl() {
        return BASE_URL + sTable + "/update";
    }

    public String getDeleteUrl() {
        return BASE_URL + sTable + "/delete";
    }
}
