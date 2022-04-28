package ID_206946790_ELI_LEVY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MultilpeChoiceQuestion extends Question implements Serializable{
	
	NewSet<MultipeChoiseAnswer> choiceAnswer = new NewSet<MultipeChoiseAnswer> ();
	private int answerNumber;
	public MultilpeChoiceQuestion(String question) {
		super(question);
		answerNumber =  0;
	}
	
	public String addAnswer(MultipeChoiseAnswer answer) {
		for(int i = 0; i <= answerNumber; i++) {
				if(choiceAnswer.contains(answer)) {
					return "Answer already exist.";
				}
		}
		answerNumber++;
		if(answerNumber >= choiceAnswer.size()) {
			choiceAnswer.add(answerNumber - 1, answer);
		}
		return "Answer added successfully";
	}
	public void deleteAnswer(int index) {
		choiceAnswer.set(index - 1, null);
		answerNumber--;
	}
	public void updateAnswer(int index, String Answer) {
		choiceAnswer.get(index - 1).setChoiseAnswer(Answer);
	}
	public MultipeChoiseAnswer getChoiseAnswer(int index) {
		return choiceAnswer.get(index);
	}
	public int getAnswerNumber() {
		return answerNumber;
	}
	public String getQuestion() {
		return question;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[" + getId() + "] Multipe Choise Question: \n" + getQuestion() + "\n");
		for(int i = 0; i < choiceAnswer.size(); i++) {
			if(choiceAnswer.get(i) != null) {
				sb.append("Answer number: [" + (i + 1) + "] " + choiceAnswer.get(i).toString());
				sb.append("[Correct or not]: " + choiceAnswer.get(i).isTrue() + "\n");
			}
		}
		return sb.toString();
	}
}
