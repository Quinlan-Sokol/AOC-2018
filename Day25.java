/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode.pkg2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author QDS
 */
public class Day25 {
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input25.txt"));
        ArrayList<String> list = new ArrayList();
        
        while(s.hasNextLine()){
            list.add(s.nextLine());
        }
        s.close();
        
        Queue<String> q = new LinkedList();
        int count = 0;
        
        while(!list.isEmpty()){
            q.add(list.get(0));
            count++;
            while(!q.isEmpty()){
                String pos = q.poll();
                list.remove(pos);
                for(String str : list){
                    if(distance(pos, str) <= 3){
                        q.add(str);
                    }
                }
            }
        }
        System.out.println(count);
    }
    public static int distance(String s1, String s2){
        return Math.abs(Integer.parseInt(s1.split(",")[0])-Integer.parseInt(s2.split(",")[0])) + Math.abs(Integer.parseInt(s1.split(",")[1])-Integer.parseInt(s2.split(",")[1])) + Math.abs(Integer.parseInt(s1.split(",")[2])-Integer.parseInt(s2.split(",")[2])) + Math.abs(Integer.parseInt(s1.split(",")[3])-Integer.parseInt(s2.split(",")[3]));
    }
}
