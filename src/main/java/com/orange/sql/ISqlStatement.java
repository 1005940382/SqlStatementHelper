package com.orange.sql;

import java.util.Map;

/**
 * @author 鞠鹏飞
 * @data 2017/8/23
 * @Description
 */
public interface ISqlStatement
{
    //getter & setter
    String getTableName();
    Map<String, String> getParams();
    void setTableName(String tableName);
    void setParams(Map<String, String> params);

    /**
     * 获取拼接好的sql语句
     * @return sql语句
     */
    String getSqlStatement();

    /**
     * 新增或修改参数
     * @param param 参数名
     * @param value 参数值
     */
    void putParam(String param, String value);

    /**
     * 根据参数名获取参数值
     * @param param 参数名
     * @return 参数值
     */
    String getParamValue(String param);

    /**
     * 将制定参数名的值转换为13位时间戳
     * @param param 参数名
     */
    void translateTimestamp(String param);

    /**
     * 删除参数
     * @param param 参数名
     */
    void removeParam(String param);
}
                                                  