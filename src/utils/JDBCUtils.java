package utils;

import com.alibaba.druid.pool.DruidDataSource;

/*
jdbc连接池的工具包。
 */
public class JDBCUtils {
    private static DruidDataSource dataSource;
    static {
        dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql:///chls?useUnicode=true&characterEncoding=UTF-8");
    }
    public static DruidDataSource getDataSource() {
        return dataSource;
    }
}
