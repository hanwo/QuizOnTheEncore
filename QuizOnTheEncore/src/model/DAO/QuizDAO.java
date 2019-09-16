package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DTO.QuizDTO;
import util.DBUtil;

public class QuizDAO {
	//문제 가져 오기
	public static ArrayList<QuizDTO> getQuizNumber() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QuizDTO> quizN = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from quiz");
			rset = pstmt.executeQuery();
			
			quizN = new ArrayList<QuizDTO>();
			while(rset.next()) {
				quizN.add(new QuizDTO(rset.getInt(1), rset.getString(2), rset.getInt(3), rset.getString(4)));
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		return quizN;
	}
	
//	public static ArrayList<QuizDTO> getQuizQuestion() throws SQLException{
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		ArrayList<QuizDTO> quizN = null;
//		try {
//			con = DBUtil.getConnection();
//			pstmt = con.prepareStatement("select question from quiz");
//			rset = pstmt.executeQuery();
//			
//			quizN = new ArrayList<QuizDTO>();
//			while(rset.next()) {
//				quizN.add(new QuizDTO());
//			}
//		}finally {
//			DBUtil.close(con, pstmt, rset);
//		}
//		return quizN;
//	}
}
