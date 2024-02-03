package balda;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Balda {
    private static String listOfWords[] = {"абзац", "порой", "закол", "ремис", "холоп"};

    private static final String regExpStart = "^(1|2)$";
    private static final String regExpGenrlCheck = "^[a-я]$";

    public static void main(String[] args) {
        start();

    }

    private static void start() {


        System.out.println("Menu \"Balda\"\nChoose you option ( \"1\" - start Game; \"2\" - quit game):");
        String str = "";
        str = startCheck(regExpStart);
        System.out.println(str);
        switch (str) {
            case "1": {
                bigStart();
                break;
            }
            case "2": {
                System.out.println("quit game...");
                System.out.println("Error");
                break;
            }
        }

    }

    public static void bigStart() {
        int fail = 0;
        boolean flag = true;
        Random random = new Random();
        String generalWord = listOfWords[random.nextInt(5)];// example - малаша
        String str = "", answer = "*****", temp = "";//!
        for (int i = 0; i <= generalWord.length(); i++) {
            str = str + "*";
        }

        System.out.println("..." + generalWord + "...");


        while (flag) {
            System.out.println("\nPlease print a letter( FAIL = " + fail + " | status =  " + answer + "):");
            String letter = startCheck(regExpGenrlCheck);// case

            temp = appearLetter(generalWord, letter);

            if (temp.equals("-1") | (generalWord.contains(letter) & !(answer.equals(temp)))) {
                answer = appearLetter1(answer, temp);
            } else {
                System.out.println("\nWrong attempt, try again...");

                fail++;
            }
            if (answer.length() - answer.replace("*", "").length() == 0)
                flag = false;
            if (fail == 6)
                flag = false;
        }
        if (fail != 6)
            System.out.println("\n Congratulations! your FAIL = " + fail + " | answer is  =  " + answer + "):\n");
        else
            System.out.println("You are lose! Secret word is " + answer);
        start();
    }

    private static String appearLetter1(String str1, String str2) {


        int j = 0;
        char[] mas2 = str2.toCharArray();
        char[] mas1 = str1.toCharArray();
        char c = '*';
        for (int i = 0; i < mas2.length; i++) {
            if (!(c == mas2[i]))
                mas1[i] = mas2[i];

        }
        String temp = new String(mas1);

        return temp;
    }

    private static String appearLetter(String genWord, String letter) {
        if (-1 == genWord.indexOf(letter))
            return "";


        String answer = "", regexp = "";
        int pos = 0, tempPos = genWord.indexOf(letter);

        for (int i = 0; i <= tempPos; i++) {
            regexp += ".";
            pos = i;
        }

        for (int i = 0; i < genWord.length(); i++) {
            if (!(i == pos))
                answer += "*";
            else
                answer += letter;
        }
        System.out.println("answer1 " + answer);
        return answer;
    }


    private static void general(String letter) {

    }

    private static String startCheck(String regExp) {
        String str = "";
        Scanner scanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile(regExp);// REGExp // simpler?
        while (str.length() != 1) {
            String inputFromUser = scanner.nextLine();
            Matcher matcher = pattern.matcher(inputFromUser);
            if (matcher.find()) {
                str = matcher.group();
                System.out.println("Correct input!");// put away?
                return str;
            } else {
                str = "Error! Input correct - \"1\" or \"2\" ";// put away?
                System.out.println(str);
            }
        }
        return str;
    }
}
