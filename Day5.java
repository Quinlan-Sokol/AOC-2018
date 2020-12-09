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
public class Day5 {
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input5.txt"));
        
        String in = "";
        while(s.hasNextLine()){
            in += s.nextLine();
        }
        s.close();
        ArrayList<Integer> list = new ArrayList();
        
        for(int n = 65; n < 91; n++){//go through letters
            String temp = in.replaceAll(Character.toString((char)n), "").replaceAll(Character.toString(Character.toLowerCase((char)n)), "");
            StringBuilder input = new StringBuilder(temp);
            for(int k = 0; k < 10000; k++){
                for(int i = 0; i < input.length()-1; i++){
                    if(Math.abs(input.charAt(i) - input.charAt(i+1)) == 32){
                        input.delete(i, i+2);
                    }
                }
            }
            list.add(input.length());
        }
        System.out.println(Collections.min(list));
    }
}
