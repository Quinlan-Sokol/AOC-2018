/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode.pkg2018;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author QDS
 */
public class Day14 {
    public static void main(String args[]) {
        //ArrayList<Integer> recipes = new ArrayList(Arrays.asList(3,7));
        StringBuilder recipes = new StringBuilder("37");
        ArrayList<Integer> elves = new ArrayList(Arrays.asList(0,1));
        String input = "513401";
        String result = "";
        
        while(true){
            int recipe = 0;
            for(int i : elves){
                recipe += recipes.charAt(i);}
            
            for(char c : Integer.toString(recipe).toCharArray()){
                recipes.append(Character.toString(c));
            }
            
            if(recipes.toString().contains(input)){
                result = recipes.toString();
                break;
            }
            
            for(int i = 0 ; i < elves.size(); i++){
                int newPos = elves.get(i) + Character.getNumericValue(recipes.charAt(elves.get(i))) + 1;
                if(newPos >= recipes.length()){
                    newPos %= recipes.length();
                }
                elves.set(i, newPos);
            }
            //System.out.println(recipes.length() - 10);
        }
        System.out.println(result.subSequence(0, result.indexOf(input)).length());
    }
}
