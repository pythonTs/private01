package kdata.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**자바 DOC
 * DB서버 연결 관리하는 클래스
 * @author choi
 * Connection 생성, 종료
*/
public class DBUtil {
	private static final String CON_URL="jdbc:oracle:thin:@localhost:1521";
	private static final String CON_NAME="zook3280";
	private static final String CON_PWD="1234";
	private static final String DRIVER_NAME="oracle.jdbc.driver.OracleDriver";
	
	static {
		//JVM 구조에서 클래스 로드될떄 한번만 실행되는거. static영역!!!!!!
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	DBUtil(){
		//생성자/ new될때마다 실행.

	}
	/**
	 * 데이터베이스 서버 연결하는 메소드
	 * @return Connection 데이터베이스 연결전보 객체 반환
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CON_URL,CON_NAME,CON_PWD);
	}
	//필드가 없으면 굳이 new 할 필요가 없음.
	//static 메소드로 만들어서 클래스로 접급하면 됨.
	/**
	 * 데이터베이스 연결시 사용한 자원 해제
	 * @throws SQLException 
	 * 
	 */
	public static void close(Connection con, PreparedStatement pstmt) throws SQLException {
		pstmt.close();
		con.close();
	}
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		rs.close();
		pstmt.close();
		con.close();
	}
}
