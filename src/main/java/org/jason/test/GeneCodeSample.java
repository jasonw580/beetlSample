package org.jason.test;

import org.beetl.sql.core.SQLManager;

public class GeneCodeSample {
    private SQLManager sqlManager;

    public GeneCodeSample(SQLManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    public String generateCode(String tableName){
        String sql = "";
        try {
            sqlManager.genPojoCodeToConsole(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql;
    }
    public String generateSqlTemplateCode(String tableName){
        String sql = "";

        try {
            sqlManager.genSQLTemplateToConsole(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql;
    }
}
