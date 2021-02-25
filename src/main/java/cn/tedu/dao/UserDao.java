package cn.tedu.dao;

import cn.tedu.entity.User;
import cn.tedu.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Tedu
 */
public class UserDao {
    public void add(User user){
        try (Connection conn = DBUtil.getConn()) {
            String sql="insert into user values(null,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.executeUpdate();
            System.out.println("新增成功!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean check(String username){
        try (Connection conn = DBUtil.getConn()) {
            String sql="select id from user where username=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean login(User user){
        try (Connection conn = DBUtil.getConn()) {
            String sql = "select id from user where username=? and password=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ResultSet rs=ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
