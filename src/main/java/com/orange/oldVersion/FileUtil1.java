package com.orange.oldVersion;

import com.orange.sql.ISqlStatement;
import com.orange.sql.SqlType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 鞠鹏飞
 * @data 2017/8/23
 * @Description 文件工具类
 */
public class FileUtil1
{
    /**
     * 读取文件
     * @param filePath 文件路径
     */
    public static List<ISqlStatement1> getSqlEntityListByReadFile(String filePath)
    {
        List<ISqlStatement1> sqlStatements = new ArrayList<ISqlStatement1>();
        try
        {
            File file = new File(filePath);
            if(file.isFile() && file.exists())
            {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String lineText = null;
                while((lineText = bufferedReader.readLine()) != null)
                {
                    if(!"".equals(lineText))
                    {
                        System.out.println(lineText);
                        ISqlStatement1 sqlStatement = getSqlEntity(lineText);
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
    public static ISqlStatement1 getSqlEntity(String sql)
    {
        //获取sql语句类型，并创建sql语句实体类
        String[] sqlArray = sql.split(" ");
        String sqlTypeString = sqlArray[0].toUpperCase();
        SqlType sqlType = SqlType.valueOf(sqlTypeString);
        ISqlStatement1 sqlStatement = SqlStatementFactory1.createSqlStatement(sqlType);

        //正则表达式匹配表名
        Pattern patternTable = Pattern.compile("`(.*?)`");
        Matcher matcher = patternTable.matcher(sql);
        while(matcher.find())
        {
            String tableName = matcher.group().replace("`", "");
            sqlStatement.setTableName(tableName);
        }

        //正则表达式匹配sql参数
        List<String> params = new ArrayList<String>();
        Pattern patternParam = Pattern.compile("'(.*?)'|null");
        matcher = patternParam.matcher(sql);
        while(matcher.find())
        {
            String param = matcher.group().replace("'", "");
            params.add(param);
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
