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
    public static void main(String argv[])
    {
        String filePath = "C:\\Users\\ddpai\\Desktop\\s1.sql";
        List<ISqlStatement> sqlStatements = FileUtil.getSqlEntityListByReadFile(filePath);
        for(ISqlStatement sqlStatement : sqlStatements)
        {
            //TODO 执行操作

            sqlStatement.translateTimestamp("buy_date");
            sqlStatement.translateTimestamp("change_date");
            sqlStatement.translateTimestamp("effective_date");
            sqlStatement.translateTimestamp("expiry_date");
            sqlStatement.putParam("total_flow", "0");

            printSql(sqlStatement);
        }
        FileUtil.writeFileBySqlEntityList("C:\\Users\\ddpai\\Desktop\\s2.sql", sqlStatements);

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
