package com.panda.demo;

import org.junit.Test;

import java.sql.*;

/**
 * @author huixiangdou
 * @date 2019-08-12
 */
public class JdbcTest {
    @Test
    public void test1() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String db_url = "jdbc:mysql://127.0.0.1:3306/panda";
        String db_user = "root";
        String db_pwd = "123456";
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //2.获得数据库连接
            conn = DriverManager.getConnection(db_url, db_user, db_pwd);
            //3.关闭自动提交
            conn.setAutoCommit(false);
            //4.sql预编译
            ps = conn.prepareStatement("select * from student;");
//            5.执行修改
//            ps.execute();
            //5.执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(new StringBuilder("id:").append(rs.getString("id")).append("_name:").append(rs.getString("name")));
            }
            //6.事务提交
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (ps != null) {
//                    ps.close();
//                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
