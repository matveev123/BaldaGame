package balda;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Balda {
    private static final String[] LIST_OF_WORDS = {"абзац", "порой", "закол", "ремис", "холоп"};

    private static final String REG_EXP_START = "^(1|2)$";
    private static final String REG_EXP_GNRL_CHECK = "^[a-я]$";
    private static final String ERROR_MESSAGE_INCORRECT_LETTER = "Input Correct symbol (а-я)!";
    private static final String ERROR_MESSAGE_INCORRECT_MENU = "Error! Input correct - \"1\" or \"2\"";

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        System.out.println("\tMenu \"Balda\"\nChoose you option ( \"1\" - start Game; \"2\" - quit game):");

        String input = startCheck(REG_EXP_START, ERROR_MESSAGE_INCORRECT_MENU);

        switch (input) {
            case "1": {
                bigStart();
                break;
            }
            case "2": {
                System.out.println("quit game...");
                break;
            }
        }

    }

    public static void bigStart() {
        int fail = 0;
        int pos;
        Random random = new Random();
        String generalWord = LIST_OF_WORDS[random.nextInt(5)];
        String secret = "";

        secret = createSecret(generalWord.length());

        while (true) {
            System.out.println("\nPlease print a letter( FAIL = " + fail + " | status =  " + secret + "):");

            String letter = startCheck(REG_EXP_GNRL_CHECK, ERROR_MESSAGE_INCORRECT_LETTER).toLowerCase();

            pos = generalWord.indexOf(letter);

            if (generalWord.contains(letter)) {
                secret = appearLetter(secret, generalWord, pos);

            } else {
                System.out.println("\nWrong attempt, try again...");
                fail++;
            }
            if (countStar(secret) == 0 | fail == 6)
                break;

        }
        if (countStar(secret) == 0)
            System.out.println("\nCongratulations! your FAIL = " + fail + " | answer is  =  " + secret + "):\n");
        else
            System.out.println("\nYou are lose! You has 6 attempts! Secret word is \"" + generalWord + "\"");
        start();
    }

    private static int countStar(String secret) {
        return secret.length() - secret.replace("*", "").length();
    }

    private static String appearLetter(String secret, String generalWord, int pos) {
        char[] masSecret = secret.toCharArray();
        char[] masGen = generalWord.toCharArray();
        for (int i = 0; i < secret.length(); i++) {
            if (masGen[i] == generalWord.charAt(pos))
                masSecret[i] = generalWord.charAt(pos);
        }
        return new String(masSecret);
    }

    private static String createSecret(int lengthOfGeneralWorld) {
        String tempString = "";
        StringBuffer stringBuffer = new StringBuffer(tempString);
        for (int j = 0; j < lengthOfGeneralWorld; j++) {
            stringBuffer.append("*");
        }
        return stringBuffer.toString();
    }


    private static String startCheck(String regExp, String message) {
        Scanner scanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile(regExp);
        while (true) {
            String inputFromUser = scanner.nextLine();
            Matcher matcher = pattern.matcher(inputFromUser);
            if (matcher.find()) {
                System.out.println("Correct input!");
                return matcher.group();

            } else {
                System.out.println(message);
            }
        }
    }
}

