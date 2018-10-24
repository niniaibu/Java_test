package com.company;

import java.util.HashMap;
import java.util.Map;

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


    //间接映射展示函数
    public void show_array(int size, String[] string_show){

        int column = 2 * size + 3;
        // String[] split_output_lists = new String[column];
        StringBuffer split_output_lists = new StringBuffer();
        for (int split_num = 0; split_num < column; split_num ++){
            for (int num = 0; num < string_show.length; num ++) {
                String[] string_lists = string_show[num].split(",");
                split_output_lists.append(string_lists[split_num]);
            }
            // System.out.print(split_output_lists);

            char[] char_array_lists = split_output_lists.toString().toCharArray();
            for (int j = 0; j < char_array_lists.length; j++) {
                if (char_array_lists[j] == '0') {
                    System.out.print(' ');
                } else if (char_array_lists[j] == '1') {
                    System.out.print('|');
                } else {
                    System.out.print('-');
                }
            }
            System.out.println();
            split_output_lists.delete(0,split_output_lists.length());
        }
    }

    //方法重载——单参数
    public void LCD_Number_with_N_size(String number){
        String size = "1";
        int size_num = Integer.parseInt(size);
        char[] number_lists = number.toCharArray();
        String[] string_show = calculate_number_to_array(size_num, number_lists);
        show_array(size_num, string_show);
    }

    //方法重载——双参数
    public void LCD_Number_with_N_size(String number, String size){
        int size_num = Integer.parseInt(size);
        char[] number_lists = number.toCharArray();
        String[] string_show = calculate_number_to_array(size_num, number_lists);
        show_array(size_num, string_show);
    }


    public static void main(String[] args) {
        if (args.length < 2){
            LCD_Number obj1 = new LCD_Number();
            obj1.LCD_Number_with_N_size(args[0]);
        }else if(args.length ==2 ){
            char[] char_size = args[0].toCharArray();
            if(char_size[0] == '-' && char_size[1] == 's'){
                String size = Character.toString(char_size[2]);
                LCD_Number obj2 = new LCD_Number();
                obj2.LCD_Number_with_N_size(args[1], size);
            }
        }
    }
}
