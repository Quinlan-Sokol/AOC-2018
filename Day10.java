/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode.pkg2018;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author QDS
 */
public class Day10 {
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input10.txt"));
        ArrayList<Point> pos = new ArrayList();
        ArrayList<Point> vel = new ArrayList();
        
        while(s.hasNextLine()){
            String str = s.nextLine();
            pos.add(new Point(Integer.parseInt(str.split("> ")[0].split("=<")[1].split(", ")[0].trim()), Integer.parseInt(str.split("> ")[0].split("=<")[1].split(", ")[1].trim())));
            vel.add(new Point(Integer.parseInt(str.split("> ")[1].split("=<")[1].split(", ")[0].trim()), Integer.parseInt(str.split("> ")[1].split("=<")[1].split(", ")[1].replace(">", "").trim())));
        }
        s.close();
        int count = 0;
        
        while(true){
            count++;
            int maxX = -1;
            int maxY = -1;
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            for(int i = 0; i < pos.size(); i++){
                Point temp = pos.get(i);
                temp.translate(vel.get(i).x, vel.get(i).y);
                pos.set(i, temp);
                if(temp.x > maxX)
                    maxX = temp.x;
                if(temp.y > maxY)
                    maxY = temp.y;
                if(temp.x < minX)
                    minX = temp.x;
                if(temp.y < minY)
                    minY = temp.y;
            }
            
            //String[][] grid = new String[Math.abs(maxX - minX)+1][Math.abs(maxY - minY)+1];
            if(Math.abs(maxY - minY)+1 < 100 && Math.abs(maxX - minX)+1 < 100){
                int pointX = minX;
                for(int x = 0; x < Math.abs(maxX - minX)+1; x++){
                    int pointY = minY;
                    System.out.println();
                    for(int y = 0; y < Math.abs(maxY - minY)+1; y++){
                        if(pos.contains(new Point(pointX, pointY))){
                            //grid[x][y] = "#";
                            System.out.print("#");
                        }else{
                            //grid[x][y] = ".";
                            System.out.print(" ");
                        }
                        pointY++;
                    }
                    pointX++;
                }
                System.out.println("-----" + count + "-----");
            }
        }
    }
}
