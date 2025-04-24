package com.auto.utils;

import com.auto.model.LoginCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class DatabaseUtil {

    public static SqlSession getSqlSession() throws IOException {
        //获取配置的资源文件
        Reader reader = Resources.getResourceAsReader("databaseConfig.xml");
        //加载配置文件
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        //返回sqlsession，sqlsession就能够执行配置文件中的sql语句
        SqlSession sqlSession = factory.openSession();

        return sqlSession;
    }
}
