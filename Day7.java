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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author QDS
 */
public class Day7 {
    
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input7.txt"));
        
        Map<String, ArrayList<String>> rules = new HashMap();
        ArrayList<String> values = new ArrayList();
        while(s.hasNextLine()){
            String temp = s.nextLine();
            ArrayList<String> arr = rules.containsKey(temp.split(" ")[1]) ? rules.get(temp.split(" ")[1]) : new ArrayList();
            arr.add(temp.split(" ")[7]);
            rules.put(temp.split(" ")[1], arr);
            values.add(temp.split(" ")[7]);
        }
        s.close();
        String result = "";
        Queue<String> q = new LinkedList();
        
        Set<String> temp = new HashSet(rules.keySet());
        temp.removeAll(values);
        ArrayList<String> list = new ArrayList(){{addAll(temp);}};
        Collections.sort(list);
        
        int time = 0;
        int workerNum = 2;
        ArrayList<Worker> workers = new ArrayList(){{for(int i = 0; i < workerNum; i++){add(new Worker());}}};
        for(int i = 0; i < list.size(); i++){
            workers.get(i).step = list.get(i);
            //workers.get(i).stepTime = list.get(i);
        }
        
        while(true){
            String key = q.poll();
            result += key;
            
            if(rules.containsKey(key)){
                for(String step : rules.get(key)){
                    boolean reqMet = true;
                    for(String str : rules.keySet()){
                        if(!str.equals(key) && rules.get(str).contains(step)){
                            reqMet = false;
                        }
                    }
                    if(reqMet){
                        q.add(step);
                    }
                }
                rules.put(key, new ArrayList());


                ArrayList<String> arr = new ArrayList();
                arr.addAll(q);
                q.clear();
                Collections.sort(arr);
                q.addAll(arr);
            }
        }
        //System.out.println(result);
    }
}

class Worker {
    String step;
    int stepTime;
    int counter = 0;
    boolean working = false;
    public Worker(){
        
    }
    public void update(){
        if(working){
            if(counter == stepTime){
                counter = 0;
                working = false;
            }else{
                counter++;
            }
        }
    }
}
