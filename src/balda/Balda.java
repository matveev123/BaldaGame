package balda;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Balda {
    private static final String[] listOfWords = {"абзац", "порой", "закол", "ремис", "холоп"};

    private static final String regExpStart = "^(1|2)$";
    private static final String regExpGenrlCheck = "^[a-я]$";
    private static final String failMessageAttempt = "Input Correct symbol (а-я)!";
    private static final String failMessageOpinion = "Error! Input correct - \"1\" or \"2\"";

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        System.out.println("\nMenu \"Balda\"\nChoose you option ( \"1\" - start Game; \"2\" - quit game):");

        String input = startCheck(regExpStart, failMessageOpinion);

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
        boolean flag = true;
        Random random = new Random();
        String generalWord = listOfWords[random.nextInt(5)];
        String secret = "";

        secret = createSecret(generalWord.length());

        while (flag) {
            System.out.println("\nPlease print a letter( FAIL = " + fail + " | status =  " + secret + "):");

            String letter = startCheck(regExpGenrlCheck, failMessageAttempt).toLowerCase();

            pos = generalWord.indexOf(letter);

            if (generalWord.contains(letter)) {
                secret=appearingSecret(secret, generalWord, pos);

            } else {
                System.out.println("\nWrong attempt, try again...");
                fail++;
            }
            if (countingStar(secret) == 0)
                flag = false;
            if (fail == 6)
                flag = false;
        }
        if (countingStar(secret) == 0)
            System.out.println("\nCongratulations! your FAIL = " + fail + " | answer is  =  " + secret + "):\n");
        else
            System.out.println("You are lose! You has 6 attempts! Secret word is " + secret);
        start();
    }

    private static int countingStar(String secret) {
        return secret.length() - secret.replace("*", "").length();
    }

    private static String appearingSecret(String secret, String generalWord, int pos) {
        char[] masSecret = secret.toCharArray();
        char[] masGen = generalWord.toCharArray();
        for (int i = 0; i < secret.length(); i++) {
            if (masSecret[i] != masGen[i] & masGen[i] == secretSymdol(generalWord, pos))
                masSecret[i] = generalWord.charAt(pos);

        }
        return new String(masSecret);
    }

    private static String createSecret(int lengthOfGeneralW) {
        String tempString = "";
        StringBuffer stringBuffer = new StringBuffer(tempString);
        for (int j = 0; j < lengthOfGeneralW; j++) {
            stringBuffer.append("*");
        }
        return stringBuffer.toString();
    }

    private static char secretSymdol(String genWord, int pos) {
        return genWord.charAt(pos);
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

