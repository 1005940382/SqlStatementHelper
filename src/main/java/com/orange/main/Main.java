package com.orange.main;

import com.orange.sql.ISqlStatement;
import com.orange.util.FileUtil;

import java.util.List;
import java.util.Map;

/**
 * @author 鞠鹏飞
 * @data 2017/8/23
 * @Description
 */
public class Main
{
    private static final String OLD_FILE_PATH = "C:\\Users\\ddpai\\Desktop\\s1.sql";
    private static final String NEW_FILE_PATH = "C:\\Users\\ddpai\\Desktop\\s3.sql";

    public static void main(String argv[])
    {
        List<ISqlStatement> sqlStatements = FileUtil.getSqlEntityListByReadFile(OLD_FILE_PATH);
        for(ISqlStatement sqlStatement : sqlStatements)
        {
            //TODO 执行操作

            sqlStatement.translateTimestamp("buy_date");
            sqlStatement.translateTimestamp("change_date");
            sqlStatement.translateTimestamp("effective_date");
            sqlStatement.translateTimestamp("expiry_date");
            sqlStatement.putParam("total_flow", "0");

            //打印语句
            printSql(sqlStatement);
        }
        FileUtil.writeFileBySqlEntityList(NEW_FILE_PATH, sqlStatements);

    }

    private static void printSql(ISqlStatement sqlStatement)
    {
        System.out.println();
        System.out.println(sqlStatement.getTableName()+"  ");
        Map<String, String> params = sqlStatement.getParams();
        for(String param : params.keySet())
        {
            System.out.println(param + " : " + params.get(param));
        }
        System.out.println(sqlStatement.getSqlStatement());
    }
}
