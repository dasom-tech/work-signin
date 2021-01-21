package work03_1224;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 데이터 관련 작업처리용 클래스(DB연동)
public class MemberDAO {
	final String DRIVER = "oracle.jdbc.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USER = "test";
	final String PASSWORD = "test";
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public MemberDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/*
	//id값을 받아서 DB에서 데이터 조회 후 결과값을 MemberVO에 담아서 리턴하는 메소드
	public MemberVO selectId(String id) {
		MemberVO member = null;
		//DB연동작업(SELECT)
		//2. DB 연결하고 Connection 객체 생성
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD );
			
			//3. SQL 실행을 위한 준비
			String sql = ""
					+ "SELECT ID, PW, NAME, EMAIL "
					+ "	 FROM MEM "
					+ " WHERE ID = ? ";
			//3-1. SQL을 전달하고 실행준비 요청
			pstmt = conn.prepareStatement(sql);
			//3-2. SQL문장의 ? 위치에 값 설정
			pstmt.setString(1, id);
			
			//3-3. SQL 문장 실행 요청
			rs = pstmt.executeQuery();	
			
			//4. SQL 실행결과에 대한 처리
			if(rs.next() ) {
				member = new MemberVO();
				member.setId(rs.getString("ID")); //이렇게 ID로 표현하는게 더 좋음
				member.setName(rs.getString("NAME"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setEmail(rs.getString("EMAIL"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return member;
		
	}
	//테이블 데이터 전체 가져와서 List에 담아서 리턴
	public List<MemberVO> selectAll() {
		ArrayList<MemberVO> list = null;
		//ex) arraylist 클릭하고 ctrl+t 누르면 상관관계 볼 수 있음
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD );
			
			String sql = ""
					+ "SELECT ID, NAME, PASSWORD, EMAIL "
					+ "	 FROM MEMBER_T "
					+ " ORDER BY ID ";
			//3-1. DB에 SQL문 전달하고 실행준비
			pstmt = conn.prepareStatement(sql);
			//3-2. SQL문 실행요청
			rs = pstmt.executeQuery();
			
			list = new ArrayList<MemberVO>();
			//4. 결과값에 대한 처리
			while(rs.next()) {
				MemberVO member = new MemberVO(
						rs.getString("ID"),
						rs.getString("NAME"),
						rs.getString("PASSWORD"),
						rs.getString("EMAIL")
						);						
				
				list.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	*/
	
	
	//전달받은 MemberVO 데이터를 DB에 입력
	public int insertData(MemberVO member) {
		int result = 0;
		//DB연동작업 : VO데이터 DB에 입력
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD );
			
			//3. SQL 실행을 위한 준비
			String sql = ""
					+ "INSERT INTO MEM "
					+ "	 	  (ID, PW, NAME, EMAIL) "
					+ "VALUES(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
						
			//SQL 문장 실행 요청
			result = pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}		
		
		return result;
		
	}
	
	private void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null) conn.close();	//데이터 중복값 허용 안 하므로 다시 실행했을 때 오류남. 데이터 수정해서 다시 실행하면 됨.
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null) conn.close();	//데이터 중복값 허용 안 하므로 다시 실행했을 때 오류남. 데이터 수정해서 다시 실행하면 됨.
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
