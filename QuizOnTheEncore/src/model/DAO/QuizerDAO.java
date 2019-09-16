package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DTO.QuizerDTO;
import oracle.net.aso.r;
import util.DBUtil;

public class QuizerDAO {
	private QuizerDAO() {
	}

	private static QuizerDAO instance = new QuizerDAO();

	static public QuizerDAO getInstance() {
		return instance;
	}

	// Create - quizer 가입
	public static boolean addQuizer(QuizerDTO quizer) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into quizer values(?, ?, ?, '0', '00:00:00')");
			pstmt.setString(1, quizer.getQuizerId());
			pstmt.setString(2, quizer.getPassword());
			pstmt.setString(3, quizer.getNickName());

			int result = pstmt.executeUpdate();

			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 가입 시 백업 테이블로부터 해당 아이디 비교하여 가입요청 시 가입여부 확인 
		public static boolean checkInsertQuizer(String quizerId) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			boolean result = false;

			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("select quizer_id from backup_Quizer where quizer_id=?");
				pstmt.setString(1, quizerId);
				rset = pstmt.executeQuery();
				if (rset.next()) {
						result = true; // 퀴저 아이디 이전 내역 존재 - 가입 불가능
				}
			} finally {
				DBUtil.close(con, pstmt, rset);
			}
			return result;
		}
	
	// 퀴저 아이디 체크 후 중복 여부 정보 반환
	public static boolean checkQuizerId(String quizerId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean result = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select quizer_id from backup_Quizer where quizer_id=?");
			pstmt.setString(1, quizerId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				return result = true;
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return result;
	}

	// Read - quizerId로 quizer 정보 확인
	public static QuizerDTO getQuizer(String quizerId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		QuizerDTO quizer = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from quizer where quizer_id=?");
			pstmt.setString(1, quizerId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				quizer = new QuizerDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return quizer;
	}

	// Update - quizer nickname 수정하기
	public static boolean updateQuizer(QuizerDTO quizer) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement("update quizer set password=?, nickname=? where quizer_Id=?");
			pstmt.setString(1, quizer.getPassword());
			pstmt.setString(2, quizer.getNickName());
			pstmt.setString(3, quizer.getQuizerId());
			
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// Update - quizer 비밀번호 수정하기
		public static boolean updateQuizerPass(QuizerDTO quizer) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBUtil.getConnection();

				pstmt = con.prepareStatement("update quizer set password=? where quizer_Id=? and password=?");
				pstmt.setString(1, quizer.getPassword());
				pstmt.setString(2, quizer.getQuizerId());
				pstmt.setString(3, quizer.getPassword());

				int result = pstmt.executeUpdate();
				if (result == 1) {
					return true;
				}
			} finally {
				DBUtil.close(con, pstmt);
			}
			return false;
		}

	// Delete - quizerId & password 입력 후 quizer 정보 삭제하기
	public static boolean deleteQuizer(String quizerId, String password) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from quizer where quizer_id=? and password=?");
			pstmt.setString(1, quizerId);
			pstmt.setString(2, password);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 로그인 시 퀴저 확인 
	public static int checkLogQuizer(String quizerId, String password) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = -1;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select password from quizer where quizer_id=?");
			pstmt.setString(1, quizerId);
//			pstmt.setString(2, password);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				if(rset.getString("password") !=null && rset.getString("password").equals(password)) {
					result = 1; // 퀴저 아이디, 비밀번호 일치 확인
				}else {
					result = 0; // 퀴저 아이디는 일치, 비밀번호 불일치 시 0 반환
				}
			}else {
				result = -1; // 퀴저 아이디가 존재 하지 않을 때
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return result;
	}
	
	// 모든 퀴저 정보 반환
	public static ArrayList<QuizerDTO> getAllQuizers() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QuizerDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from quizer");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<QuizerDTO>();
			while(rset.next()){
				list.add(new QuizerDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)) );
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	// Update - quizer 게임 결과
			public static boolean updateQuizerResult(String score, String solvingTime, String quizerId) throws SQLException {
				Connection con = null;
				PreparedStatement pstmt = null;
				try {
					con = DBUtil.getConnection();

					pstmt = con.prepareStatement("update quizer set score=?, solvingTime=? where quizer_Id=?");
					pstmt.setString(1, score);
					pstmt.setString(2, solvingTime);
					pstmt.setString(3, quizerId);

					int result = pstmt.executeUpdate();
					if (result == 1) {
						return true;
					}
				} finally {
					DBUtil.close(con, pstmt);
				}
				return false;
			}
			public static ArrayList<QuizerDTO> rankAllQuizers() throws SQLException{
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				ArrayList<QuizerDTO> list = null;
				try{
					con = DBUtil.getConnection();
					pstmt = con.prepareStatement("select nickName,score,solvingtime, ROW_NUMBER() OVER(order by SCORE DESC, solvingtime desc) AS 순위 from quizer");
					rset = pstmt.executeQuery();
					
					list = new ArrayList<QuizerDTO>();
					while(rset.next()){
						list.add(new QuizerDTO(rset.getString(1),rset.getString(2),rset.getString(3)));
					}
				}finally{
					DBUtil.close(con, pstmt, rset);
				}
				return list;
			}
}
