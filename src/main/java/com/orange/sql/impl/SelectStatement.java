package com.orange.sql.impl;

import com.orange.sql.ISqlStatement;
import com.orange.util.TimeUtil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 鞠鹏飞
 * @data 2017/8/24
 * @Description sql查询语句 TODO
 */
public class SelectStatement implements ISqlStatement
{
    /** 查询表名 */
    private String tableName;
    /** 查询参数 */
    private Map<String, String> params;

    public SelectStatement()
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
        return null;
    }

    public void putParam(String param, String value)
    {

    }

    public String getParamValue(String param)
    {
        return null;
    }

    public void translateTimestamp(String param)
    {

    }

    public void removeParam(String param)
    {

    }

    public ISqlStatement getSqlEntityByString(String sql)
    {
        return null;
    }

}
