/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode.pkg2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author QDS
 */
public class Day2 {
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input2.txt"));
        ArrayList<String> list = new ArrayList();
        
        while(s.hasNextLine()){
            list.add(s.nextLine());
        }
        s.close();
        loop:
        for(String str : list){
            for(String str2 : list){
                ArrayList<Character> arr1 = new ArrayList(){{for(char c : str.toCharArray()){add(c);}}};
                for(int i = 0; i < str2.length(); i++){
                    if(arr1.get(i) == str2.charAt(i)){
                        arr1.set(i, ' ');
                    }
                }
                if(Collections.frequency(arr1, ' ') == arr1.size()-1){
                    System.out.println(str);
                    System.out.println(str2);
                    System.out.println(arr1.toString().replace("[", "").replace("]", "").replace(", ", ""));
                    break loop;
                }
            }
        }
    }
}
