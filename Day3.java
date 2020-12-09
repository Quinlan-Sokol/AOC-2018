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
public class Day3 {
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input3.txt"));
        ArrayList<String> list = new ArrayList();
        
        while(s.hasNextLine()){
            list.add(s.nextLine());
        }
        s.close();
        
        int[][] grid = new int[1000][1000];
        
        for(String str : list){
            int x = Integer.parseInt(str.split(" ")[2].split(",")[0]);
            int y = Integer.parseInt(str.split(" ")[2].split(",")[1].replace(":", ""));
            int sizeX = Integer.parseInt(str.split(" ")[3].split("x")[0]);
            int sizeY = Integer.parseInt(str.split(" ")[3].split("x")[1]);
            
            for(int i = x; i < x+sizeX; i++){
                for(int j = y; j < y+sizeY; j++){
                    grid[i][j] += 1;
                }
            }
        }
        
        for(String str : list){
            int x = Integer.parseInt(str.split(" ")[2].split(",")[0]);
            int y = Integer.parseInt(str.split(" ")[2].split(",")[1].replace(":", ""));
            int sizeX = Integer.parseInt(str.split(" ")[3].split("x")[0]);
            int sizeY = Integer.parseInt(str.split(" ")[3].split("x")[1]);
            
            boolean bool = false;
            for(int i = x; i < x+sizeX; i++){
                for(int j = y; j < y+sizeY; j++){
                    if(grid[i][j] > 1)  bool = true;
                }
            }
            if(!bool){
                System.out.println(str);
            }
        }
    }
}
