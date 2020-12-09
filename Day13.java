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
import java.util.Scanner;

/**
 *
 * @author QDS
 */
public class Day13 {
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input13.txt"));
        ArrayList<String> temp = new ArrayList();
        ArrayList<Cart> carts = new ArrayList();
        
        
        while(s.hasNextLine()){
            temp.add(s.nextLine());
        }
        s.close();
        
        int count = 0;
        char[][] grid = new char[temp.size()][temp.get(0).length()];
        for(int i = 0; i < temp.size(); i++){
            for(int j = 0; j < temp.get(i).length(); j++){
                switch (temp.get(i).charAt(j)) {
                    case '^':
                        carts.add(new Cart(new Point(j, i), 0, count));
                        grid[i][j] = '|';
                        count++;
                        break;
                    case 'v':
                        carts.add(new Cart(new Point(j, i), 2, count));
                        grid[i][j] = '|';
                        count++;
                        break;
                    case '<':
                        carts.add(new Cart(new Point(j, i), 3, count));
                        grid[i][j] = '-';
                        count++;
                        break;
                    case '>':
                        carts.add(new Cart(new Point(j, i), 1, count));
                        grid[i][j] = '-';
                        count++;
                        break;
                    default:
                        grid[i][j] = temp.get(i).charAt(j);
                        break;
                }
            }
        }
        ArrayList<Integer> remove = new ArrayList();
        
        while(carts.size() > 1){
            if(!remove.isEmpty()){
                for(int i = 0; i < carts.size(); i++){
                    if(remove.contains(carts.get(i).ID)){
                        carts.remove(i);
                        i--;
                    }
                }
                remove.clear();
            }
            if(carts.size() == 1){
                break;
            }
            
            for(Cart c : carts){
                if(!c.removing){
                    c.update();
                    //System.out.println(c.pos);

                    for(Cart c2 : carts){
                        if(c.ID != c2.ID && c.pos.distance(c2.pos) == 0){//collision
                            remove.add(c.ID);
                            remove.add(c2.ID);
                            c.removing = true;
                            c2.removing = true;
                        }
                    }

                    char track = grid[c.pos.y][c.pos.x];
                    switch(track){
                        case '/':
                            if(c.direction == 0 || c.direction == 2){
                                c.direction += 1;
                            }else if(c.direction == 1 ||c.direction == 3){
                                c.direction -= 1;
                            }
                            break;
                        case '\\':
                            if(c.direction == 0){
                                c.direction = 3;
                            }else if(c.direction == 1){
                                c.direction = 2;
                            }else if(c.direction == 2){
                                c.direction = 1;
                            }else if(c.direction == 3){
                                c.direction = 0;
                            }
                            break;
                        case '+':
                            if(c.turnCounter == 0){//left
                                c.direction--;
                                if(c.direction < 0) c.direction = 3;
                            }else if(c.turnCounter == 2){//right
                                c.direction++;
                                if(c.direction > 3) c.direction = 0;
                            }
                            c.turnCounter++;
                            if(c.turnCounter > 2)   c.turnCounter = 0;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        //carts.get(0).update();
        System.out.println(carts.get(0).pos);
    }
}
class Cart {
    int ID;
    int turnCounter = 0;
    Point pos;
    boolean removing = false;
    int direction;
    public Cart(Point p, int d, int id){ //0 = north  1 = east  2 = south  3 = west
        pos = p;
        direction = d;
        ID = id;
    }
    public void update(){
        switch(direction){
            case 0:
                pos.translate(0, -1);
                break;
            case 1:
                pos.translate(1, 0);
                break;
            case 2:
                pos.translate(0, 1);
                break;
            case 3:
                pos.translate(-1, 0);
                break;
        }
    }
}
