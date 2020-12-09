/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode.pkg2018;

import java.awt.Point;

/**
 *
 * @author QDS
 */
public class Day11 {
    public static void main(String args[]){
        int[][] grid = new int[300][300];
        int serialNum = 7989;
        
        for(int x = 1; x <= 300; x++){
            for(int y = 1; y <= 300; y++){
                int rackID = x + 10;
                int level = ((rackID * y) + serialNum) * rackID;
                String str = Integer.toString(level);
                if(str.length() >= 3){
                    level = Integer.parseInt(Character.toString(str.charAt(str.length() - 3)));
                }else{
                    level = 0;
                }
                level -= 5;
                
                grid[x-1][y-1] = level;
            }
        }
        int max = -1;
        int bestSize = -1;
        Point best = new Point();
        for(int size = 1; size <= 300; size++){
            for(int x = 0; x < 300; x++){
                if(x+size-1 < 300){
                    for(int y = 0; y < 300; y++){
                        if(y+size-1 < 300){
                            int total = 0;
                            for(int i = x; i <= x+size-1; i++){
                                for(int j = y; j <= y+size-1; j++){
                                    total += grid[i][j];
                                }
                            }
                            if(total > max){
                                max = total;
                                bestSize = size;
                                best = new Point(x, y);
                            }
                        }
                    }
                }
            }
            //System.out.println(size);
        }
        System.out.println(best.x + "," + best.y + "," + bestSize);
    }
}
