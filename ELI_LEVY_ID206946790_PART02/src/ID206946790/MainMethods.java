package ID206946790;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface MainMethods {
	void questionList();

	void mangerMethods();

	void mainMenu() throws FileNotFoundException, IOException, ClassNotFoundException;

	void autoImport() throws ClassNotFoundException, IOException;

	boolean importQuestions() throws ClassNotFoundException, IOException;
}
