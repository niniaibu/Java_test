package com.company;

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


        for (int char_num = 0; char_num < number_lists.length; char_num++) {

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
    public String[] change_font(int size, String[] string_show) {

        int num_series_zeros = 0;
        // String[] string_list_sub = new String[2 * size + 3];
        String[] string_change_list = new String[string_show.length];
        for (int j_num = 0; j_num < string_show.length; j_num++) {
            int num_zeros_max = size + 2;
            String[] string_lists = string_show[j_num].split(",");
            for (int i_string = 0; i_string < string_lists.length; i_string++) {
                char[] char_list = string_lists[i_string].toCharArray();
                for (int i_char = 0; i_char < char_list.length; i_char++) {
                    if (char_list[i_char] == '0') {
                        num_series_zeros += 1;
                    } else {
                        break;
                    }
                }
                if (num_series_zeros < num_zeros_max) {
                    num_zeros_max = num_series_zeros;
                }
                num_series_zeros = 0;
            }
            String concat_string = "";
            for (int i_delete = 0; i_delete < string_lists.length; i_delete++) {
                // string_list_sub[i_delete] = string_lists[i_delete].substring(num_zeros_max);
                concat_string = concat_string.concat(string_lists[i_delete].substring(num_zeros_max));
                if (i_delete == string_lists.length - 1) {
                    break;
                }
                concat_string = concat_string.concat(",");
            }
            string_change_list[j_num] = concat_string;
        }
        return string_change_list;
    }


    //间接映射展示函数
    public void show_array(int size, String[] string_show) {

        int column = 2 * size + 3;
        StringBuffer split_output_lists = new StringBuffer();
        for (int split_num = 0; split_num < column; split_num++) {
            for (int num = 0; num < string_show.length; num++) {

                String[] string_lists = string_show[num].split(",");
                if (split_num >= string_lists.length) {
                    for (int j = 0; j < string_lists[0].length(); j++) {
                        split_output_lists.append("0");

                    }
                    split_output_lists.append("0");
                } else {
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
    public String[] merge_two_string_array(String[] strings1, String[] strings2) {
        List<String> string_show_both = new ArrayList<>(Arrays.asList(strings1));
        string_show_both.addAll(Arrays.asList(strings2));
        Object[] objectArray = string_show_both.toArray();
        String[] string_show_last = Arrays.copyOf(objectArray, objectArray.length, String[].class);
        return string_show_last;
    }


    public void judge_param(String[] args) {
        int index_number_deal = 0;
        ArrayList<String[]> string_all_show = new ArrayList<>();
        ArrayList<Integer> size_array = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {//输入所有参数

            boolean flag_number = Character.isDigit(args[i].toCharArray()[0]);

            if (flag_number) {
                //默认参数准备
                int size = 1;
                boolean change_font = false;
                int number_index = 0;
                String number = null;

                for (int j = index_number_deal; j <= i; j++) {
                    if (args[j].startsWith("-s")) {
                        size = Integer.parseInt(args[j].substring(2));
                    } else if (args[j].startsWith("-f")) {
                        change_font = true;
                    } else {
                        number_index = j;
                    }
                }

                number = args[number_index];
                String[] string_prepare = calculate_number_to_array(size, number.toCharArray());
                index_number_deal = i + 1;


                if (change_font) {
                    string_prepare = change_font(size, string_prepare);
                }

                string_all_show.add(string_prepare);
                size_array.add(size);
            }
        }

        String[] string_show_last = string_all_show.get(0);
        int large_size = size_array.get(0);

        for (int list_i = 1; list_i < string_all_show.size(); list_i++) {
            string_show_last = merge_two_string_array(string_show_last, string_all_show.get(list_i));
            if (large_size < size_array.get(list_i)) {
                large_size = size_array.get(list_i);
            }
        }
        show_array(large_size, string_show_last);

    }


    public static void main(String[] args) {
        LCD_Number obj = new LCD_Number();
        obj.judge_param(args);
    }
}
