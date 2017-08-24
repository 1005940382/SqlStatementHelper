package com.orange.oldVersion;

import java.util.List;

/**
 * @author 鞠鹏飞
 * @data 2017/8/23
 * @Description
 */
public interface ISqlStatement1
{

    String getTableName();
    List<String> getParams();
    void setTableName(String tableName);
    void setParams(List<String> params);

    /**
     * 获取拼接好的sql语句
     * @return
     */
    String getSqlStatement();

    /**
     * 添加参数
     * @param param 新参数
     */
    void appendParam(String param);

    /**
     * 在指定位置添加参数
     * @param param 新参数
     * @param index 参数位置
     */
    void appendParam(String param, int index);

    /**
     * 在指定位置更新参数
     * @param param 新参数
     * @param index 参数位置
     */
    void updateParam(String param, int index);

    /**
     * 获取指定位置的参数值
     * @param index 参数位置
     * @return
     */
    String getParamValue(int index);

    /**
     * 将参数的时间格式转换为13位时间戳
     * @param index 参数位置
     */
    void translateTimestamp(int index);

    /**
     * 删除指定位置的参数
     * @param index 参数位置
     */
    void removeParam(int index);
}
                                                  