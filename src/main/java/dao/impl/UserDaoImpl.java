package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import entity.User;
import utils.DBUtils;

/**
*@author  wsz
*@createdTime 2018年3月15日
*/
public class UserDaoImpl implements UserDao{

	private PreparedStatement pstmt = null;
	
	private ResultSet rs = null;
	
	
	/**
	 * 登录判断
	 * @param user
	 * @return
	 */
	@Override
	public User login(User user) {
		Connection conn = DBUtils.getConn();
		
		User temp = new User();
		String sql = "select id,username,password from t_user where username=? and password=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			while(rs.next()){
				temp.setId(rs.getInt("id"));
				temp.setUsername(rs.getString("username"));
				temp.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.clossAll(conn, pstmt, rs);
		}
		return temp;
	}

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@Override
	public boolean createUser(User user) {
		Connection conn = DBUtils.getConn();
		String sql = "insert into t_user(username,password) values(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			DBUtils.clossAll(conn, pstmt, rs);
		}
		return true;
	}

	/**
	 * 获取所有用户
	 * @return
	 */
	@Override
	public List<User> getAllUser() {
		Connection conn = DBUtils.getConn();
		List<User> list = new ArrayList<User>();
		String sql = "select id,username,password from t_user";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				User temp = new User();
				temp.setId(id);
				temp.setUsername(username);
				temp.setPassword(password);
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.clossAll(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	@Override
	public List<User> findUserById(int id) {
		Connection conn = DBUtils.getConn();
		List<User> list = new ArrayList<User>();
		String sql = "select id,username,password from t_user where id =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int tid = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				User temp = new User();
				temp.setId(tid);
				temp.setUsername(username);
				temp.setPassword(password);
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据username查询用户
	 * @param name
	 * @return
	 */
	@Override
	public List<User> findUserByName(String name) {
		Connection conn = DBUtils.getConn();
		List<User> list = new ArrayList<User>();
		String sql = "select id,username,password from t_user where username like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int tid = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				User temp = new User();
				temp.setId(tid);
				temp.setUsername(username);
				temp.setPassword(password);
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	@Override
	public boolean updateUser(User user) {
		Connection conn = DBUtils.getConn();
		String sql = "update t_user set username =?, password=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getId());
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.clossAll(conn, pstmt, rs);
		}
		return false;
	}

	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	@Override
	public boolean deleteUser(User user) {
		Connection conn = DBUtils.getConn();
		String sql = "delete from t_user where id =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getId());
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.clossAll(conn, pstmt, rs);
		}
		return false;
	}

	@Override
	public boolean updatePwd(int uid, String password) {
		Connection conn = DBUtils.getConn();
		String sql = "update t_user set password=? where id =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setInt(2, uid);
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.clossAll(conn, pstmt, rs);
		}
		return false;
	}

	@Override
	public boolean deleteUserById(int uid) {
		Connection conn = DBUtils.getConn();
		String sql = "delete from t_user where id =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.clossAll(conn, pstmt, rs);
		}
		return false;
	}

}
