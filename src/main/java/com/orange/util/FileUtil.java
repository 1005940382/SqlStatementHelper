package com.orange.util;

import com.orange.sql.ISqlStatement;
import com.orange.sql.SqlStatementFactory;
import com.orange.sql.SqlType;

import java.io.*;
import java.util.*;

/**
 * @author 鞠鹏飞
 * @data 2017/8/23
 * @Description 文件工具类
 */
public class FileUtil
{
    /**
     * 读取文件
     * @param filePath 文件路径
     */
    public static List<ISqlStatement> getSqlEntityListByReadFile(String filePath)
    {
        List<ISqlStatement> sqlStatements = new ArrayList<ISqlStatement>();
        try
        {
            File file = new File(filePath);
            if(file.isFile() && file.exists())
            {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String lineText = null;
                for(int i = 0; (lineText = bufferedReader.readLine()) != null; i++)
                {
                    if(!"".equals(lineText))
                    {
                        //TODO 文件的第一个字符
                        if(i == 0)
                        {
                            lineText = lineText.substring(1, lineText.length() - 1) + ";";
                        }
                        else
                        {
                            lineText = lineText.substring(0, lineText.length() - 1) + ";";
                        }
                        System.out.println(lineText);
                        ISqlStatement sqlStatement = getSqlEntity(lineText);
                        sqlStatements.add(sqlStatement);
                    }
                }
                inputStreamReader.close();
            }
            else
            {
                System.out.println("找不到指定的文件");
            }
        }
        catch(Exception e)
        {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return sqlStatements;
    }

    /**
     * 根据sql语句生成sql语句实体
     * @param sql sql语句
     * @return
     */
    public static ISqlStatement getSqlEntity(String sql)
    {
        //获取sql语句类型，并创建sql语句实体类
        String[] sqlArray = sql.split(" ");
        String sqlTypeString = sqlArray[0].toUpperCase();
        SqlType sqlType = SqlType.valueOf(sqlTypeString);
        ISqlStatement sqlStatement = SqlStatementFactory.createSqlStatement(sqlType);

        String[] sqlParam = sql.split("\\(|\\)");

        //匹配表名
        String[] tableNameArray = sqlParam[0].split(" ");
        String tableName = tableNameArray[2].replace("`", "");
        sqlStatement.setTableName(tableName);

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
        sqlStatement.setParams(params);
        return sqlStatement;
    }

    /**
     * 将sql语句实体列表写入文件
     * @param filePath 文件路径
     * @param sqlStatements sql语句实体列表
     */
    public static void writeFileBySqlEntityList(String filePath, List<ISqlStatement> sqlStatements)
    {
        File file = new File(filePath);
        try
        {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            for(ISqlStatement sqlStatement : sqlStatements)
            {
                String contentString = sqlStatement.getSqlStatement() + "\r\n";
                byte[] content = contentString.getBytes();
                fileOutputStream.write(content, 0, content.length);
            }
            fileOutputStream.close();
            System.out.println("写入文件成功");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
