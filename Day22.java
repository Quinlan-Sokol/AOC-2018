/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode.pkg2018;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author QDS
 */
public class Day22 {
    public static void main(String args[]){
        int depth = 510;
        Point target = new Point(10,10);
        int[][] grid = new int[target.x+101][target.y+101];
        int[][] eGrid = new int[target.x+101][target.y+101];
        
        for(int x = 0; x < target.x+101; x++){
            for(int y = 0; y < target.y+101; y++){
                int geoIndex;
                if((x == 0 && y == 0) || (x == target.x && y == target.y)){
                    geoIndex = 0;
                }else if(y == 0){
                    geoIndex = x*16807;
                }else if(x == 0){
                    geoIndex = y*48271;
                }else{
                    geoIndex = eGrid[x-1][y]*eGrid[x][y-1];
                }
                
                int erosionLevel = (geoIndex + depth) % 20183;
                eGrid[x][y] = erosionLevel;
                grid[x][y] = erosionLevel % 3; //0=rocky   1=wet   2=narrow
            }
        }
        //System.out.println(Arrays.deepToString(grid).replace("], ", "\n").replace("[", "").replace(", ", "").replace("]", "").replace("0", ".").replace("1", "=").replace("2", "|"));
        
        Queue<Move> q = new LinkedList();
        q.add(new Move(new Point(0,0), 0, "torch"));
        boolean[][] visited = new boolean[target.x+101][target.y+101];
        visited[0][0] = true;
        int minTime = Integer.MAX_VALUE;
        ArrayList<String> tools = new ArrayList();
        ArrayList<Integer> times = new ArrayList();
        
        while(!q.isEmpty()){
            Move n = q.poll();
            Point p = n.pos;
            int time = n.time;
            String oldTool = n.tool;
            
            tools.clear();
            switch(grid[p.x][p.y]){
                case 0:
                    tools.add("torch");
                    tools.add("gear");
                    break;
                case 1:
                    tools.add("gear");
                    tools.add("neither");
                    break;
                case 2:
                    tools.add("torch");
                    tools.add("neither");
                    break;
            }
            
            if(p.equals(target)){
                times.add(time);
                if(times.size() >= 100){
                    break;
                }
            }
            
            if(p.x+1 < target.x+101 && !visited[p.x+1][p.y]){
                visited[p.x+1][p.y] = true;
                for(String tool : tools){
                    if(isValid(grid, new Point(p.x+1,p.y), tool)){
                        if(tool.equals(oldTool)){
                            q.add(new Move(new Point(p.x+1,p.y), time + 1, tool));
                        }else{
                            q.add(new Move(new Point(p.x+1,p.y), time + 8, tool));
                        }
                    }
                }
            }
            if(p.x-1 > 0 && !visited[p.x-1][p.y]){
                visited[p.x-1][p.y] = true;
                for(String tool : tools){
                    if(isValid(grid, new Point(p.x-1,p.y), tool)){
                        if(tool.equals(oldTool)){
                            q.add(new Move(new Point(p.x-1,p.y), time + 1, tool));
                        }else{
                            q.add(new Move(new Point(p.x-1,p.y), time + 8, tool));
                        }
                    }
                }
            }
            if(p.y+1 < target.y+101 && !visited[p.x][p.y+1]){
                visited[p.x][p.y+1] = true;
                for(String tool : tools){
                    if(isValid(grid, new Point(p.x,p.y+1), tool)){
                        if(tool.equals(oldTool)){
                            q.add(new Move(new Point(p.x,p.y+1), time + 1, tool));
                        }else{
                            q.add(new Move(new Point(p.x,p.y+1), time + 8, tool));
                        }
                    }
                }
            }
            if(p.y-1 > 0 && !visited[p.x][p.y-1]){
                visited[p.x][p.y-1] = true;
                for(String tool : tools){
                    if(isValid(grid, new Point(p.x,p.y-1), tool)){
                        if(tool.equals(oldTool)){
                            q.add(new Move(new Point(p.x,p.y-1), time + 1, tool));
                        }else{
                            q.add(new Move(new Point(p.x,p.y-1), time + 8, tool));
                        }
                    }
                }
            }
        }
        System.out.println(times);
    }
    public static boolean isValid(int[][] grid, Point p, String tool){
        switch(grid[p.x][p.y]){
            case 0://rocky
                if(tool.equals("neither"))
                    return false;
                break;
            case 1://wet
                if(tool.equals("torch"))
                    return false;
                break;
            case 2://narrow
                if(tool.equals("gear"))
                    return false;
                break;
        }
        return true;
    }
}
class Move {
    Point pos;
    int time;
    String tool;
    public Move(Point p, int t, String tool){
        pos = p;
        time = t;
        this.tool = tool;
    }
}
