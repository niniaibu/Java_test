package com.company;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.*;

public class LCD_Number {

    // 间接进行字符映射，输出字符集传入到show_array()展示
    public String[] calculate_number_to_array(int size, char[] number_lists) {
        String[] string_show = new String[number_lists.length];

        Map<Integer, String> reflection_array_map = new HashMap<>();
        reflection_array_map.put(0, "020,101,000,101,020");
        reflection_array_map.put(1, "000,001,000,001,000");
        reflection_array_map.put(2, "020,001,020,100,020");
        reflection_array_map.put(3, "020,001,020,001,020");
        reflection_array_map.put(4, "000,101,020,001,000");
        reflection_array_map.put(5, "020,100,020,001,020");
        reflection_array_map.put(6, "020,100,020,101,020");
        reflection_array_map.put(7, "020,001,000,001,000");
        reflection_array_map.put(8, "020,101,020,101,020");
        reflection_array_map.put(9, "020,101,020,001,020");


        for (int char_num = 0; char_num < number_lists.length; char_num ++) {

            if (size == 1) {
                string_show[char_num] = reflection_array_map.get(number_lists[char_num] - '0');
            } else {

                String origin_string = reflection_array_map.get(number_lists[char_num] - '0');
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
                string_show[char_num] = buf.toString();
            }
        }
        return string_show;
    }


    //改变显示为非等宽
    public String[] change_font(int size, String[] string_show){

        int num_series_zeros = 0;
        // String[] string_list_sub = new String[2 * size + 3];
        String[] string_change_list = new String[string_show.length];
        for (int j_num = 0; j_num < string_show.length; j_num ++){
            int num_zeros_max = size + 2;
            String[] string_lists = string_show[j_num].split(",");
            for (int i_string = 0; i_string < string_lists.length; i_string ++){
                char[] char_list = string_lists[i_string].toCharArray();
                for (int i_char = 0; i_char < char_list.length; i_char ++){
                    if (char_list[i_char] == '0'){
                        num_series_zeros += 1;
                    }else{
                        break;
                    }
                }
                if(num_series_zeros < num_zeros_max){
                    num_zeros_max = num_series_zeros;
                }
                num_series_zeros = 0;
            }
            String concat_string = "";
            for (int i_delete = 0; i_delete < string_lists.length; i_delete ++){
                // string_list_sub[i_delete] = string_lists[i_delete].substring(num_zeros_max);
                concat_string = concat_string.concat(string_lists[i_delete].substring(num_zeros_max));
                if (i_delete == string_lists.length - 1){
                    break;
                }
                concat_string = concat_string.concat(",");
            }
            string_change_list[j_num] = concat_string;
        }
        return string_change_list;
    }


    //显示不同大小字符
    public String[] change_size(int size, String[] string_show){

        int num_series_zeros = 0;
        String[] string_list_sub = new String[2 * size + 3];
        String[] string_change_list = new String[string_show.length];
        for (int j_num = 0; j_num < string_show.length; j_num ++){
            int num_zeros_max = size + 2;
            String[] string_lists = string_show[j_num].split(",");
            for (int i_string = 0; i_string < string_lists.length; i_string ++){
                char[] char_list = string_lists[i_string].toCharArray();
                for (int i_char = 0; i_char < char_list.length; i_char ++){
                    if (char_list[i_char] == '0'){
                        num_series_zeros += 1;
                    }else{
                        break;
                    }
                }
                if(num_series_zeros < num_zeros_max){
                    num_zeros_max = num_series_zeros;
                }
                num_series_zeros = 0;
            }
            String concat_string = "";
            for (int i_delete = 0; i_delete < string_lists.length; i_delete ++){
                // string_list_sub[i_delete] = string_lists[i_delete].substring(num_zeros_max);
                concat_string = concat_string.concat(string_lists[i_delete].substring(num_zeros_max));
                if (i_delete == string_lists.length - 1){
                    break;
                }
                concat_string = concat_string.concat(",");
            }
            string_change_list[j_num] = concat_string;
        }
        return string_change_list;
    }


