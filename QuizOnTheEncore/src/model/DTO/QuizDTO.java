package model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
	private int quizNumber;
	private String question;
	private int answer;
	private String content;
	
	
//	public QuizDTO(int quizNumber, String question, String content) {
//		this.quizNumber = quizNumber;
//		this.question = question;
//		this.content = content;
//	}
	
	
}
