/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode.pkg2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author QDS
 */
public class Day4 {
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input4.txt"));
        ArrayList<String> list = new ArrayList();
        
        while(s.hasNextLine()){
            list.add(s.nextLine());
        }
        s.close();
        Collections.sort(list);
        
        Map<String, Map<Integer, Integer>> map = new HashMap(); //ID, minutes
        String ID = "";
        int asleep = 0;
        for(String str : list){
            if(str.contains("#")){
                ID = str.split(" ")[3];
                if(!map.containsKey(ID)){
                    Map<Integer, Integer> m = new HashMap();
                    map.put(ID, m);
                }
            }
            else if(str.contains("falls asleep")){
                asleep = Integer.parseInt(str.split("] ")[0].split(" ")[1].split(":")[1]);
            }
            else if(str.contains("wakes up")){
                for(int i = asleep; i < Integer.parseInt(str.split("] ")[0].split(" ")[1].split(":")[1]); i++){
                    if(!map.get(ID).containsKey(i)){
                        map.get(ID).put(i, 1);
                    }else{
                        map.get(ID).put(i, map.get(ID).get(i) + 1);
                    }
                }
            }
        }
        
        int count = 0;
        int minute = 0;
        for (Map.Entry<String, Map<Integer, Integer>> entry : map.entrySet()){
            for (Map.Entry<Integer, Integer> e : entry.getValue().entrySet()){
                if(e.getValue() > count){
                    count = e.getValue();
                    minute = e.getKey();
                    ID = entry.getKey();
                }
            }
        }
        System.out.println(Integer.parseInt(ID.replace("#", "")) * minute);
    }
}
