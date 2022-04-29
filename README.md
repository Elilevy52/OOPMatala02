*Java OOP projact PART 2*

Creators Name: Eli Levy.
ID : 206946790.

* Part 1: Part 1: Hierarchy, Polymorphism, Object and Exceptions. [Done].
* Part 2: Part 2: Interfaces, files, generics [Done].
* Part 3: Part 3: GUI & MVC.

** My GitHub link -> https://github.com/Elilevy52/OOPMatala02

*About / Synopsis*

Part 1 & 2 are done. A program used to create and edit exams, managing specific questions and answers.
Create and export exams, copy exams, import question & answers lists.

*Usage*

Run the program, choose whether to import a 'questions.ser' file or the pre-made questions list
(Currently the import question file is added even if choesn not to but its not saved on the Binaryfile or Examfile.
Only beacuse they request build-in questions and answers). 
it will import pre - made questions and save them to a binary file named 'exam.ser' into 'Exam/' directory.
(it will create the directory if it does not exists). On the next program run, you can choose Option 2 in the 'question.ser' file.

 *KNOWN iSSUE* 

If there is a problem with running the program for the first time, Please go to 'ELI_LEVY_ID206946790_PART02\BinaryFile'
and delete the Exam.dat file, then run the program again and it should work just fine.

*Content*

Contains a way to add a new question + answer. 
Update a question, or update/delete an answer. 
Functions to manually create an exam with the available questions or rather let the program create one for you. 
Or even create a copy of an existing exam. 
Can sort the questions list by answer length. 
Export exams into .txt files or .ser files. Import .ser files.

Contains a 'Program' main class to request user input for the various options and implemented methods from 'MainMethods'.
A 'ManagerClass' class to manage all the requests coming from the 'Program' main class.
'Question' abstract class to contain all the data & info regarding the questions.
'MultilpeChoiceQuestion' along with 'OpenQuestion' classes to store all sub-data of 'Question' class.
'MultipeChoiseAnswer' has all the available answers to the 'MultilpeChoiceQuestion' questions.
'CompareQuestion' sorts the requested array by the total answer length.
'MainMethods' is an interface used to hand-out methods for the 'Program' class.
