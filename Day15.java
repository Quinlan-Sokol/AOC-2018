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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author QDS
 */
public class Day15 {
    static int gridX = 9;
    static int gridY = 9;
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input15.txt"));
        
        char[][] grid = new char[gridX][gridY];
        ArrayList<Unit> units = new ArrayList();
        
        int index = 0;
        while(s.hasNextLine()){
            String temp = s.nextLine();
            for(int i = 0; i < temp.length(); i++){
                grid[i][index] = temp.charAt(i);
                if(temp.charAt(i) == 'E'){
                    units.add(new Unit("E", new Point(i,index)));
                    //grid[i][index] = '.';
                }else if(temp.charAt(i) == 'G'){
                    units.add(new Unit("G", new Point(i,index)));
                    //grid[i][index] = '.';
                }
            }
            index++;
        }
        s.close();
        
        for(int k = 0; k < 3; k++){
            readingOrder(units);

            ArrayList<Point> unitPos = new ArrayList(){{for(Unit u : units){add(u.pos);}}};
            for(Unit unit : units){//each units turn
                if(unit.hitPoints > 0){
                    ArrayList<Point> adjacent = new ArrayList();
                    if(unit.pos.x+1 < gridX-1){  
                        if(grid[unit.pos.x+1][unit.pos.y] != '.' && grid[unit.pos.x+1][unit.pos.y] != '#' && Character.toString(grid[unit.pos.x+1][unit.pos.y]).equals(unit.type))
                            adjacent.add(new Point(unit.pos.x+1,unit.pos.y));
                    }
                    if(unit.pos.x-1 > 0){
                        if(grid[unit.pos.x-1][unit.pos.y] != '.' && grid[unit.pos.x-1][unit.pos.y] != '#' && Character.toString(grid[unit.pos.x-1][unit.pos.y]).equals(unit.type))
                            adjacent.add(new Point(unit.pos.x-1,unit.pos.y));
                    }
                    if(unit.pos.y+1 < gridY-1){
                        if(grid[unit.pos.x][unit.pos.y+1] != '.' && grid[unit.pos.x][unit.pos.y+1] != '#' && Character.toString(grid[unit.pos.x][unit.pos.y+1]).equals(unit.type))
                            adjacent.add(new Point(unit.pos.x,unit.pos.y+1));
                    }
                    if(unit.pos.y-1 > 0){  
                        if(grid[unit.pos.x][unit.pos.y-1] != '.' && grid[unit.pos.x][unit.pos.y-1] != '#' && Character.toString(grid[unit.pos.x][unit.pos.y-1]).equals(unit.type))
                            adjacent.add(new Point(unit.pos.x,unit.pos.y-1));
                    }
                    if(!adjacent.isEmpty()){//ATTACK

                    }
                    else{//MOVE
                        for(Unit u : units){//finding adjacent squares
                            if(u != unit && !unit.type.equals(u.type)){
                                if(u.pos.x < gridX-1){
                                    if(grid[u.pos.x+1][u.pos.y] != '#' && !unitPos.contains(new Point(u.pos.x+1, u.pos.y)) && !adjacent.contains(new Point(u.pos.x+1, u.pos.y))){
                                        adjacent.add(new Point(u.pos.x+1, u.pos.y));
                                    }
                                }
                                if(u.pos.x > 0){
                                    if(grid[u.pos.x-1][u.pos.y] != '#' && !unitPos.contains(new Point(u.pos.x-1, u.pos.y)) && !adjacent.contains(new Point(u.pos.x-1, u.pos.y))){
                                        adjacent.add(new Point(u.pos.x-1, u.pos.y));
                                    }
                                }
                                if(u.pos.y < gridY-1){
                                    if(grid[u.pos.x][u.pos.y+1] != '#' && !unitPos.contains(new Point(u.pos.x, u.pos.y+1)) && !adjacent.contains(new Point(u.pos.x, u.pos.y+1))){
                                        adjacent.add(new Point(u.pos.x, u.pos.y+1));
                                    }
                                }
                                if(u.pos.y > 0){
                                    if(grid[u.pos.x][u.pos.y-1] != '#' && !unitPos.contains(new Point(u.pos.x, u.pos.y-1)) && !adjacent.contains(new Point(u.pos.x, u.pos.y-1))){
                                        adjacent.add(new Point(u.pos.x, u.pos.y-1));
                                    }
                                }
                            }
                        }
                        //elimating unreachable and farthest points
                        ArrayList<Point> remove = new ArrayList();
                        int minDis = Integer.MAX_VALUE;
                        for(Point p : adjacent){
                            if(distanceToPoint(grid, p, unit.pos) == -1){
                                remove.add(p);
                            }else if(distanceToPoint(grid, p, unit.pos) < minDis){
                                minDis = distanceToPoint(grid, p, unit.pos);
                            }
                        }
                        adjacent.removeAll(remove);
                        Point chosen = new Point(Integer.MAX_VALUE,Integer.MAX_VALUE);
                        for(Point p : adjacent){
                            if(distanceToPoint(grid, p, unit.pos) == minDis){
                                   if(p.y < chosen.y){
                                       chosen = new Point(p.x, p.y);
                                   }else if(p.y == chosen.y && p.x < chosen.x){
                                       chosen = new Point(p.x, p.y);
                                   }
                               }
                        }

                        if(!chosen.equals(new Point(Integer.MAX_VALUE,Integer.MAX_VALUE))){//can move
                            adjacent.clear();
                            minDis = Integer.MAX_VALUE;
                            if(unit.pos.y+1 < gridY-1){
                                if(grid[unit.pos.x][unit.pos.y+1] == '.'){
                                    if(distanceToPoint(grid, new Point(unit.pos.x,unit.pos.y+1), chosen) <= minDis)   minDis = distanceToPoint(grid, new Point(unit.pos.x,unit.pos.y+1), chosen);
                                    adjacent.add(new Point(unit.pos.x,unit.pos.y+1));
                                }
                            }
                            if(unit.pos.x-1 > 0){
                                if(grid[unit.pos.x-1][unit.pos.y] == '.'){
                                    if(distanceToPoint(grid, new Point(unit.pos.x-1,unit.pos.y), chosen) <= minDis)   minDis = distanceToPoint(grid, new Point(unit.pos.x-1,unit.pos.y), chosen);
                                    adjacent.add(new Point(unit.pos.x-1,unit.pos.y));
                                }
                            }
                            if(unit.pos.x+1 < gridX-1){  
                                if(grid[unit.pos.x+1][unit.pos.y] == '.'){
                                    if(distanceToPoint(grid, new Point(unit.pos.x+1,unit.pos.y), chosen) <= minDis)   minDis = distanceToPoint(grid, new Point(unit.pos.x+1,unit.pos.y), chosen);
                                    adjacent.add(new Point(unit.pos.x+1,unit.pos.y));
                                }
                            }
                            if(unit.pos.y-1 > 0){  
                                if(grid[unit.pos.x][unit.pos.y-1] == '.'){
                                    if(distanceToPoint(grid, new Point(unit.pos.x,unit.pos.y-1), chosen) <= minDis)   minDis = distanceToPoint(grid, new Point(unit.pos.x,unit.pos.y-1), chosen);
                                    adjacent.add(new Point(unit.pos.x,unit.pos.y-1));
                                }
                            }
                            Point move = new Point(Integer.MAX_VALUE,Integer.MAX_VALUE);
                            for(Point p : adjacent){
                               if(distanceToPoint(grid, chosen, p) == minDis){
                                   if(p.y < move.y){
                                       move = new Point(p.x, p.y);
                                   }else if(p.y == move.y && p.x < move.x){
                                       move = new Point(p.x, p.y);
                                   }
                               }
                            }

                            //make the move
                            if(distanceToPoint(grid, chosen, move) < distanceToPoint(grid, chosen, unit.pos) && distanceToPoint(grid, chosen, move) != -1){
                                grid[unit.pos.x][unit.pos.y] = '.';
                                unit.pos = move;
                                grid[unit.pos.x][unit.pos.y] = unit.type.charAt(0);
                            }
                            
                        }
                    }
                }
            }
            System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
        }
    }
    public static int distanceToPoint(char[][] grid, Point end, Point start){
        Queue<Node> q = new LinkedList();
        q.add(new Node(start, 0));
        boolean[][] visited = new boolean[gridX][gridY];
        visited[start.x][start.y] = true;
        int minDis = Integer.MAX_VALUE;
        
        while(!q.isEmpty()){
            Node n = q.poll();
            Point p = n.pos;
            int dist = n.distance;
            
            if(p.equals(end)){
                minDis = dist;
                return minDis;
            }
            
            if(p.x+1 < gridX && grid[p.x+1][p.y] == '.' && !visited[p.x+1][p.y]){
                visited[p.x+1][p.y] = true;
                q.add(new Node(new Point(p.x+1,p.y), dist + 1));
            }
            if(p.x-1 > 0 && grid[p.x-1][p.y] == '.' && !visited[p.x-1][p.y]){
                visited[p.x-1][p.y] = true;
                q.add(new Node(new Point(p.x-1,p.y), dist + 1));
            }
            if(p.y+1 < gridY && grid[p.x][p.y+1] == '.' && !visited[p.x][p.y+1]){
                visited[p.x][p.y+1] = true;
                q.add(new Node(new Point(p.x,p.y+1), dist + 1));
            }
            if(p.y-1 > 0 && grid[p.x][p.y-1] == '.' && !visited[p.x][p.y-1]){
                visited[p.x][p.y-1] = true;
                q.add(new Node(new Point(p.x,p.y-1), dist + 1));
            }
        }
        
        return -1;
    }
    public static void readingOrder(ArrayList<Unit> arr){
        ArrayList<Unit> newArr = new ArrayList();
        for(int y = 0; y < gridY; y++){
            for(int x = 0; x < gridX; x++){
                for(Unit u : arr){
                    if(u.pos.equals(new Point(x,y))){
                        newArr.add(u);
                    }
                }
            }
        }
        arr.clear();
        arr.addAll(newArr);
    }
}

class Unit {
    String type;
    int hitPoints = 200;
    int damage = 3;
    Point pos;
    public Unit(String t, Point p){
        type = t;
        pos = p;
    }
}
class Node {
    Point pos;
    int distance;
    public Node(Point p, int d){
        pos = p;
        distance = d;
    }
}