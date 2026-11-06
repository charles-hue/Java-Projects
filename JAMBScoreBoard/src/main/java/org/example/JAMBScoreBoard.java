package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class JAMBScoreBoard {

    // A function to check user input, and return a null value if empty.
    static String readLine (Scanner in) {
        return in.hasNextLine()? in.nextLine().trim() : null;
    }

    static Integer checkAndParseToInt(String in) {
        if (in.isEmpty()) {
            System.out.println("You didn't type anything.");
                    return null;
        }

        if (!in.matches("\\d{1,9}")) {
            System.out.printf("%s is not a number!%n", in);
            return null;
        }

        return Integer.parseInt(in);
    }


    public static void main (String [] args) {

        Scanner input = new Scanner(System.in);
        String menuOptions;
        String userEntry;
        ArrayList<Integer> scoresList = new ArrayList<>();



        System.out.println("===================");
        System.out.println(" JAMB Score Board");
        System.out.println("===================");

        menu:
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Scores");
            System.out.println("2. Show Scores");
            System.out.println("3. Analyse Scores");
            System.out.println("4. Exit\n");
            System.out.print("Enter: ");
            menuOptions = readLine(input);
            if (menuOptions == null) {break menu;}


            switch (menuOptions) {

                case "1": {
                    System.out.print("Enter score: ");
                    userEntry = readLine(input);
                    if (userEntry == null) {
                        System.out.println("Enter a score from 0 to 400, please.");
                        continue menu;
                    }

                    final Integer score = checkAndParseToInt(userEntry);
                    if (score == null) {
                        continue menu;
                    }
                    // Checks if the input contains anything besides numbers.
                    // I'm using Integer in case there's a null value.

                    if (score < 0 || score > 400) {
                        System.out.println("Out of range.");
                        System.out.println("Enter a score from 0 to 400, please.");
                        continue menu;
                    } else {
                        scoresList.add(score); // Adds the score to an array list.
                        System.out.println("Score added successfully!\n");
                        break;
                    }
                }

                case "2": {
                    int i;
                    if (scoresList.isEmpty()) {
                        System.out.println("There are no scores recorded.");
                        break;
                    }
                    else {
                        for (i=0; i < scoresList.size(); i++) {
                            // Prints the scores in a list.
                            System.out.printf("%d. %d%n",  i+1, scoresList.get(i));
                        }
                    }
                    System.out.println(); // For a space before the menu runs again.
                    break;
                }

                case "3": {
                    if (scoresList.isEmpty()) {
                        System.out.println("No scores to analyse yet.");
                        break;
                    }

                    int sum = 0;
                    int highest = scoresList.get(0);   // safe, the list isn't empty
                    int lowest = scoresList.get(0);
                    int excellent = 0;
                    int veryGood = 0;
                    int good = 0;
                    int fair = 0;
                    int needsAnother = 0;

                    for (int i = 0; i < scoresList.size(); i++) {
                        final int s = scoresList.get(i);
                        sum += s;

                        // Highest and lowest
                        if (s > highest) { highest = s; }
                        if (s < lowest) { lowest = s; }

                        // Grade counter
                        if (s >= 300) {
                            excellent++;
                        } else if (s >= 250) {
                            veryGood++;
                        } else if (s >= 200) {
                            good++;
                        } else if (s >= 160) {
                            fair++;
                        } else {
                            needsAnother++;
                        }
                    }

                    // Typecasting to double o the decimals aren't rounded up.
                    final double average = (double) sum / scoresList.size();

                    System.out.println("\n--- Analysis ---");
                    System.out.printf("Candidates: %d%n", scoresList.size());
                    System.out.printf("Highest:    %d%n", highest);
                    System.out.printf("Lowest:     %d%n", lowest);
                    System.out.printf("Average:    %.1f%n%n", average);
                    System.out.println("Grade bands:");
                    System.out.printf("  Excellent (300-400):         %d%n", excellent);
                    System.out.printf("  Very Good (250-299):         %d%n", veryGood);
                    System.out.printf("  Good (200-249):              %d%n", good);
                    System.out.printf("  Fair (160-199):              %d%n", fair);
                    System.out.printf("  Needs another attempt (<160): %d%n%n", needsAnother);
                    break;
                }

                case "4": {
                    System.out.printf("You entered %d score(s) this session.%n", scoresList.size());
                    System.out.println("Goodbye!");
                    break menu;
                }

                default: {
                    System.out.println("Please select a valid option. (1-4)");
                    continue menu;
                }
            }
        }

        input.close();
    }
}
