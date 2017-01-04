package com.devlip.gymassistant.constants;

public class DatabaseInitConst {
    public static final String DATABASE_NAME = "GymAssistant.db";
    public static final int DATABASE_VERSION = 1;
    public static final String SQL_TABLE_CREATE_TEMPLATE = "CREATE TABLE IF NOT EXISTS %s (%s)";
    public static final String SQL_TABLE_CREATE_FIELD_TEMPLATE = "%s %s";
}
