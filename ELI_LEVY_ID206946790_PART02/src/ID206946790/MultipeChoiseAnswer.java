package ID206946790;

import java.io.Serializable;
import java.util.Objects;

public class MultipeChoiseAnswer implements Serializable {
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

	@Override
	public int hashCode() {
		return Objects.hash(choiseAnswer, isTrue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultipeChoiseAnswer other = (MultipeChoiseAnswer) obj;
		return Objects.equals(choiseAnswer, other.choiseAnswer) && isTrue == other.isTrue;
	}
}
