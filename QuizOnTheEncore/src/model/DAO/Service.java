package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Exception.MessageException;
import Exception.NotExistException;
import model.DTO.QuizDTO;
import model.DTO.QuizerDTO;

public class Service {
	
	// 미존재시 예외처리
	public static void notExistquizer(String quizerId) throws NotExistException, SQLException{
		QuizerDTO quizer = QuizerDAO.getQuizer(quizerId);
		if(quizer == null){
			throw new NotExistException("검색하는 Quizer가 미 존재합니다.");
		}
	}
	
	// 가입 - 퀴저 가입
	public static boolean addquizer(QuizerDTO quizer) throws MessageException{
		boolean result = false;
		try{
			result = QuizerDAO.addQuizer(quizer);
		}catch(SQLException s){
			if(!result) {
			throw new MessageException("회원 가입에 실패했습니다. 다시 시도해 주세요.");
			}
		}
		return result;
	}
	
	// 가입 시 퀴저 정보 일치 확인 - true 반환시 이전 내역 존재;가입 불가능
		public static boolean checkInsertQuizer(String quizerId) throws SQLException, NotExistException{
			boolean result = false;
			try {
				result = QuizerDAO.checkInsertQuizer(quizerId);
			} catch (Exception s) {
				throw new NotExistException(".");
			}
			return result;
		}
	
	// 퀴저 아이디 체크 후 가입 여부 정보 반환
	public static boolean checkQuizerId(String quizerId) throws MessageException{
		boolean result = false;
		try{
			result = QuizerDAO.checkQuizerId(quizerId);
		}catch(SQLException s){
			throw new MessageException("이미 존재하는 ID입니다 다시 시도 하세요");
		}
		return result;
	}
	
	// 퀴저 비밀번호 확인 후 닉네임 변경
	public static boolean updateQuizer(QuizerDTO quizer) throws SQLException, NotExistException{		
//		notExistquizer(quizer.getQuizerId());
		boolean result = QuizerDAO.updateQuizer(quizer);
		if(!result){
			throw new NotExistException("Quizer NickName 변경에 실패했습니다");
		}
		return result;
	}
	
	// 퀴저 비밀번호 확인 후 비밀번호 변경
		public static boolean updateQuizerPass(QuizerDTO quizer) throws SQLException, NotExistException{		
			notExistquizer(quizer.getPassword());
			boolean result = QuizerDAO.updateQuizerPass(quizer);
			if(!result){
				throw new NotExistException("Quizer Password 변경에 실패했습니다");
			}
			return result;
		}
	
	// 퀴저 아이디 & 비밀번호 일치 시 탈퇴
	public static boolean deleteQuizer(String quizerId, String password) throws SQLException, NotExistException{
//		notExistquizer(quizerId);
//		notExistquizer(password);
		boolean result = QuizerDAO.deleteQuizer(quizerId, password);
		if(!result){
			throw new NotExistException("Quizer 탈퇴에 실패했습니다");
		}
		return result;
	}
	
	// 퀴저 아이디 일치 시 퀴저 정보 반환
	public static QuizerDTO getQuizer(String quizerId) throws SQLException, NotExistException{
		QuizerDTO quizer = QuizerDAO.getQuizer(quizerId);
		if(quizer == null){
			throw new NotExistException("검색한 Quizer 정보가 미 존재합니다.");
		}
		return quizer;
	}

	// 로그인 시 퀴저 정보 일치 확인
	public static int checkLogQuizer(String quizerId, String password) throws SQLException, NotExistException{
		int result = QuizerDAO.checkLogQuizer(quizerId, password);
		return result;
	}
	
	// 모든 퀴저 반환
	public static ArrayList<QuizerDTO> getAllQuizers() throws SQLException{
		return QuizerDAO.getAllQuizers();
	}
	
	// 모든 퀴즈 반환
	public static ArrayList<QuizDTO> getAllQuiz() throws SQLException{
		return QuizDAO.getQuizNumber();
	}
	
	// 퀴저 게임후 score, time 업데이트
		public static boolean updateQuizerResult(String score, String solvingTime, String quizerId) throws SQLException, NotExistException{		
			boolean result = QuizerDAO.updateQuizerResult(score, solvingTime, quizerId);
			if(!result){
				throw new NotExistException("Quizer Game result 변경에 실패했습니다");
			}
			return result;
		}
		
		public static ArrayList<QuizerDTO> rankAllQuizers() throws SQLException{
			return QuizerDAO.rankAllQuizers();
		}
	
}
