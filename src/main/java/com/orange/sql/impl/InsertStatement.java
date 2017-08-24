package com.orange.sql.impl;

import com.orange.sql.ISqlStatement;
import com.orange.util.TimeUtil;

import java.util.*;

/**
 * @author 鞠鹏飞
 * @data 2017/8/23
 * @Description sql插入语句（有参数列表）
 */
public class InsertStatement implements ISqlStatement
{
    /** 插入表名 */
    private String tableName;
    /** 插入参数 */
    private Map<String, String> params;

    public InsertStatement()
    {
        params = new LinkedHashMap<String, String>();
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public Map<String, String> getParams()
    {
        return params;
    }

    public void setParams(Map<String, String> params)
    {
        this.params = params;
    }

    /**
     * @Example INSERT INTO `TableName` (`param1`, `param2`, `param3`) VALUES ('value1', 'value2', null);
     */
    public String getSqlStatement()
    {
        StringBuilder sql = new StringBuilder("INSERT INTO `").append(tableName).append("` ");
        StringBuilder paramBuilder = new StringBuilder("(");
        StringBuilder valueBuilder = new StringBuilder("(");
        for(String param : params.keySet())
        {
            String value = params.get(param);
            paramBuilder.append("`").append(param).append("`, ");
            if("NULL".equals(value) || "null".equals(value))
            {
                valueBuilder.append("").append(value).append(", ");
            }
            else
            {
                valueBuilder.append("'").append(value).append("', ");
            }
        }
        paramBuilder.delete(paramBuilder.length() - 2, paramBuilder.length());
        paramBuilder.append(") VALUES ");
        valueBuilder.delete(valueBuilder.length() - 2, valueBuilder.length());
        valueBuilder.append(");");
        sql.append(paramBuilder).append(valueBuilder);
        return sql.toString();
    }

    public void putParam(String param, String value)
    {
        params.put(param, value);
    }

    public String getParamValue(String param)
    {
        return params.get(param);
    }

    public void translateTimestamp(String param)
    {
        String oldValue = params.get(param);
        params.put(param, TimeUtil.translateTimestamp(oldValue));
    }

    public void removeParam(String param)
    {
        params.remove(param);
    }

    /**
     * @Example INSERT INTO `TableName` (`param1`, `param2`, `param3`) VALUES ('value1', 'value2', null);
     */
    public ISqlStatement getSqlEntityByString(String sql)
    {
        //分割sql语句
        String[] sqlParam = sql.split("\\(|\\)");

        //匹配表名
        String[] tableNameArray = sqlParam[0].split(" ");
        String tableName = tableNameArray[2].replace("`", "");
        setTableName(tableName);

        //匹配sql参数和值
        String paramString = sqlParam[1];
        String valueString = sqlParam[3];
        Map<String, String> params = new LinkedHashMap<String, String>();
        String[] paramArray = paramString.split(",");
        String[] valueArray = valueString.split(",");
        for(int i = 0; i < paramArray.length; i++)
        {
            String param = paramArray[i].trim().replace("`", "");
            String value = valueArray[i].trim().replace("'", "");
            params.put(param, value);
        }
        setParams(params);
        return this;
    }
}
