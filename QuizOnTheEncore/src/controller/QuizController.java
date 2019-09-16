package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Exception.NotExistException;
import model.DAO.Service;
import model.DTO.QuizerDTO;

public class QuizController extends HttpServlet {
//command=quizerUpdateNick
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");
		try {
			if (command.equals("quizerInsertReq")) {
				quizerInsertReq(request, response);
			} else if (command.equals("quizerInsert")) {
				quizerInsert(request, response);
			} else if (command.equals("quizerIdCheck")) {
				quizerIdCheck(request, response);
			} else if (command.equals("quizerUpdateReq")) {
				quizerUpdateReq(request, response);
			} else if (command.equals("quizerUpdateInfoReq")) {
				quizerUpdateInfoReq(request, response);
			}  else if (command.equals("quizerUpdateInfo")) {
				quizerUpdateInfo(request, response);
			} else if (command.equals("quizerDeleteReq")) {
				quizerDeleteReq(request, response);
			} else if (command.equals("quizerDelete")) {
				quizerDelete(request, response);
			} else if (command.equals("quizerDetail")) {
				quizerDetail(request, response);
			} else if (command.equals("quizerLogin")) {
				quizerLogin(request, response);
			} else if (command.equals("quizerLogout")) {
				quizerLogout(request, response);
			} else if (command.equals("quizerAll")) {
				quizerAll(request, response);
			} else if (command.equals("quizAll")) {
				quizAll(request, response);
			} else if (command.equals("updateQuizerResult")) {
				updateQuizerResult(request, response);
			} else if (command.equals("quizerRankAll")) {
				quizerRankAll(request, response);
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
	}

	// 퀴저 가입 요구
	public void quizerInsertReq(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			url = "quizerInsert.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 퀴저 가입 메소드
	protected void quizerInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
		
		String url = "signUpCheck.jsp";
		
		String quizerId = request.getParameter("quizerId");
		String password = request.getParameter("password");
		String nickName = request.getParameter("nickName");

		QuizerDTO quizer = new QuizerDTO(quizerId, password, nickName, "0", "00:00:00");
		boolean result = false;
		try {
			result = Service.checkQuizerId(quizerId);
			url="signUpCheck.jsp";
			if (result) {
				request.setAttribute("message", "이미 존재하는 Id 입니다. 회원 가입시 중복체크 해주세요.");
			} else if(Service.addquizer(quizer)){
				request.setAttribute("quizer", quizer);
				request.setAttribute("message", "가입 완료");
			}
		} catch (Exception s) {
			request.setAttribute("message", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 퀴저 가입시 ID 중복체크
	public void quizerIdCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String url = "idCheck.jsp";
		String quizerId = request.getParameter("id");
		
		boolean result = false;
		try {
			request.setAttribute("quizerId", quizerId);
			result = Service.checkQuizerId(quizerId);
		} catch (Exception s) {
			request.setAttribute("message", s.getMessage());
		}
		if (result) {
			request.setAttribute("message", "이미 존재하는 Id 이거나 사용 할 수 없는 Id 입니다");
		} else {
			request.setAttribute("message", "사용 가능한 Id 입니다");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 퀴저 정보(닉네임) 수정 요구
	public void quizerUpdateInfoReq(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		String quizerId = request.getParameter("quizerId");
		
		try {
			request.setAttribute("quizer", Service.getQuizer(quizerId));
			url = "quizerUpdateInfo.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 퀴저 정보(닉네임) 수정 - 상세정보 확인 jsp[quizerDetail.jsp]
		public void quizerUpdateInfo(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String url = "showError.jsp";
			String quizerId = request.getParameter("quizerId");
			String password = request.getParameter("password");
			String nickName = request.getParameter("nickName");
			String score = request.getParameter("score");
			String solvingTime = request.getParameter("solvingTime");
			
			HttpSession session = request.getSession();
			
			
			boolean result = false;
			QuizerDTO quizer = new QuizerDTO(quizerId, password, nickName, score, solvingTime);
			try {
				result = Service.updateQuizer(quizer);
				if (result) {
					request.setAttribute("quizer", quizer);
					session.setAttribute("quizerId", quizerId);
					request.setAttribute("message", "수정 완료");
					url = "quizerDetail.jsp";
				}
			} catch (Exception s) {
				s.printStackTrace();
				request.setAttribute("errorMsg", s.getMessage());
			}
			request.getRequestDispatcher(url).forward(request, response);
		}
		
		// 퀴저 정보(닉네임) 수정 - 상세정보 확인 jsp[quizerDetail.jsp]
				public void updateQuizerResult(HttpServletRequest request, HttpServletResponse response)
						throws ServletException, IOException {
					String url = "showError.jsp";
					String score = request.getParameter("fscore");
					System.out.println(score);
					String solvingTime = request.getParameter("storeTime");
					System.out.println(solvingTime);
					
					HttpSession session = request.getSession();
					String quizerId = (String) session.getAttribute("quizerId");
					System.out.println(quizerId);
					
					boolean result = false;
					try {
						result = Service.updateQuizerResult(score, solvingTime, quizerId);
						if (result) {
							request.setAttribute("quizer", Service.getQuizer(quizerId));
							session.setAttribute("quizerId", quizerId);
							request.setAttribute("message", "수정 완료");
							url = "quizerDetail.jsp";
						}
					} catch (Exception s) {
						s.printStackTrace();
						request.setAttribute("errorMsg", s.getMessage());
					}
					request.getRequestDispatcher(url).forward(request, response);
				}
		
	// 퀴저 정보 수정 요구
		public void quizerUpdateReq(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String url = "index.html";
			HttpSession session = request.getSession();
			String quizerId = (String) session.getAttribute("quizerId");
			
			
			try {
				request.setAttribute("quizer", Service.getQuizer(quizerId));
				url = "quizerUpdate.jsp";
			} catch (Exception s) {
				request.setAttribute("errorMsg", s.getMessage());
			}
			request.getRequestDispatcher(url).forward(request, response);
		}
	
	// 퀴저 탈퇴 요구
	public void quizerDeleteReq(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		
		HttpSession session = request.getSession();
		String quizerId = (String) session.getAttribute("quizerId");
		
		try {
			request.setAttribute("quizer", Service.getQuizer(quizerId));
			session.setAttribute("quizerId", quizerId);
			url = "quizerDelete.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 퀴저 탈퇴
	public void quizerDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		String quizerId = (String) session.getAttribute("quizerId");

		boolean result = false;
		try {
			result = Service.deleteQuizer(quizerId, password);
			if (result) {
				request.setAttribute("message", "탈퇴 성공");
				url = "index.html";
			} else {
				request.setAttribute("message", "탈퇴 실패");
				url = "quizerDetail.jsp";
				request.setAttribute("quizer", Service.getQuizer(quizerId));
				session.setAttribute("quizerId", quizerId);
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 퀴저 개인 정보 반환
	public void quizerDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		HttpSession session = request.getSession();
		
		String quizerId = (String) session.getAttribute("quizerId");
		
		try {
			request.setAttribute("quizer", Service.getQuizer(quizerId));
			
			url = "quizerDetail.jsp";
		} catch (Exception s) {
			request.setAttribute("message", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 퀴저 로그인시 아이디, 비밀번호 체크
	public void quizerLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
		
		String url = "index.html";
		String quizerId = request.getParameter("quizerId");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
			
		
		int result = -1;
		try {
			result = Service.checkLogQuizer(quizerId, password);
			if(quizerId.equals("admin")) {
				if (password.equals("123")) {
					session.setAttribute("quizerId", quizerId);
					session.setAttribute("password", password);
					url = "adminMain.jsp";
				} else {
					request.setAttribute("message", "비밀번호를 확인해주세요");
					url = "loginCheck.jsp";
				}
			} else if (result == 1) {
				request.setAttribute("message", "환영합니다");
				session.setAttribute("quizerId", quizerId);
				session.setAttribute("password", password);
//				session.setAttribute("scroe", scroe);
				url = "main.jsp";
			} else if (result == 0) {
				request.setAttribute("message", "비밀번호를 확인해주세요");
				url = "loginCheck.jsp";
			} else if (result == -1) {
				request.setAttribute("message", "아이디가 존재하지 않습니다");
				url = "loginCheck.jsp";
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 퀴저 로그아웃
	public void quizerLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		session = null;
		
		response.sendRedirect("index.html");
	}
	
	// 퀴저 모든 정보 반환
	public void quizerAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("quizerAll", Service.getAllQuizers());
			url = "quizerAll.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 퀴즈 Start
	public void quizAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("quizAll", Service.getAllQuiz());
			url = "quiz.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}catch(Exception e) {
			request.setAttribute("errorMSG", e.getMessage());
		}
	}
	
	public void quizerRankAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
			try {
				request.setAttribute("quizerRankAll", Service.rankAllQuizers());
				url = "quizerRank.jsp";
			} catch (Exception s) {
				request.setAttribute("errorMsg", s.getMessage());
			}
		request.getRequestDispatcher(url).forward(request, response);
	}

	
}
