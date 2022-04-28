package ID_206946790_ELI_LEVY;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main01 implements MainMethods{
	Scanner input;
	MangerClass01 manage;
	private char chr;
	private int choice;
	private int innerOP;
	private int questionNum;
	private int amountOfQuestions;
	private boolean flag = true;
	
	@Override
	public boolean importQuestions() throws ClassNotFoundException, IOException {
		System.out.println("Import questions from binary file ? \nType '1' for yes. \nType '2' for no.");
		int q = manage.intException(input);
		if(q == 1) {
			autoImport();
			return true;
		}
		else {
			questionList();
			manage.saveOnExitBinaryFile();
			return false;
		}
	}
	@Override
	public void mainMenu() throws FileNotFoundException, IOException, ClassNotFoundException {
		String menu = ("\n--------Exam creator--------\n" + "-----Select option:-----\n"
				+ "[1] - Show all questions/answers that exist.\n" + "[2] - Add a new question/answer.\n"
				+ "[3] - Update an existing question.\n" + "[4] - Update an existing answer.\n"
				+ "[5] - Delete an existing answer.\n" + "[6] - Create an exam manually.\n"
				+ "[7] - Create an exam automatically.\n" + "[8] - Sort Questions by answers length.\n" 
				+ "[9] - Create copy of an existing exam.\n"
				+ "\n[11] - Exit.\n" + "Enter your choice: ");
		
		do {
			do {
				System.out.println(menu);
				try {
					choice = Integer.parseInt(input.nextLine());
					flag = false;
				} catch (InputMismatchException e) {
					System.out.println("Wrong Input, Please try again.");
				} catch (NumberFormatException e) {
					System.out.println("Input must be Integer, Please try again.");
				}

			} while (flag);
			switch (choice) {
			case 1:
				System.out.println("-----Show all questions menu-----");
				System.out.println("[1] - Show all questions.");
				System.out.println("[2] - Show only American-type questions (With their correct answers).");
				System.out.println("[3] - Show only Open-type questions.");
				System.out.println("[4] - Show available questions only.");
				System.out.println(
						"[-1] - Go back to main menu." + "\n(You can type '-1' at any time to go back to main menu).");
				do {
					innerOP = Integer.parseInt(input.nextLine());
					switch (innerOP) {
					case 1:
						manage.printAllQuestionAndAnswers();
						break;
					case 2:
						manage.printOnlyMultilpeChoiceQuestions();
						break;
					case 3:
						manage.printOnlyOpenQuestions();
						break;
					case 4:
						manage.printAllQuestions();
						break;
					}
				} while (innerOP != -1);
				break;

			case 2:
				System.out.println("----Add a question-----" + "\n[1] - Add an Multilpe Choice - type question."
						+ "\n[2] - Add an open - type question." + "\n[-1] - To go back to main menu."
						+ "\n(You can type '-1' at any time to go back to main menu).");
				do {
					innerOP = manage.intException(input);
					;
					input.nextLine();
					switch (innerOP) {
					case 1:
						System.out.println("Please enter your question below: ");
						String ameriQuestion = input.nextLine();
						MultilpeChoiceQuestion ameriC = new MultilpeChoiceQuestion(ameriQuestion);
						manage.addMultilpeChoiceQuestion(ameriC);
						System.out.println("How many answers do you want to add? (2 to 6) ");
						int answersAmount = manage.intException(input);
						input.nextLine();
						if (answersAmount < 2 || answersAmount > 6) {
							System.out.println("Invalid input, Please enter a number between 2 to 6.");
							answersAmount = manage.intException(input);
							input.nextLine();
						}
						for (int i = 0; i < answersAmount; i++) {
							System.out.println("Please enter answer number " + (i + 1) + ": ");
							String answer = input.nextLine();
							System.out.println("Is it a correct answer or not? (true/false): ");
							try {
								boolean isTrue = input.nextBoolean();
								System.out.println(ameriC.addAnswer(new MultipeChoiseAnswer(answer, isTrue)));
								input.nextLine();
								flag = false;
							} catch (InputMismatchException e) {
								System.out.println("Invaild input, Please enter True/False answers only.");
								i--;
							}
						}

						break;
					case 2:
						System.out.println("Please enter your question below: ");
						String question = input.nextLine();
						System.out.println("Please enter an answer: ");
						String answer = input.nextLine();
						System.out.println(manage.addOpenQuestion(question, answer));
						break;
					}
				} while (innerOP != -1);
				break;

			case 3:

				do {
					System.out.println(
							"See the full list of questions & answers? y/Y" + "\nTo go back to main menu 'n/N.");
					char newChoice = input.next().charAt(0);
					if (newChoice == 'y' || newChoice == 'Y') {
						manage.printAllQuestions();
					} else if (newChoice == 'n' || newChoice == 'N') {
						innerOP = -1;
						break;
					}
					System.out.println("Please choose the question number you want to update: ");
					int updateChoice = manage.intException(input);
					input.nextLine();
					System.out.println("Enter new text below: ");
					String newQuestion = input.nextLine();
					System.out.println(manage.updateAnExistingQuestion(updateChoice, newQuestion));
					break;

				} while (innerOP != -1);
				break;

			case 4:
				System.out.println("Would you like to update an Multilpe Choice answer or Open answer?"
						+ "\nType [1] for Multilpe Choise, \nType [2] for Open." + "\n[-1] - To go back to main menu.");
				do {
					innerOP = manage.intException(input);
					input.nextLine();
					switch (innerOP) {
					case 1:
						System.out.println(
								"See the full list of questions & answers? y/Y" + "\nTo go back to main menu 'n/N.");
						char newChoice1 = input.next().charAt(0);
						if (newChoice1 == 'y' || newChoice1 == 'Y') {
							manage.printOnlyMultilpeChoiceQuestions();
						} else if (newChoice1 == 'n' || newChoice1 == 'N') {
							innerOP = -1;
							break;
						}
						System.out.println("Please select from which question you want to update an answer: ");
						questionNum = manage.intException(input);
						input.nextLine();
						manage.getMultipeChoiseAnswer(questionNum);
						System.out.println("Please select which answer: ");
						int answerNum = manage.intException(input);
						input.nextLine();
						System.out.println("Please enter the new answer: ");
						String newAnswer = input.nextLine();
						System.out.println(manage.updateAnAnswer(questionNum, answerNum, newAnswer));
						break;
					case 2:
						System.out.println(
								"See the full list of questions & answers? y/Y" + "\nTo go back to main menu 'n/N.");
						char newChoice2 = input.next().charAt(0);
						if (newChoice2 == 'y' || newChoice2 == 'Y') {
							manage.printOnlyOpenQuestions();
						} else if (newChoice2 == 'n' || newChoice2 == 'N') {
							innerOP = -1;
							break;
						}

						System.out.println("Please select from which question you want to update an answer: ");
						int questionNum1 = manage.intException(input);
						input.nextLine();
						System.out.println("Please enter the new answer: ");
						String newAnswer1 = input.nextLine();
						System.out.println(manage.updateAnAnswer(questionNum1, questionNum, newAnswer1));
						break;
					}
				} while (innerOP != -1);
				break;

			case 5:
				do {

					System.out
							.println("See the full list of questions&answers? y/Y" + "\nTo go back to main menu 'n/N.");
					char newChoice2 = input.next().charAt(0);
					if (newChoice2 == 'y' || newChoice2 == 'Y') {
					} else if (newChoice2 == 'n' || newChoice2 == 'N') {
						innerOP = -1;
						break;
					}
					System.out.println("Can only delete from American-type questions.");
					manage.printAllQuestions();

					System.out.println("Please select from which question you want to delete an answer: ");
					int questionNum1 = manage.intException(input);
					input.nextLine();
					while (!manage.checkInstanceofQuestion(questionNum1)) {
						System.out.println("Cannot delete from an Open Question, select a different one: ");
						questionNum1 = manage.intException(input);
						input.nextLine();
					}
					manage.printSelectedMultipeChoiseAnswers(questionNum1);
					System.out.println("Please select which answer: ");
					int answerNum1 = manage.intException(input);
					input.nextLine();
					System.out.println(manage.deleteAnExistingAnswer(questionNum1, answerNum1));
					break;
				} while (innerOP != -1);
				break;

			case 6:

				System.out.println("How many questions do you want in your exam? ");
				amountOfQuestions = manage.intException(input);
				manage.setSize(amountOfQuestions);
				manage.printAllQuestions();
				int answersAmount = 0;
				ArrayList<Integer> answersArray = new ArrayList<Integer>();
				System.out.println("\nPlease choose the question you wish to add: ");
				for (int i = 0; i < amountOfQuestions; i++) {
					questionNum = manage.intException(input);
					if (!manage.checkInstanceofQuestion(questionNum)) {
						manage.addQuestionToManualExam(questionNum, null);
					} else {
						System.out.println("Please choose the amount of answers you want for the question: ");
						answersAmount = manage.intException(input);
						answersArray = new ArrayList<>(Collections.nCopies(answersAmount, 0));
						System.out.println("These are the answers:");
						manage.getMultipeChoiseAnswer(questionNum);
						System.out.println("Please select the answers you want: ");
						for (int j = 0; j < answersArray.size(); j++) {
							answersArray.set(j, manage.intException(input));
						}
						manage.addQuestionToManualExam(questionNum, answersArray);
						if (i != answersArray.size() - 1) {
							System.out.println("Please enter the next question.");
						}
					}
				}
				manage.printAndSaveManualExamToFile();
				break;

			case 7:
				System.out.println("How many questions would you like in your exam? ");
				System.out.println("There are only: " + manage.checkAllQuestionLength() + " Questions available.");
				int questAmount = manage.intException(input);
				if (questAmount < 0 || questAmount > manage.checkAllQuestionLength()) {
					System.out.println("Invalid amount of question, please select again: ");
					questAmount = manage.intException(input);
					;
				}
				manage.autoCreateExam(questAmount);
				break;
			case 8:
				System.out.println("Sort Questions by answers length.");
				manage.sortQuestionsByAnswersLength();
				break;
			case 9:
				manage.createCopyOfAnExistingExam();
				System.out.println("\nPlease chose file number: ");
				do {
				try {
				int chosenFile = manage.intException(input);
				manage.makeCopyOfChosenFile(chosenFile);
				flag = false;
				} catch (InputMismatchException e) {
					System.out.println("Chosen File must be 1 or higher, Please try again.");
				}
				}while(flag);
				break;
			default:
				if (choice != 11) {
					System.out.println("Invalid option, Please try again.");
				}

			}
		} while (choice != 11);

		manage.saveOnExitBinaryFile();

		System.out.println("Exiting program...Thank you!");
		input.close();

	}
	@Override
	public void questionList() {
		// General questions
		String quest1 = "First question";
		String quest2 = "Second question";
		String quest3 = "Third question";
		String quest4 = "Fourth question";
		String quest5 = "Fifth question";
		String quest6 = "Sixth question";
		String quest7 = "Seventh question";
		String quest8 = "Eighth question";

		// Just answers
		String ans1 = "First answer";
		String ans2 = "Second answer";
		String ans3 = "Another answer";
		String ans4 = "And another one";
		String ans5 = "Fifth answer";
		String ans6 = "Sixth answer";
		String ans7 = "Seventh answer";
		String ans8 = "Eighth answer";

		// False answers
		String ansF1 = "False answer #1";
		String ansF2 = "False answer #2";
		String ansF3 = "False answer #3";
		String ansF4 = "False answer #4";
		String ansF5 = "False answer #5";

		// Open answers
		String openAns = "First open answer";
		String openAns2 = "Second open answer";
		String openAns3 = "Third open answer";
		String openAns4 = "Fourth open answer";

		// Create new Multiple Choice Question question objects
		MultilpeChoiceQuestion ameriTest1 = new MultilpeChoiceQuestion(quest1);
		MultilpeChoiceQuestion ameriTest2 = new MultilpeChoiceQuestion(quest2);
		MultilpeChoiceQuestion ameriTest3 = new MultilpeChoiceQuestion(quest3);
		MultilpeChoiceQuestion ameriTest4 = new MultilpeChoiceQuestion(quest4);

		// Add add Multiple Choice Question questions
		manage.addMultilpeChoiceQuestion(ameriTest1);
		manage.addMultilpeChoiceQuestion(ameriTest2);
		manage.addMultilpeChoiceQuestion(ameriTest3);
		manage.addMultilpeChoiceQuestion(ameriTest4);

		// Multiple Choice answers
		MultipeChoiseAnswer ameriAns = new MultipeChoiseAnswer(ans1, true);
		MultipeChoiseAnswer ameriAns2 = new MultipeChoiseAnswer(ans2, true);
		MultipeChoiseAnswer ameriAns3 = new MultipeChoiseAnswer(ans3, true);
		MultipeChoiseAnswer ameriAns4 = new MultipeChoiseAnswer(ans4, true);
		MultipeChoiseAnswer ameriAns5 = new MultipeChoiseAnswer(ans5, true);
		MultipeChoiseAnswer ameriAns6 = new MultipeChoiseAnswer(ans6, true);
		MultipeChoiseAnswer ameriAns7 = new MultipeChoiseAnswer(ans7, true);
		MultipeChoiseAnswer ameriAns8 = new MultipeChoiseAnswer(ans8, true);
		MultipeChoiseAnswer ameriAnsF1 = new MultipeChoiseAnswer(ansF1, false);
		MultipeChoiseAnswer ameriAnsF2 = new MultipeChoiseAnswer(ansF2, false);
		MultipeChoiseAnswer ameriAnsF3 = new MultipeChoiseAnswer(ansF3, false);
		MultipeChoiseAnswer ameriAnsF4 = new MultipeChoiseAnswer(ansF4, false);
		MultipeChoiseAnswer ameriAnsF5 = new MultipeChoiseAnswer(ansF5, false);

		// More than one is correct:
		ameriTest1.addAnswer(ameriAns);
		ameriTest1.addAnswer(ameriAns2);
		ameriTest1.addAnswer(ameriAns3);
		ameriTest1.addAnswer(ameriAns4);
		ameriTest1.addAnswer(ameriAnsF1);
		ameriTest1.addAnswer(ameriAnsF4);

		// All false
		ameriTest2.addAnswer(ameriAnsF1);
		ameriTest2.addAnswer(ameriAnsF2);
		ameriTest2.addAnswer(ameriAnsF3);
		ameriTest2.addAnswer(ameriAnsF4);
		ameriTest2.addAnswer(ameriAnsF5);

		// Only one question is true
		ameriTest3.addAnswer(ameriAns5);
		ameriTest3.addAnswer(ameriAnsF1);
		ameriTest3.addAnswer(ameriAnsF2);
		ameriTest3.addAnswer(ameriAnsF3);
		ameriTest3.addAnswer(ameriAnsF4);

		// more than one is correct #2
		ameriTest4.addAnswer(ameriAns6);
		ameriTest4.addAnswer(ameriAns7);
		ameriTest4.addAnswer(ameriAns8);
		ameriTest4.addAnswer(ameriAnsF3);
		ameriTest4.addAnswer(ameriAnsF4);

		// Open questions + Answers
		manage.addOpenQuestion(quest5, openAns);
		manage.addOpenQuestion(quest6, openAns2);
		manage.addOpenQuestion(quest7, openAns3);
		manage.addOpenQuestion(quest8, openAns4);
		
	}
	@Override
	public void mangerMethods() {
		this.chr = 0;
		this.choice = 0;
		this.innerOP = 0;
		this.amountOfQuestions = 0;
		this.questionNum = 0;
		this.input = new Scanner(System.in);
		this.manage = new MangerClass01();
		
	}
	@Override
	public void autoImport() throws ClassNotFoundException, IOException {
		try {
			manage.addQuestionsFromBinaryFile();
			
		}catch(FileNotFoundException e) {
			System.out.println("File not found, Importing questions from pre-made question list.");
			questionList();
			manage.saveOnExitBinaryFile();
		}
	}
	public static void main(String[] args)
			throws FileAlreadyExistsException, FileNotFoundException, ClassNotFoundException, IOException {
		Main01 op = new Main01();
		op.mangerMethods();	
		op.importQuestions();
		op.mainMenu();
	}
	

}
