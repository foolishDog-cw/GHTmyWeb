package cn.tedu.util;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Tedu
 */
public class DBUtil {
    private static DruidDataSource ds;
    static{
        Properties p=new Properties();
        InputStream is=DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver=p.getProperty("db.driver");
        String url=p.getProperty("db.url");
        String username=p.getProperty("db.username");
        String password=p.getProperty("db.password");
        String maxActive=p.getProperty("db.maxActive");
        String initialSize=p.getProperty("db.initialSize");
        ds=new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setMaxActive(Integer.parseInt(maxActive));
        ds.setInitialSize(Integer.parseInt(initialSize));
    }
    public static Connection getConn() throws SQLException {
        return ds.getConnection();
    }
}
