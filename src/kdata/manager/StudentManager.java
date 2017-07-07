package kdata.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentManager {
	//정보추가
	public static int insert(Student stu) throws SQLException {
		Connection con = DBUtil.getConnection();
		String sql = "insert into stuinfo values (?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, stu.getSnum());
		pstmt.setString(2, stu.getSname());
		pstmt.setString(3, stu.getSex());
		
		int result = pstmt.executeUpdate();
		DBUtil.close(con, pstmt);
		
		return result;
	}
	
	public static List<Student> selectAll() throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Student> list=null;
		
		try {
			con = DBUtil.getConnection();
			String sql = "select * from stuinfo order by snum";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				String snumList = rs.getString("snum");
				String snameList = rs.getString("sname");
				String sexList = rs.getString("sex");
				Student s = new Student(snumList,snameList,sexList) ;
				list.add(s);
			}
		} finally {
			DBUtil.close(con, pstmt,rs);
		}
		return list;
		
	}
	
	public static Student selectOne(String sname) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student s = null;
		
		try {
			con = DBUtil.getConnection();
			String sql = "select * from stuinfo where sname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				s = new Student(rs.getString(1),rs.getString(2),rs.getString(3));
			}else {
				s = null;
			}
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return s;

	}

}
