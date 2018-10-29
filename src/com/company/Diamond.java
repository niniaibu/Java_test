package com.company;

public class Diamond {
    private String String_array = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private char aChar;

    Diamond(String s) {
        this.aChar = s.charAt(0);
    }

    public void paintDiamond() {
        int number_string = this.aChar - 'A' + 1;
        String sub_string = String_array.substring(0, number_string);
        char[] char_list = sub_string.toCharArray();

        for (int i = 0; i < number_string; i++) {
            for (int j = 0; j < number_string - i - 1; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < 2 * i + 1; k++) {
                if (k == 0 || k == 2 * i) {
                    System.out.print(char_list[i]);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        for (int i = number_string - 1; i > 0; i--) {
            for (int j = 0; j < number_string - i; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < 2 * i - 1; k++) {
                if (k == 0 || k == 2 * i - 2) {
                    System.out.print(char_list[i - 1]);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {

        Diamond d = new Diamond(args[0]);
        d.paintDiamond();

    }
}
