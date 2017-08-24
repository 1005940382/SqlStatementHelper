package com.orange.sql;

import com.orange.util.TimeUtil;

import java.util.*;

/**
 * @author 鞠鹏飞
 * @data 2017/8/23
 * @Description sql插入语句（无参数列表）
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
        String newValue = oldValue;
        //Datetime-->13位时间戳
        if(TimeUtil.isDateTime(oldValue))
        {
            newValue = TimeUtil.getTimestampByString(oldValue);
        }
        //10位时间戳-->13位时间戳
        else if(oldValue.length() == 10 && TimeUtil.isNumeric(oldValue))
        {
            newValue = oldValue + "000";
        }
        params.put(param, newValue);
    }

    public void removeParam(String param)
    {
        params.remove(param);
    }
}
