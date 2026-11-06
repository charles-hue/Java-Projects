package org.example;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        System.out.println("\n=============================");
        System.out.println("Welcome to NumberGuessingGame");
        System.out.println("=============================");
        System.out.println("You have 7 tries to guess the secret number.\n");

        // Game loop.
        gameLoop:
        while (true) {
            int secretNumber = random.nextInt(900) + 100; // Makes sure generated numbers stay above 100.
            int tries = 0;
            ArrayList<Integer> guesses = new ArrayList<>();
            boolean isGuessed = false;

            // Guess loop.
            while (tries < 7) {
                System.out.println(String.format("Attempt %d of 7.", tries + 1));
                System.out.print("Enter your guess: ");
                if (!input.hasNextLine()) { break gameLoop; } // Breaks out of the entire game loop if line is empty.
                String line = input.nextLine().trim();

                // Makes sure only numbers are input, then parses the string to an integer variable.
                if (!line.matches("\\d{1,9}")) {
                    System.out.println("Invalid guess, try again!\n");
                    continue;
                }
                final int guess = Integer.parseInt(line);
                if (guess < 100 || guess > 999) {
                    System.out.println("Must be three digits only!\n");
                    continue;
                }

                tries++; // Updates number of attempts.
                guesses.add(guess); // Adds the valid guess to an ArrayList for the summary later.

                if (guess ==  secretNumber) {
                    System.out.println("Congratulations! You guessed the secret number.\n");
                    isGuessed = true;
                    break;
                }
                else {
                    // Hint block.
                    final int distance = Math.abs(guess - secretNumber);
                    if (distance < 10) {
                        System.out.println("Very hot!\n");
                    }
                    else if (distance < 50) {
                        System.out.println("Warm...\n");
                    }
                    else {
                        System.out.println(guess > secretNumber ? "Too high.\n" : "Too low.\n");
                    }
                }

            }

            // Runs when all guesses are exhausted, closes game.
            if (!isGuessed) {
                System.out.println(String.format("You failed. The secret number was %d\n", secretNumber));
            }

            System.out.println("==============================");
            System.out.println("         GAME SUMMARY");
            System.out.println("==============================");
            System.out.printf("Attempts used: %d | Secret: %d |\nGuesses: %s%n", tries, secretNumber, guesses);
            System.out.println("==============================");

            while (true) {
                System.out.print("Play again? (y/n): ");
                if (!input.hasNextLine()) {
                    break gameLoop;
                }
                String answer = input.nextLine().trim().toLowerCase();
                switch (answer) {
                    case "y":
                        continue gameLoop;

                    case "n":
                        System.out.println("Thanks for playing. Goodbye.");
                        break gameLoop;

                    default:
                        System.out.println("Please type y or n.\n");
                }
            }
        }
        input.close();
    }
}
