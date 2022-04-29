package ID206946790;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MangerClass {
	List<Question> manualExam = new ArrayList<Question>();
	List<Question> allQuestions = new ArrayList<Question>();
	List<Question> autoExamArray = new ArrayList<Question>();
	List<File> files = new ArrayList<File>();

	private int amountOfQuestions;
	private int size;
	private int testCounter;

	Scanner scan = new Scanner(System.in);

	public MangerClass() {
		amountOfQuestions = 0;
		size = 0;
		testCounter = 1;
	}

	public void setSize(int size) {
		this.size = size;
		manualExam = new ArrayList<Question>((size));
		for (int i = 0; i < size; i++) {
			manualExam.add(i, null);
		}
	}

	public boolean isAllQuestionEmpty() {
		if (allQuestions.isEmpty()) {
			System.out.println(
					"There is no Questions available, Please add Questions or Import Questions list. \nReturning to main menu safely.");
			return true;
		} else {
			return false;
		}
	}

	public boolean addOpenQuestion(String question, String answer) {
		for (int i = 0; i < amountOfQuestions; i++) {
			if (allQuestions.get(i).getQuestion().equals(question)) {
				System.out.println("Question already exists");
				return false;
			}
		}
		allQuestions.add(new OpenQuestion(question, answer));
		System.out.println("Created question #" + (amountOfQuestions + 1));
		amountOfQuestions++;
		return true;
	}

	public boolean addMultilpeChoiceQuestion(MultilpeChoiceQuestion question) {
		for (int i = 0; i < amountOfQuestions; i++) {
			if (allQuestions.get(i).getQuestion().equals(question.getQuestion())) {
				System.out.println("Question already exists");
				return false;
			}
		}
		allQuestions.add(question);
		System.out.println("Created question #" + (amountOfQuestions + 1));
		amountOfQuestions++;
		return true;
	}

	private Question getQuestionById(int questionNumber) {
		return allQuestions.get(questionNumber);
	}

	public String updateAnExistingQuestion(int questionNumber, String question) {
		Question quest = getQuestionById(questionNumber - 1);
		if (quest != null) {
			quest.setQuestion(question);
			return "Question updated successfully.";
		}
		return "Question does not exist";
	}

	public String deleteAnExistingAnswer(int questionNumber, int loction) {
		Question quest = getQuestionById(questionNumber - 1);
		if (quest == null) {
			return "Question does not exist.";
		}
		if (quest instanceof OpenQuestion) {
			return "Cannot delete an Open Question type question.";
		}
		MultilpeChoiceQuestion mQuestion = (MultilpeChoiceQuestion) quest;
		if (loction > mQuestion.getAnswerNumber() || loction < 1) {
			return "Answer does not exist.";
		}
		mQuestion.deleteAnswer(loction);
		return "Answer deleted successfully.";
	}

	public String updateAnAnswer(int questionNumber, int loction, String newAnswer) {
		Question quest = getQuestionById(questionNumber - 1);
		if (quest instanceof OpenQuestion) {
			OpenQuestion open = (OpenQuestion) quest;
			open.setOpenAnswer(newAnswer);
		}
		if (quest instanceof MultilpeChoiceQuestion) {
			MultilpeChoiceQuestion mQuestion = (MultilpeChoiceQuestion) quest;
			if (loction > mQuestion.getAnswerNumber() || loction < 1) {
				return "Answer does not exist.";
			}
			mQuestion.updateAnswer(loction, newAnswer);
		}
		return "Answer updated successfully.";
	}

	public void printAllQuestionAndAnswers() {
		System.out.println("___Printing all Questions and all Answers___");
		if (allQuestions.isEmpty()) {
			System.out.println(
					"There is no Questions available, Please add Questions or Import Questions list. Type '-1' to go back to main menu.");
		}
		for (int i = 0; i < allQuestions.size(); i++) {
			if (allQuestions.get(i) != null) {
				System.out.println(allQuestions.get(i));
			}
		}
	}

	public void printAllQuestions() {
		System.out.println("\n___All Multiple Choice Questions___" + "\n");
		if (allQuestions.isEmpty()) {
			System.out.println(
					"There is no Multiple Questions available, Please add Questions or Import Questions list. Type '-1' to go back to main menu.");
		}
		for (int i = 0; i < allQuestions.size(); i++) {
			if (allQuestions.get(i) != null) {
				if (allQuestions.get(i) instanceof MultilpeChoiceQuestion) {
					System.out.println(allQuestions.get(i).getId() + ") " + allQuestions.get(i).getQuestion());
				}
			}
		}
		System.out.println("\n___All Open Questions___" + "\n");
		if (allQuestions.isEmpty()) {
			System.out.println(
					"There is no Open Questions available, Please add Questions or Import Questions list. Type '-1' to go back to main menu.");
		}
		for (int i = 0; i < allQuestions.size(); i++) {
			if (allQuestions.get(i) != null) {
				if (allQuestions.get(i) instanceof OpenQuestion) {
					System.out.println(allQuestions.get(i).getId() + ") " + allQuestions.get(i).getQuestion());
				}
			}
		}
	}

	public void printOnlyOpenQuestions() {
		System.out.println("\n___All Open Questions___" + "\n");
		if (allQuestions.isEmpty()) {
			System.out.println(
					"There is no Open Questions available, Please add Questions or Import Questions list. Type '-1' to go back to main menu.");
		}
		for (int i = 0; i < allQuestions.size(); i++) {
			if (allQuestions.get(i) != null) {
				if (allQuestions.get(i) instanceof OpenQuestion) {
					System.out.println(allQuestions.get(i).getQuestion());
				}
			}
		}
	}

	public void printOnlyMultilpeChoiceQuestions() {
		System.out.println("\n___All Multipe Choise Questions___" + "\n");
		if (allQuestions.isEmpty()) {
			System.out.println(
					"There is no Multiple Questions available, Please add Questions or Import Questions list. Type '-1' to go back to main menu.");
		}
		for (int i = 0; i < allQuestions.size(); i++) {
			if (allQuestions.get(i) != null) {
				if (allQuestions.get(i) instanceof MultilpeChoiceQuestion) {
					System.out.println(
							"[" + allQuestions.get(i).getId() + "] " + allQuestions.get(i).printQuestionNumber());
				}
			}
		}
	}

	public void printSelectedMultipeChoiseAnswers(int questionNumber) {
		System.out.println("Selected Answers for Question number ->\t" + "[" + questionNumber + "] ");
		Question quest = getQuestionById(questionNumber);
		if (quest instanceof MultilpeChoiceQuestion) {
			MultilpeChoiceQuestion mQuestion = (MultilpeChoiceQuestion) quest;
			for (int i = 0; i < mQuestion.getAnswerNumber(); i++) {
				if (mQuestion.getChoiseAnswer(i) != null) {
					System.out.println("[" + (i + 1) + "] " + mQuestion.getChoiseAnswer(i));
				}
			}
		}
	}

	public void sortAndPrintAutoExam() throws IOException {
		CompareQuestion cq = new CompareQuestion();
		autoExamArray.sort(cq);
		saveFile(getCurrentDate(), autoExamArray);
		for (int i = 0; i < autoExamArray.size(); i++) {
			System.out.println(autoExamArray.get(i));
		}
		System.out.println("Exam created successfully\n" + "The exam contains:" + autoExamArray.size() + " questions.");
		autoExamArray.clear();
	}

	public void sortAndPrintManualExam() {
		for (int i = 0; i < manualExam.size(); i++) {
			if (manualExam.get(i) != null) {
				if (manualExam.get(i) instanceof OpenQuestion) {
					System.out.println(manualExam.get(i).toString());
				}
				if (manualExam.get(i) instanceof MultilpeChoiceQuestion) {
					MultilpeChoiceQuestion mQuestion = (MultilpeChoiceQuestion) manualExam.get(i);
					addBulitInAnswers(mQuestion);
					System.out.println(manualExam.get(i).toString());
				}
			}
		}
		int counter = 0;
		for (int i = 0; i < manualExam.size(); i++) {
			if (manualExam.get(i) != null) {
				counter++;
			}
		}
		System.out.println("Exam created successfully\n" + "The exam contains: " + counter + " questions.");
		try {
			saveFile(getCurrentDate(), manualExam);
		} catch (IOException e) {
			e.printStackTrace();
		}
		manualExam.clear();
	}

	public boolean checkIfQuestionInArray(Question quest) {
		if (quest == null) {
			return false;
		}
		for (int i = 0; i < autoExamArray.size(); i++) {
			if (autoExamArray.get(i) != null) {
				if (autoExamArray.contains(quest)) {
					return true;
				}
			}
		}
		return false;
	}

	public Question generateNewQuestion(int amount) {
		Random rand = new Random();
		while (autoExamArray.size() != amount) {
			Question quest = getQuestionById(rand.nextInt(amount));
			while (checkIfQuestionInArray(quest)) {
				quest = getQuestionById(rand.nextInt(amount));
			}
			return quest;
		}
		return null;
	}

	public int checkAllQuestionLength() {
		return allQuestions.size();
	}

	public void addBulitInAnswers(MultilpeChoiceQuestion answers) {
		int trueCounter = 0;
		int falseCounter = 0;
		for (int i = 0; i < answers.getAnswerNumber(); i++) {
			if (answers.getChoiseAnswer(i).isTrue()) {
				trueCounter++;
			} else {
				falseCounter++;
			}
		
		}
		if (trueCounter == 1) {
			answers.addAnswer(new MultipeChoiseAnswer("Nothing is Correct", false));
			answers.addAnswer(new MultipeChoiseAnswer("More then one answer is correct", false));
		}
		if (trueCounter > 1) {
			answers.addAnswer(new MultipeChoiseAnswer("Nothing is Correct", false));
			answers.addAnswer(new MultipeChoiseAnswer("More then one answer is correct", true));
		}
		if (falseCounter > 0 && trueCounter == 0) {
			answers.addAnswer(new MultipeChoiseAnswer("Nothing is Correct", true));
			answers.addAnswer(new MultipeChoiseAnswer("More then one answer is correct", false));
		}
	}

	public void autoCreateExam(int amount) throws IOException {
		for (int i = 0; i < amount; i++) {
			Question quest = generateNewQuestion(amount);
			if (quest instanceof OpenQuestion) {
				OpenQuestion open = (OpenQuestion) quest;
				autoExamArray.add(open);
			}
			if (quest instanceof MultilpeChoiceQuestion) {
				MultilpeChoiceQuestion mQuestion = (MultilpeChoiceQuestion) quest;
				autoExamArray.add(mQuestion);
				addBulitInAnswers(mQuestion);
			}
		}
		sortAndPrintAutoExam();

	}

	public boolean checkInstanceofQuestion(int questionNum) {
		Question quest = getQuestionById(questionNum);
		if (quest instanceof OpenQuestion) {
			return false;
		}
		if (quest instanceof MultilpeChoiceQuestion) {
			return true;
		}
		return false;
	}

	public void getMultipeChoiseAnswer(int questionNum) {
		Question quest = getQuestionById(questionNum);
		if (quest instanceof MultilpeChoiceQuestion) {
			MultilpeChoiceQuestion mQuestion = (MultilpeChoiceQuestion) quest;
			for (int i = 0; i < mQuestion.getAnswerNumber(); i++) {
				if (!mQuestion.getChoiseAnswer(i).getChoiseAnswer().isEmpty()) {
					System.out.println("[" + (i + 1) + "] " + mQuestion.getChoiseAnswer(i));
				}
				else {
					System.out.println("No answers available.");
				}
			}
		}
	}

	public boolean addMultipeChoiseQuestionToManualExam(MultilpeChoiceQuestion question) {
		for (int i = 0; i < size; i++) {
			if (manualExam.get(i) != null) {
				if (manualExam.contains(question)) {
					System.out.println("Question already exists");
					return false;
				}
			}
		}
		if (manualExam.add(question)) {
			return true;
		}
		return false;
	}

	public boolean addOpenQuestionToManualExam(String question, String answer) {
		for (int i = 0; i < size; i++) {
			if (manualExam.get(i) != null) {
				if (manualExam.get(i).getQuestion().equals(question)) {
					System.out.println("Question already exists");
					return false;
				}
			}
		}
		for (int i = 0; i < manualExam.size(); i++) {
			if (manualExam.get(i) == null || manualExam.get(i).getQuestion().isEmpty()) {
				manualExam.add(new OpenQuestion(question, answer));
				System.out.println("Added question #" + (i + 1));
				return true;
			}
		}
		return false;
	}

	public boolean addQuestionToManualExam(int questionNumber, List<Integer> answersArray) {
		Question question = getQuestionById(questionNumber);
		if (question instanceof OpenQuestion) {
			OpenQuestion open = (OpenQuestion) question;
			addOpenQuestionToManualExam(open.getQuestion(), open.getOpenAnswer());
			return true;
		}
		if (question instanceof MultilpeChoiceQuestion) {
			MultilpeChoiceQuestion quest = (MultilpeChoiceQuestion) question;
			MultilpeChoiceQuestion aQ = new MultilpeChoiceQuestion(quest.getQuestion());
			for (int i = 0; i < answersArray.size(); i++) {
				aQ.addAnswer(new MultipeChoiseAnswer(quest.getChoiseAnswer(answersArray.get(i) - 1)));
			}
			addMultipeChoiseQuestionToManualExam(aQ);
			return true;
		}
		return false;
	}

	public int intException(Scanner e) {
		int num = 0;
		boolean input = false;
		do {
			input = false;
			try {
				num = e.nextInt();
			} catch (InputMismatchException exception) {
				System.err.println("Invalid input ! Expected numercial value. ");
				input = true;
			}
			e.nextLine();
		} while (input);
		return num;
	}

	public boolean booleanException(Scanner e) {
		boolean input = false;
		boolean invalidInput = false;
		do {
			invalidInput = false;
			try {
				invalidInput = e.nextBoolean();
			} catch (InputMismatchException exception) {
				System.err.println("Invalid input ! Expected boolean value, (true/false). ");
				invalidInput = true;
			}
			e.nextLine();
		} while (invalidInput);
		return input;
	}

	public void saveFile(String date, List<Question> manualExam) throws IOException, FileAlreadyExistsException {
		BufferedWriter questionsFile = new BufferedWriter(
				new FileWriter("Exams/" + "Exam_" + date + "_Questions_" + "(" + testCounter + ") " + ".txt"));
		BufferedWriter answersFile = new BufferedWriter(
				new FileWriter("Exams/" + "Exam_" + date + "_Solutions_" + "(" + testCounter + ") " + ".txt"));
		testCounter++;
		for (int i = 0; i < manualExam.size(); i++) {
			if (manualExam.get(i) != null) {
				questionsFile.write(manualExam.get(i).getQuestion() + "\n");
			}
		}
		for (int i = 0; i < manualExam.size(); i++) {
			if (manualExam.get(i) instanceof MultilpeChoiceQuestion) {
				MultilpeChoiceQuestion MultipleQuest = (MultilpeChoiceQuestion) manualExam.get(i);
				for (int j = 0; j < MultipleQuest.getAnswerNumber(); j++) {
					answersFile.write(MultipleQuest.getChoiseAnswer(j).getChoiseAnswer() + " - "
							+ MultipleQuest.getChoiseAnswer(j).isTrue() + "\n");
				}
			}
			if (manualExam.get(i) instanceof OpenQuestion) {
				OpenQuestion oQuestion = (OpenQuestion) manualExam.get(i);
				answersFile.write(oQuestion.getOpenAnswer() + "\n");
			}
		}
		questionsFile.flush();
		questionsFile.close();
		answersFile.flush();
		answersFile.close();

	}

	public void saveOnExitBinaryFile()
			throws IOException, FileNotFoundException, ClassNotFoundException, FileAlreadyExistsException {
		try {
			Files.createDirectory(Paths.get("BinaryFile/"));
		} catch (FileAlreadyExistsException e) {
			e.getMessage();
		}
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("BinaryFile/Exam.dat"));
		for (int i = 0; i < allQuestions.size(); i++) {
			outFile.writeObject(allQuestions.get(i));
		}
		outFile.close();
		System.out.println("Binary file created.");
	}

	public void readBinaryFile() throws ClassNotFoundException, IOException {
		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("BinaryFile/Exam.dat"))) {
			while (true) {
				allQuestions.add((Question) inFile.readObject());
			}
		} catch (EOFException e) {
			System.out.println("Binary File -> \n");
		}
	}

	private final static String getCurrentDate() {
		String pattern = "MM-dd-yyyy";
		DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(new Date());
	}

	public void printAndSaveManualExamToFile() throws IOException {
		CompareQuestion cq = new CompareQuestion();
		manualExam.sort(cq);
		saveFile(getCurrentDate(), manualExam);
		for (int i = 0; i < manualExam.size(); i++) {
			if (manualExam.get(i) != null) {
				System.out.println(manualExam.get(i));
			}
		}
		manualExam.clear();
	}

	public void sortQuestionsByAnswersLength() {
		CompareQuestion cq = new CompareQuestion();
		allQuestions.sort(cq);
		System.out.println("___Printing all Questions and all Answers___");
		for (int i = 0; i < allQuestions.size(); i++) {
			if (allQuestions.get(i) != null) {
				System.out.println(allQuestions.get(i));
			}
		}
	}

	public void createCopyOfAnExistingExam() throws IOException, FileNotFoundException {
		int countFile = 0;
		File[] curDir = new File("Exams/").listFiles();
		for (File f : curDir) {
			if (f.isFile()) {
				countFile++;
				System.out.println(countFile + ") " + f.getName());
				files.add(f);
			}
		}
	}

	public void makeCopyOfChosenFile(int chosenFile) throws IOException, FileNotFoundException {
		BufferedReader read = new BufferedReader(new FileReader(files.get(chosenFile - 1)));
		FileWriter copiedFile = new FileWriter("ExamCopy/" + "Copy of" + files.get(chosenFile - 1).getName());
		String count;
		while ((count = read.readLine()) != null) {
			copiedFile.write(count + "\n");
		}
		System.out.println("Created copy of" + files.get(chosenFile - 1).getName());
		read.close();
		copiedFile.flush();
		copiedFile.close();
	}

	public void addQuestionsFromBinaryFile() throws ClassNotFoundException, IOException {

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("BinaryFile/Exam.dat"))) {
			while (true) {
				allQuestions.add((Question) in.readObject());
			}
		} catch (EOFException e) {
			System.out.println("Questions added from binary file successfully");
		}
		for (int i = 0; i < allQuestions.size(); i++) {
			Question quest = null;
			if (i == 0) {
				quest = getQuestionById(i + 1);
			} else {
				quest = getQuestionById(i);
			}
			if (quest != null) {
				quest.setQuestionsId(allQuestions);
				break;
			}
		}

	}

}