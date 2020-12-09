/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode.pkg2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author QDS
 */
public class Day8 {
    
    public static void main(String args[]){
        String[] input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2".split(" ");
        
        int total = 0;
        Queue<String[]> q = new LinkedList();
        q.add(input);
        
        while(!q.isEmpty()){
            String[] node = q.poll();
            int children = Integer.parseInt(node[0]);
            int meta = Integer.parseInt(node[1]);
            String[] newNode = new String[node.length - 2 - meta];
            for(int i = 2; i < node.length-1; i++){
                if(i < node.length-meta){
                    newNode[i-2] = node[i];
                }else{
                    total += Integer.parseInt(node[i]);
                }
            }System.out.println(Arrays.toString(newNode));
        }
        System.out.println(total);
    }
}
