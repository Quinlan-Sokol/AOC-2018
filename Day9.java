/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode.pkg2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author QDS
 */
public class Day9 {
    public static void main(String args[]){//change to start with array of 7179001, iterate through array through circle to find 9th pos
        int players = 459;
        int counter = 1;
        List<Integer> circle = new LinkedList();
        circle.add(0, 0);
        Map<Integer, Integer> scores = new HashMap(){{for(int i = 1; i <= players; i++){put(i,0);}}};
        int pos = 1;
        int lastMarble = 7179000;
        int size = 0;
        
        for(int k = 1; k <= lastMarble; k++){  
            counter = counter == players ? 1 : counter+1;
            if(k % 23 == 0){
                int index = pos-9 < 0 ? size - Math.abs(pos-9) : pos-9;
                scores.put(counter, scores.get(counter) + k + circle.get(index));
                size--;
                circle.remove(index); 
                pos = index+2 > size ? index+2-size : index+2;
            }
            else{
                size++;
                circle.add(pos, k);
                pos = pos+2 > size ? pos+2-size : pos+2;
            } 
        }
        int max = -1;
        for(int i : scores.values()){
            if(i > max) max = i;
        }
        System.out.println(max);
    }
}
