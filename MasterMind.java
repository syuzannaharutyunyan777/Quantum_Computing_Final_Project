import java.util.*;

public class MasterMind {
    private final String[] secretCode;

    public MasterMind(int codeLength, String[] colors) {
        secretCode = generateSecretCode(codeLength, colors);
    }

    private String[] generateSecretCode(int codeLength, String[] colors) {
        Random random = new Random();
        String[] code = new String[codeLength];
        for (int i = 0; i < codeLength; i++) {
            code[i] = colors[random.nextInt(colors.length)];
        }
        return code;
    }

    public int[] evaluateGuess(String[] guess) {
        int correctPositions = 0, correctColors = 0;
        Set<String> secretSet = new HashSet<>(Arrays.asList(secretCode));

        for (int i = 0; i < guess.length; i++) {
            if (guess[i].equals(secretCode[i])) {
                correctPositions++;
            } else if (secretSet.contains(guess[i])) {
                correctColors++;
            }
        }

        return new int[]{correctPositions, correctColors};
    }

    public static void main(String[] args) {
        int codeLength = 4;
        String[] colors = {"Red", "Blue", "Green", "Yellow", "Orange", "Purple"};
        int maxGuesses = 5;

        MasterMind game = new MasterMind(codeLength, colors);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Master Mind!");
        System.out.println("You have " + maxGuesses + " guesses to find the secret code.");
        System.out.println("Available colors: " + Arrays.toString(colors));

        for (int i = 0; i < maxGuesses; i++) {
            System.out.print("Enter your guess (comma-separated colors): ");
            String[] guess = scanner.nextLine().split(", ");
            int[] result = game.evaluateGuess(guess);
            System.out.println("Result: " + result[0] + " pins in correct positions, " + result[1] + " pins with correct colors but in wrong places.");
            if (result[0] == codeLength) {
                System.out.println("Congratulations! You've guessed the secret code!");
                return;
            } else if (i == maxGuesses - 1) {
                System.out.println("Sorry, you've reached the maximum number of guesses. The secret code was: " + Arrays.toString(game.secretCode));
            }
        }
    }
}
