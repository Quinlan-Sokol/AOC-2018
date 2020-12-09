/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode.pkg2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author QDS
 */
public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/input1.txt"));
        ArrayList<String> list = new ArrayList();
        
        while(s.hasNextLine()){
            list.add(s.nextLine());
        }
        s.close();
        
        ArrayList<Integer> has = new ArrayList();
        int num = 0;
        loop:
        while(true){
            for(String str : list){
                if(!has.contains(num)){
                    has.add(num);
                    num += Integer.parseInt(str);
                }
                else{
                    break loop;
                }
            }
        }
        System.out.println(num);
    }
}