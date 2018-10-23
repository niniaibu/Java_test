package com.company;

// import apple.laf.JRSUIConstants;

import java.util.HashMap;
import java.util.Map;

public class Main {

    //直接进行字符映射，输出字符传入到show_number()展示
    public String reflection_number_old(char character){
        Map<Character, String> reflection_map = new HashMap<>();
        reflection_map.put('0', " - | |   | | - ");
        reflection_map.put('1', "     |     |   ");
        reflection_map.put('2', " -   | - |   - ");
        reflection_map.put('3', " -   | -   | - ");
        reflection_map.put('4', "   | | -   |   ");
        reflection_map.put('5', " - |   -   | - ");
        reflection_map.put('6', " - |   - | | - ");
        reflection_map.put('7', " -   |     |   ");
        reflection_map.put('8', " - | | - | | - ");
        reflection_map.put('9', " - | | -   | - ");
        String string  = reflection_map.get(character);
        return string;
    }



    //间接进行字符映射，输出字符集传入到show_array()展示
    public String calculate_number_to_array(int size, int number) {
        String string_show;

        Map<Integer, String> reflection_array_map = new HashMap<>();
        reflection_array_map.put(0, "020,101,020,101,020");
        reflection_array_map.put(1, "000,001,000,001,000");
        reflection_array_map.put(2, "020,001,000,100,020");
        reflection_array_map.put(3, "020,001,020,001,020");
        reflection_array_map.put(4, "000,101,020,001,000");
        reflection_array_map.put(5, "020,100,020,001,020");
        reflection_array_map.put(6, "020,100,020,101,020");
        reflection_array_map.put(7, "020,001,000,001,000");
        reflection_array_map.put(8, "020,101,020,101,020");
        reflection_array_map.put(9, "020,101,020,001,020");

        if (size == 1) {
            string_show = reflection_array_map.get(number);
        } else {
            String origin_string = reflection_array_map.get(number);
            StringBuffer buf = new StringBuffer();

            String[] origin_lists = origin_string.split(",");
            boolean flag_no_semicolon = true;

            for (int j_list = 0; j_list < origin_lists.length; j_list++) {
                char[] origin_string_char = origin_lists[j_list].toCharArray();

                if ((j_list == 0) || (j_list == origin_lists.length - 1) || (j_list == (origin_lists.length - 1) / 2)) { //解决三个横线
                    String vertical_string = String.format("%0" + size + "d", 0).replace('0', origin_string_char[1]);
                    buf.append("0").append(vertical_string).append("0");
                    flag_no_semicolon = true;

                } else {
                    String cross_string = String.format("%0" + size + "d", 0);
                    String copy_col = origin_string_char[0] + cross_string + origin_string_char[2];
                    for (int k_col = 0; k_col < size; k_col++) {
                        buf.append(copy_col);
                        buf.append(",");
                        flag_no_semicolon = false;
                    }
                }
                if (flag_no_semicolon) {
                    buf.append(",");
                    flag_no_semicolon = true;
                }
            }
            string_show = buf.toString();
        }
        return string_show;
    }


    //间接映射展示函数
    public void show_array(String string_show){
        String[] string_lists = string_show.split(",");
        for(int i = 0; i<string_lists.length; i ++){
            char[] char_array_lists = string_lists[i].toCharArray();
            for (int j = 0; j < char_array_lists.length; j++){
                if (char_array_lists[j] == '0'){
                    System.out.print(' ');
                }else if(char_array_lists[j] == '1'){
                    System.out.print('|');
                }else{
                    System.out.print('-');
                }
            }
            System.out.println();
        }
    }



    //直接映射展示函数
    public void show_number(char[] number_lists){
        for(int j_row = 0; j_row < 15; j_row += 3) {
            for (int char_num = 0; char_num < number_lists.length; char_num ++) {
                String reflect_string = reflection_number_old(number_lists[char_num]);
                char[] char_list = reflect_string.toCharArray();
                for(int i_column = j_row; i_column < j_row + 3; i_column ++){
                    System.out.print(char_list[i_column]);
                }
            }
            System.out.println();
        }
    }


    public void LCD_Number_with_one_size(String size, String number){
        if(size == "1"){
            size = "1";
        }
        char[] number_lists = number.toCharArray();
        show_number(number_lists);

    }


    public void LCD_Number_with_N_size(String size, String number){
        if(size == null){
            size = "1";
        }
        int size_num = Integer.parseInt(size);
        int number_int = Integer.parseInt(number);
        String string_show = calculate_number_to_array(size_num, number_int);
        show_array(string_show);
    }


    public static void main(String[] args) {
        Main obj1 = new Main();
        obj1.LCD_Number_with_one_size("1", "876");


        Main obj2 = new Main();
        obj2.LCD_Number_with_N_size("2", "3");
    }
}
