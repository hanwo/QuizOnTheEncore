package model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizerDTO {
	private String quizerId;
	private String password;
	private String nickName;
	private String score;
	private String solvingTime;
	
	public QuizerDTO(String nickName, String score, String solvingTime) {
		super();
		this.nickName = nickName;
		this.score = score;
		this.solvingTime = solvingTime;
	}
}
