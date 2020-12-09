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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author QDS
 */
public class Day6 {
    
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input6.txt"));
        
        ArrayList<Point> list = new ArrayList();
        Set<Point> edge = new HashSet();
        Map<Point, Integer> map = new HashMap();
        int maxX = -1;
        int maxY = -1;
        int count = 0;
        
        while(s.hasNextLine()){
            String temp = s.nextLine();
            if(Integer.parseInt(temp.split(", ")[0]) > maxX) 
                maxX = Integer.parseInt(temp.split(", ")[0]);
            if(Integer.parseInt(temp.split(", ")[1]) > maxY) 
                maxY = Integer.parseInt(temp.split(", ")[1]);
            list.add(new Point(Integer.parseInt(temp.split(", ")[0]), Integer.parseInt(temp.split(", ")[1])));
            map.put(new Point(Integer.parseInt(temp.split(", ")[0]), Integer.parseInt(temp.split(", ")[1])), 1);
        }
        s.close();
        
        for(int x = 0; x < maxX+1; x++){
            for(int y = 0; y < maxY+1; y++){
                //part 2
                int total = 0;
                for(Point p : list){
                    int d = Math.abs(p.x - x) + Math.abs(p.y - y);
                    total += d;
                }
                if(total < 10000)
                    count++;
                
                if(!list.contains(new Point(x,y))){//not key point
                    Point best = new Point(-1,-1);
                    int dis = Integer.MAX_VALUE;
                    boolean equal = false;
                    
                    for(Point p : list){
                        int tempDis = Math.abs(p.x - x) + Math.abs(p.y - y);
                        
                        if(tempDis < dis){ //closer
                            dis = tempDis;
                            best = p;
                            equal = false;
                        }else if(tempDis == dis){//equal distance
                            equal = true;
                        }
                    }
                    
                    if(best.getX() != -1 && best.getY() != -1 && !equal) {
                        if(x == 0 || x == maxX || y == 0 || y == maxY){
                            edge.add(best);
                        }
                        map.put(best, map.get(best)+1);
                    }
                }
            }
        }
        
        for(Point p : edge){
            map.remove(p);
        }
        
        int max = -1;
        for(int i : map.values()){
            if(i > max)
                max = i;
        }
        System.out.println(count);
    }
}
