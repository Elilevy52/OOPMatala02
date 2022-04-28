package ID_206946790_ELI_LEVY;

import java.io.Serializable;

public class MultipeChoiseAnswer implements Serializable{
	private String choiseAnswer;
	private boolean isTrue;
	
	
	public MultipeChoiseAnswer(String choiseAnswer, boolean isTrue) {
		setChoiseAnswer(choiseAnswer);
		setTrue(isTrue);
	}
	
	public MultipeChoiseAnswer(MultipeChoiseAnswer other) {
		setChoiseAnswer(other.choiseAnswer);
		setTrue(other.isTrue);
	}
	
	public boolean isTrue() {
		return isTrue;
	}


	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}


	public String getChoiseAnswer() {
		return choiseAnswer;
	}


	public boolean setChoiseAnswer(String choiseAnswer) {
		this.choiseAnswer = choiseAnswer;
		return true;
	}

	public String toString() {
		return choiseAnswer;
	}
}
