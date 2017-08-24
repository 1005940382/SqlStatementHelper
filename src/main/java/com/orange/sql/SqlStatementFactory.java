package com.orange.sql;

import com.orange.sql.impl.InsertStatement;
import com.orange.sql.impl.SelectStatement;

/**
 * @author 鞠鹏飞
 * @data 2017/8/23
 * @Description sql语句实体类工厂
 */
public class SqlStatementFactory
{
    public static ISqlStatement createSqlStatement(SqlType sqlType)
    {
        switch(sqlType)
        {
            case INSERT:
                return new InsertStatement();
            case DELETE:
                return null;
            case SELECT:
                return new SelectStatement();
            case UPDATE:
                return null;
        }
        return null;
    }
}
