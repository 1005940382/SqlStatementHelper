package com.orange.oldVersion;

import com.orange.sql.SqlType;

/**
 * @author 鞠鹏飞
 * @data 2017/8/23
 * @Description sql语句实体类工厂
 */
public class SqlStatementFactory1
{
    public static ISqlStatement1 createSqlStatement(SqlType sqlType)
    {
        switch(sqlType)
        {
            case INSERT:
                return new InsertStatement1();
            case DELETE:
                return null;
            case SELECT:
                return null;
            case UPDATE:
                return null;
        }
        return null;
    }
}
