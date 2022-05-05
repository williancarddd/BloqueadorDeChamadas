package com.example.bloqueadordechamadas;

public class ScriptDLL {
    public static String getCreateTableBlackList() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS  black_list (" );
        sql.append("id INTERGER PRIMARY KEY, ");
        sql.append("number VARCHAR(55) NOT NULL )");
        return sql.toString();
    }
}
