package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Quiz  {
    private String name;
    private ArrayList<Question> questions;

    public Quiz() throws InvalidQuizFormatException {

        questions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public static Quiz loadFromFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scan = new Scanner(file);
        Quiz quiz = null;
        try {
            quiz = new Quiz();
        } catch (InvalidQuizFormatException e) {
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            String question = scan.nextLine();
            ArrayList<String> answers = new ArrayList<>();
            String answer = scan.nextLine();
            do {
                answers.add(answer);
                if (scan.hasNextLine())
                    answer = scan.nextLine();
                else
                    break;
            } while (!answer.equals(""));
            if (answers.size() == 1) {
                FillIn fillIn = new FillIn();
                fillIn.setDescription(question);
                fillIn.setAnswer(answers.get(0));
                quiz.addQuestion(fillIn);
            } else if (!answers.isEmpty()){
                Test test = new Test();
                test.setDescription(question);
                test.setAnswer(answers.get(0));
                Collections.shuffle(answers);
                String[] arr = new String[answers.size()];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = answers.get(i);
                }
                test.setOptions(arr);
                quiz.addQuestion(test);
            }
        }
        Collections.shuffle(quiz.questions);
        return quiz;
    }

    @Override
    public String toString() {
        return  "----------------------------------------------------\n" +
                "Welcome to \"" + getName() + "\" QUIZ!\n";
    }

    public void start() {
        System.out.println(toString());
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int i = 1;
        for (Question question: questions
        ) {
            System.out.println("====================================================");
            System.out.println();
            System.out.println(i +". "+ question);
            System.out.println("----------------------------------");
            i++;
            if (question instanceof Test) {
                System.out.println("Enter the correct choice: ");
                while (true) {
                    String answer = scanner.nextLine();
                    char choice = answer.charAt(0);
                    if (choice >= 'A' && choice <= 'D' && answer.length() == 1) {
                        if (((Test) question).getOptionAt(choice - 'A').equals(question.getAnswer())) {
                            count++;
                            System.out.println("Correct!");
                        } else {
                            System.out.println("Incorrect!");
                        }
                        break;
                    } else {
                        System.out.print("Invalid choice try again! (Ex: A, B, ...):");
                    }
                }
            } else if (question instanceof FillIn) {
                System.out.println("Type your answer: ");
                String answer = scanner.nextLine();
                if (answer.toLowerCase().equals(question.getAnswer())) {
                    count++;
                    System.out.println("Correct!");
                } else {
                    System.out.println("Incorrect!");
                }
            }

        }

        System.out.println("Correct answers: " + count + "/" + questions.size() + " (" + (count * 100.0 / questions.size()) + "%)");
    }
}

