/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode.pkg2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author QDS
 */
public class Day18 {
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input18.txt"));
        char[][] grid = new char[50][50];
        
        int X = 0;
        int Y = 0;
        while(s.hasNextLine()){
            for(char c : s.nextLine().toCharArray()){
                grid[X][Y] = c;
                X++;
            }
            Y++;
            X = 0;
        }
        s.close();
        
        ArrayList<Integer> nums = new ArrayList();
        
        //195952
        
        System.out.println((1000000000 - 436) % 28);
        
        for(int k = 0; k < 440; k++){//minute 436     190896
            char[][] newGrid = new char[50][50];
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid.length; j++){
                    char c = grid[i][j];
                    char newAcre = c;
                    ArrayList<Character> adjacent = new ArrayList();
                    getAdjacent(adjacent, grid, i, j);
                    
                    switch(c){
                        case '.':
                            if(Collections.frequency(adjacent, '|') >= 3)
                                newAcre = '|';
                            break;
                        case '|':
                            if(Collections.frequency(adjacent, '#') >= 3)
                                newAcre = '#';
                            break;
                        case '#':
                            if(Collections.frequency(adjacent, '#') < 1 || Collections.frequency(adjacent, '|') < 1)
                                newAcre = '.';
                            break;
                    }
                    newGrid[i][j] = newAcre;
                }
            }
            grid = newGrid.clone();
            
            ArrayList<Character> list = new ArrayList();
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid.length; j++){
                    list.add(grid[i][j]);
                }
            }
            if(nums.contains(Collections.frequency(list, '|') * Collections.frequency(list, '#')) && k >= 435){
                //System.out.println(k+1);
                //System.out.println(Collections.frequency(list, '|') * Collections.frequency(list, '#'));
            }System.out.println(Collections.frequency(list, '|') * Collections.frequency(list, '#'));
            nums.add(Collections.frequency(list, '|') * Collections.frequency(list, '#'));
        }
    }
    public static void getAdjacent(ArrayList<Character> arr, char[][] grid, int x, int y){
        if(x+1 < grid.length){
            arr.add(grid[x+1][y]);
            if(y+1 < grid.length)
                arr.add(grid[x+1][y+1]);
            if(y-1 >= 0)
                arr.add(grid[x+1][y-1]);
        }
        if(x-1 >= 0){
            arr.add(grid[x-1][y]);
            if(y+1 < grid.length)
                arr.add(grid[x-1][y+1]);
            if(y-1 >= 0)
                arr.add(grid[x-1][y-1]);
        }
        if(y-1 >= 0)
            arr.add(grid[x][y-1]);
        if(y+1 < grid.length)
            arr.add(grid[x][y+1]);
    }
}
