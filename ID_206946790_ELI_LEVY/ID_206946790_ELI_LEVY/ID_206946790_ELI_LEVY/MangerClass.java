package ID_206946790_ELI_LEVY;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileAlreadyExistsException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class MangerClass implements Serializable{
	private static final long serialVersionUID = 1299802132430594458L;
	private Question[] allQuestions;
	private Question[] manualExamArray;
	private int amountOfQuestions;
	private int size;
	private int testCounter;
	Scanner scan = new Scanner(System.in);

	public MangerClass() {
		allQuestions = new Question[1];
		amountOfQuestions = 0;
		size = 0;
		testCounter = 1;
	}

	public void setSize(int size) {
		this.size = size;
		manualExamArray = new Question[size];
	}

	public boolean addOpenQuestion(String question, String answer) {
		for (int i = 0; i < amountOfQuestions; i++) {
			if (allQuestions[i].getQuestion().equals(question)) {
				System.out.println("Question already exists");
				return false;
			}
		}
		if (amountOfQuestions == allQuestions.length) {
			allQuestions = Arrays.copyOf(allQuestions, allQuestions.length * 2);
		}
		allQuestions[amountOfQuestions] = new OpenQuestion(question, answer);
		System.out.println("Created question #" + (amountOfQuestions + 1));
		amountOfQuestions++;
		return true;
	}

	public boolean addMultilpeChoiceQuestion(MultilpeChoiceQuestion question) {
		for (int i = 0; i < amountOfQuestions; i++) {
			if (allQuestions[i].getQuestion().equals(question.getQuestion())) {
				System.out.println("Question already exists");
				return false;
			}
		}
		if (amountOfQuestions == allQuestions.length) {
			allQuestions = Arrays.copyOf(allQuestions, allQuestions.length * 2);
		}
		allQuestions[amountOfQuestions] = question;
		System.out.println("Created question #" + (amountOfQuestions + 1));
		amountOfQuestions++;
		return true;
	}

	private Question getQuestionById(int questionNumber) {
		if (questionNumber == allQuestions.length) {
			return null;
		}
		if (allQuestions[questionNumber] == null) {
			getQuestionById(questionNumber + 1);
		}
		for (int i = 0; i < allQuestions.length; i++) {
			while (allQuestions[i] != null) {
				if (allQuestions[i].getId() == questionNumber) {
					return allQuestions[i];
				}
				break;
			}
		}
		return null;
	}

	public String updateAnExistingQuestion(int questionNumber, String question) {
		Question quest = getQuestionById(questionNumber);
		if (quest != null) {
			quest.setQuestion(question);
			return "Question updated successfully.";
		}
		return "Question does not exist";
	}

	public String deleteAnExistingAnswer(int questionNumber, int loction) {
		Question quest = getQuestionById(questionNumber);
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
		Question quest = getQuestionById(questionNumber);
		if (quest == null) {
			return "Question does not exist.";
		}
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
		for (int i = 0; i < allQuestions.length; i++) {
			if (allQuestions[i] != null) {
				System.out.println(allQuestions[i]);
			}
		}
	}

	public void printAllQuestions() {
		System.out.println("\n___All Multipe Choise Questions___" + "\n");
		for (int i = 0; i < allQuestions.length; i++) {
			if (allQuestions[i] != null) {
				if (allQuestions[i] instanceof MultilpeChoiceQuestion) {
					System.out.println(allQuestions[i].getId() + ") " + allQuestions[i].getQuestion());
				}
			}
		}
		System.out.println("\n___All Open Questions___" + "\n");
		for (int i = 0; i < allQuestions.length; i++) {
			if (allQuestions[i] != null) {
				if (allQuestions[i] instanceof OpenQuestion) {
					System.out.println(allQuestions[i].getId() + ") " + allQuestions[i].getQuestion());
				}
			}
		}
	}

	public void printOnlyOpenQuestions() {
		System.out.println("\n___All Open Questions___" + "\n");
		for (int i = 0; i < allQuestions.length; i++) {
			if (allQuestions[i] != null) {
				if (allQuestions[i] instanceof OpenQuestion) {
					System.out.println(allQuestions[i].getQuestion());
				}
			}
		}
	}

	public void printOnlyMultilpeChoiceQuestions() {
		System.out.println("\n___All Multipe Choise Questions___" + "\n");
		for (int i = 0; i < allQuestions.length; i++) {
			if (allQuestions[i] != null) {
				if (allQuestions[i] instanceof MultilpeChoiceQuestion) {
					System.out.println("[" + allQuestions[i].getId() + "] " + allQuestions[i].printQuestionNumber());
				}
			}
		}
	}

	public void printSelectedMultipeChoiseAnswers(int questionNumber) {
		System.out.println("Selected Answers for Question number ->\t" + "[" + questionNumber + "] ");
		Question quest = getQuestionById(questionNumber);
		if (quest instanceof MultilpeChoiceQuestion) {
			MultilpeChoiceQuestion mQuestion = (MultilpeChoiceQuestion) quest;
			for (int i = 0; i < 10; i++) {
				if (mQuestion.getChoiseAnswer(i) != null) {
					System.out.println("[" + (i + 1) + "] " + mQuestion.getChoiseAnswer(i));
				}
			}
		}
	}

	public void sortAndPrintAutoExam(Question[] arr) {
		Question temp;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i].getQuestion().compareTo(arr[j].getQuestion()) > 0) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		try {
			saveFile(getCurrentDate(), arr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				System.out.println(arr[i]);
			}
		}
		System.out.println("Exam created successfully\n" + "The exam contains:" + arr.length + " questions.");
	}

	public void sortAndPrintManualExam(int[] answersArray) {
		for (int i = 0; i < manualExamArray.length; i++) {
			if (manualExamArray[i] != null) {
				if (manualExamArray[i] instanceof OpenQuestion) {
					System.out.println(manualExamArray[i].toString());
				}
				if (manualExamArray[i] instanceof MultilpeChoiceQuestion) {
					MultilpeChoiceQuestion mQuestion = (MultilpeChoiceQuestion) manualExamArray[i];
					addBulitInAnswers(mQuestion);
					System.out.println(manualExamArray[i].toString());
				}
			}
		}
		int counter = 0;
		for (int i = 0; i < manualExamArray.length; i++) {
			if (manualExamArray[i] != null) {
				counter++;
			}
		}
		System.out.println("Exam created successfully\n" + "The exam contains: " + counter + " questions.");
		try {
			saveFile(getCurrentDate(), manualExamArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean checkIfQuestionInArray(Question[] arr, Question quest) {
		if (quest == null) {
			return false;
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				if (arr[i].getQuestion().equals(quest.getQuestion())) {
					return true;
				}
			}
		}
		return false;
	}

	public Question generateNewQuestion(Question[] array) {
		Random rand = new Random();
		Question quest = null;
		while (quest == null) {
			int n = rand.nextInt(allQuestions.length);
			quest = getQuestionById(n);
			if (checkIfQuestionInArray(array, quest)) {
				quest = null;
			}

		}
		return quest;
	}

	public int checkAllQuestionLength() {
		int counter = 0;
		for (int i = 0; i < allQuestions.length; i++) {
			if (allQuestions[i] != null) {
				counter++;
			}
		}
		return counter;
	}

	public void addBulitInAnswers(MultilpeChoiceQuestion answers) {
		int trueCounter = 0;
		int falseCounter = 0;
		for (int i = 0; i < 10; i++) {
			if (answers.getChoiseAnswer(i) != null) {
				if (answers.getChoiseAnswer(i).isTrue()) {
					trueCounter++;
				} else {
					falseCounter++;
				}
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

	public void autoCreateExam(int amount) {
		Question[] autoExamArray = new Question[amount];
		for (int i = 0; i < amount; i++) {
			Question quest = generateNewQuestion(autoExamArray);
			if (quest instanceof OpenQuestion) {
				OpenQuestion open = (OpenQuestion) quest;
				autoExamArray[i] = open;
			}
			if (quest instanceof MultilpeChoiceQuestion) {
				MultilpeChoiceQuestion mQuestion = (MultilpeChoiceQuestion) quest;
				autoExamArray[i] = mQuestion;
				addBulitInAnswers(mQuestion);
			}
		}
		sortAndPrintAutoExam(autoExamArray);
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
			for (int i = 0; i < 10; i++) {
				if (mQuestion.getChoiseAnswer(i) != null) {
					System.out.println("[" + (i + 1) + "] " + mQuestion.getChoiseAnswer(i));
				}
			}
		}
	}

	public boolean addMultipeChoiseQuestionToManualExam(MultilpeChoiceQuestion question, int[] answersArray) {
		for (int i = 0; i < size; i++) {
			if (manualExamArray[i] != null) {
				if (manualExamArray[i].getQuestion().equals(question.getQuestion())) {
					System.out.println("Question already exists");
					return false;
				}
			}
		}
		for (int i = 0; i < manualExamArray.length; i++) {
			if (manualExamArray[i] == null) {
				MultilpeChoiceQuestion test = new MultilpeChoiceQuestion(question.getQuestion());
				manualExamArray[i] = test;
				for (int j = 0; j < answersArray.length; j++) {
					if (answersArray[j] != 0) {
						int temp = answersArray[j];
						test.addAnswer(new MultipeChoiseAnswer(question.getChoiseAnswer(temp - 1)));
					}
				}
				addBulitInAnswers(test);
				System.out.println("Added question #" + (i + 1));
				return true;
			}
		}
		return false;
	}

	public boolean addOpenQuestionToManualExam(String question, String answer) {
		for (int i = 0; i < size; i++) {
			if (manualExamArray[i] != null) {
				if (manualExamArray[i].getQuestion().equals(question)) {
					System.out.println("Question already exists");
					return false;
				}
			}
		}
		for (int i = 0; i < manualExamArray.length; i++) {
			if (manualExamArray[i] == null) {
				manualExamArray[i] = new OpenQuestion(question, answer);
				System.out.println("Added question #" + (i + 1));
				return true;
			}
		}
		return false;
	}

	public boolean addQuestionToManualExam(int questionNumber, int[] answersArray) {
		Question question = getQuestionById(questionNumber);
		if (question instanceof OpenQuestion) {
			OpenQuestion open = (OpenQuestion) question;
			addOpenQuestionToManualExam(open.getQuestion(), open.getOpenAnswer());
			return true;
		}
		if (question instanceof MultilpeChoiceQuestion) {
			MultilpeChoiceQuestion mQuestion = (MultilpeChoiceQuestion) question;
			addMultipeChoiseQuestionToManualExam(mQuestion, answersArray);
			return true;
		}
		return false;
	}

	public void createManualExamAndSendToPrint(int[] answersArray, int amountOfQuestions) {
		if (amountOfQuestions == manualExamArray.length) {
			sortAndPrintManualExam(answersArray);
		}
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

	public void saveFile(String date, Question[] arr) throws IOException {
		BufferedWriter questionsFile = new BufferedWriter(new FileWriter("Exam_" + date + "_Questions_" + "(" + testCounter + ") "));
		BufferedWriter answersFile = new BufferedWriter(new FileWriter("Exam_" + date + "_Solutions_" + "(" + testCounter + ") "));
		testCounter++;
		for (int i = 0; i < arr.length; i++) {
			questionsFile.write(arr[i].getQuestion() + "\n");
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] instanceof MultilpeChoiceQuestion) {
				MultilpeChoiceQuestion MultipleQuest = (MultilpeChoiceQuestion) arr[i];
				for (int j = 0; j < arr.length; j++) {
					answersFile.write(MultipleQuest.getChoiseAnswer(j) + " - "
							+ MultipleQuest.getChoiseAnswer(j).isTrue() + "\n");
				}
			}
			if (arr[i] instanceof OpenQuestion) {
				OpenQuestion oQuestion = (OpenQuestion) arr[i];
				answersFile.write(oQuestion.getOpenAnswer() + "\n");
			}
		}
		questionsFile.flush();
		questionsFile.close();
		answersFile.flush();
		answersFile.close();

	}

	public void saveOnExit() throws IOException, FileAlreadyExistsException, FileNotFoundException, ClassNotFoundException{
		Question[] arr = allQuestions;
		BufferedWriter saveOnExit = new BufferedWriter(new FileWriter("Exam_" + getCurrentDate() + "_ " + testCounter));
		testCounter++;
		for (int i = 0; i < arr.length; i++) {
			saveOnExit.write(arr[i] + "\n");
		}
		saveOnExit.flush();
		saveOnExit.close();
		System.out.println("Exam file saved.");
	}
	public void saveOnExitBinaryFile() throws IOException, FileAlreadyExistsException, FileNotFoundException, ClassNotFoundException{
		Question[] arr = allQuestions;
		FileOutputStream out = new FileOutputStream("File name_" + getCurrentDate());
		DataOutputStream dout = new DataOutputStream(out);
		for(int i = 0 ; i < arr.length; i++) {
			dout.writeBytes(arr[i] + " ");
		}
		out.close();
		dout.close();
		System.out.println("Binary file created.");
	}
	private final static String getCurrentDate() {
		String pattern = "MM-dd-yyyy";
		DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(new Date());
	}
}