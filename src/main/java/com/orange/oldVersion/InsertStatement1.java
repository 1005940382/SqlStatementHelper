package com.orange.oldVersion;

import com.orange.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 鞠鹏飞
 * @data 2017/8/23
 * @Description sql插入语句（无参数列表）
 */
public class InsertStatement1 implements ISqlStatement1
{
    /** 插入表名 */
    private String tableName;
    /** 插入参数 */
    private List<String> params;

    public InsertStatement1()
    {
        params = new ArrayList<String>();
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public List<String> getParams()
    {
        return params;
    }

    public void setParams(List<String> params)
    {
        this.params = params;
    }

    public String getSqlStatement()
    {
        StringBuilder sql = new StringBuilder("INSERT INTO `").append(tableName).append("` VALUES (");
        for(int i = 0; i < params.size(); i++)
        {
            StringBuilder param = new StringBuilder("'").append(params.get(i));
            if(i != params.size() - 1)
            {
                param.append("', ");
            }else
            {
                param.append("');");
            }
            String paramString = param.toString();
            if("null".equals(params.get(i)))
            {
                paramString = paramString.replace("'", "");
            }
            sql.append(paramString);
        }
        return sql.toString();
    }

    public void appendParam(String param)
    {
        params.add(param);
    }

    public void appendParam(String param, int index)
    {
        params.add(index, param);
    }

    public void updateParam(String param, int index)
    {
        params.set(index, param);
    }

    public String getParamValue(int index)
    {
        return params.get(index);
    }

    public void translateTimestamp(int index)
    {
        String oldparam = params.get(index);
        String newParam = null;
        if(oldparam.contains("-") && oldparam.contains(":"))
        {
            newParam = TimeUtil.getTimestampByString(oldparam);
        }
        else if(oldparam.length() == 10)
        {
            newParam = oldparam + "000";
        }
        params.set(index, newParam);
    }

    public void removeParam(int index)
    {
        params.remove(index);
    }
}