    //间接映射展示函数
    public void show_array(int size, String[] string_show){

        int column = 2 * size + 3;
        StringBuffer split_output_lists = new StringBuffer();
        for (int split_num = 0; split_num < column; split_num ++){
            for (int num = 0; num < string_show.length; num ++) {

                String[] string_lists = string_show[num].split(",");
                if (split_num >= string_lists.length){
                        for (int j = 0; j < string_lists[0].length(); j ++){
                            split_output_lists.append("0");

                        }
                        split_output_lists.append("0");
                }else{
                    split_output_lists.append(string_lists[split_num]);
                    split_output_lists.append("0");
                }
            }

            char[] char_array_lists = split_output_lists.toString().toCharArray();
            for (int j = 0; j < char_array_lists.length; j++) {
                if (char_array_lists[j] == '2') {
                    System.out.print('-');
                } else if (char_array_lists[j] == '1') {
                    System.out.print('|');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
            split_output_lists.delete(0, split_output_lists.length());
        }
    }


    //合并两个字符串数组
    public String[] merge_two_string_array(String[] strings1, String[] strings2){
        List<String> string_show_both = new ArrayList<>(Arrays.asList(strings1));
        string_show_both.addAll(Arrays.asList(strings2));
        Object[] objectArray = string_show_both.toArray();
        String[] string_show_last = Arrays.copyOf(objectArray, objectArray.length, String[].class);
        return string_show_last;
    }



    public void LCD_Number_with_N_size(String number, String size, boolean flag_change_font){
        int size_num = Integer.parseInt(size);
        char[] number_lists = number.toCharArray();
        String[] string_show = calculate_number_to_array(size_num, number_lists);
        if (flag_change_font) {
            String[] string_change_font = change_font(size_num, string_show);
            show_array(size_num, string_change_font);
        }else{
            show_array(size_num, string_show);
        }
    }

    public void judge_function(String[] args){
        String size = "1";
        boolean change_font = false;
        int index = 0;
        String number = null;
        // System.out.print(args.length);
        switch (args.length){
            case 1:
                LCD_Number_with_N_size(args[0], size, change_font);
                break;
            case 2:
                for (int i = 0; i < 2; i ++) {

                    if (args[i].startsWith("-s")) {
                        size = args[i].substring(2);
                    }
                    if (args[i].startsWith("-f")) {
                        change_font = true;
                        // String font_value = args[i].substring(2);
                    }

                    if (change_font){
                        LCD_Number_with_N_size(args[1 - i], size, change_font);
                        break;
                    }else{
                        LCD_Number_with_N_size(args[1 - i], size, change_font);
                        break;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 3; i ++) {

                    if (args[i].startsWith("-s")) {
                        size = args[i].substring(2);
                    }else if (args[i].startsWith("-f")){
                        change_font = true;
                    }else{
                        index = i;
                    }
                }
                LCD_Number_with_N_size(args[index], size, change_font);
                break;
            case 4:
                String size_two = "1";
                String number_two;
                int i = 0;

                //起始为-s
                if (args[i].startsWith("-s")){
                    size = args[i].substring(2);
                    int size_int = Integer.parseInt(size);
                    index = i + 1;
                    number = args[index];
                    String[] string_show1 = calculate_number_to_array(size_int, number.toCharArray());
                    index = index + 2;
                    number_two = args[index];
                    String[] strings_show2;
                    String[] string_show_last;


                    if (args[i + 2].startsWith("-s")){//第二个参数-s
                        size_two = args[i + 2].substring(2);
                        strings_show2 = calculate_number_to_array(Integer.parseInt(size_two), number_two.toCharArray());

                        size = (size.compareTo(size_two) >= 0) ? size : size_two;
                        size_int = Integer.parseInt(size);
                        string_show_last = merge_two_string_array(string_show1, strings_show2);
                        show_array(size_int, string_show_last);



                    }else if (args[i + 2].startsWith("-f")){//第二个参数-f
                        change_font = true;
                        strings_show2 = calculate_number_to_array(Integer.parseInt(size_two), number_two.toCharArray());
                        if (change_font) {
                            String[] string_change_font = change_font(Integer.parseInt(size_two), strings_show2);
                            string_show_last = merge_two_string_array(string_show1, string_change_font);
                            show_array(size_int, string_show_last);
                        }

                    }
                }else{
                    System.out.print("Not realized！");
                }
                break;
                //起始为-f
                // else if (args[i].startsWith("-f")){
                //
                // }
            case 5:
                size_two = "1";
                i = 0;
                if (args[i].startsWith("-s")){
                    size = args[i].substring(2);
                    int size_int = Integer.parseInt(size);
                    if (! args[i + 1].startsWith("-f")) {
                        index = i + 1;
                        number = args[index];
                        String[] string_show1 = calculate_number_to_array(size_int, number.toCharArray());
                        index = index + 3;
                        number_two = args[index];
                        String[] strings_show2;
                        String[] string_show_last;

                        if (args[i + 2].startsWith("-s")) {
                            size_two = args[i + 2].substring(2);
                            strings_show2 = calculate_number_to_array(Integer.parseInt(size_two), number_two.toCharArray());

                            if (args[i + 3].startsWith("-f")) {
                                size = (size.compareTo(size_two) >= 0) ? size : size_two;
                                size_int = Integer.parseInt(size);
                                String[] string_change_font = change_font(Integer.parseInt(size_two), strings_show2);
                                string_show_last = merge_two_string_array(string_show1, string_change_font);
                                show_array(size_int, string_show_last);
                            }
                        }
                    }else{
                        index = i + 2;
                        number = args[index];
                        String[] string_show1 = calculate_number_to_array(size_int, number.toCharArray());
                        String[] string_change_font = change_font(Integer.parseInt(size), string_show1);
                        if (args[index + 1].startsWith("-s")){
                            index = index + 2;
                            number_two = args[index];
                            String[] strings_show2;
                            String[] string_show_last;

                            size_two = args[index - 1].substring(2);
                            strings_show2 = calculate_number_to_array(Integer.parseInt(size_two), number_two.toCharArray());

                            size = (size.compareTo(size_two) >= 0) ? size : size_two;
                            size_int = Integer.parseInt(size);
                            string_show_last = merge_two_string_array(string_change_font, strings_show2);
                            show_array(size_int, string_show_last);
                        }
                    }
                }
                break;
        }
    }



    public static void main(String[] args) {
        LCD_Number obj = new LCD_Number();
        obj.judge_function(args);
    }
}
