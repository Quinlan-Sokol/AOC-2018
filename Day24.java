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
import java.util.Scanner;

/**
 *
 * @author QDS
 */
public class Day24 {
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input24.txt"));
        ArrayList<Group> groups = new ArrayList();
        String type = "system";
        
        while(s.hasNextLine()){
            String temp = s.nextLine();
            if(temp.contains("Infection"))
                type = "infection";
            else{
                int hp = Integer.parseInt(temp.split(" ")[4]);
                int u = Integer.parseInt(temp.split(" ")[0]);
                int a = Integer.parseInt(temp.split(" ")[temp.split(" ").length-6]);
                String aT = temp.split(" ")[temp.split(" ").length-5];
                int i = Integer.parseInt(temp.split(" ")[temp.split(" ").length-1]);
                ArrayList<String> w = new ArrayList();
                ArrayList<String> I = new ArrayList();
                if(temp.contains("(")){
                    temp = temp.split("\\(")[1].split("\\)")[0];
                    for(String str : temp.split("; ")){
                        if(str.contains("weak")){
                            for(String term : str.replace("weak to ", "").split(", ")){
                                w.add(term);
                            }
                        }else{
                            for(String term : str.replace("immune to ", "").split(", ")){
                                I.add(term);
                            }
                        }
                    }
                }
                
                groups.add(new Group(type,hp,u,a,aT,i,w,I));
            }
        }
        s.close();
        
        while(true){
        
            Collections.sort(groups, (Group g1, Group g2) -> {
                if(g1.power == g2.power){
                    return Integer.toString(g1.initiative).compareTo(Integer.toString(g2.initiative));
                }else{
                    return Integer.toString(g1.power).compareTo(Integer.toString(g2.power));
                }
            });
            
            for(Group group : groups){//targeting
                 int maxDamage = -1;
                 Group target = null;
                 
                 for(Group g : groups){
                     if(!g.equals(group) && !g.type.equals(group.type) && !g.isTargeted){
                         int damage;
                         if(g.immunities.contains(group.attackType)){
                             damage = 0;
                         }else if(g.weaknesses.contains(group.attackType)){
                             damage = group.power*2;
                         }else{
                             damage = group.power;
                         }
                         if(damage > maxDamage){
                             maxDamage = damage;
                             target = g;
                         }
                     }
                 }
                 group.hasTarget = false;
                 if(target != null){
                    int index = groups.indexOf(target);
                    target.isTargeted = true;
                    group.hasTarget = true;
                    groups.set(index, target);
                 }
            }
            
            Collections.sort(groups, (Group g1, Group g2) -> {
                return Integer.toString(g1.initiative).compareTo(Integer.toString(g2.initiative));
            });
            
            //ATTACK
            for(int i = 0; i < groups.size();i++){
                if(groups.get(i).hasTarget){//NOT WORKING target null
                    int damage;
                    
                    if(groups.get(i).target.immunities.contains(groups.get(i).attackType)){
                        damage = 0;
                    }else if(groups.get(i).target.weaknesses.contains(groups.get(i).attackType)){
                        damage = groups.get(i).power*2;
                    }else{
                        damage = groups.get(i).power;
                    }
                    
                    
                }
            }
            
            ArrayList<Group> remove = new ArrayList();
            for(Group g : groups){
                if(g.units == 0)    remove.add(g);
                else    g.update();
                //break statement
            }
            groups.removeAll(remove);
        }
    }
}
class Group {
    int hitPoints;
    int units;
    int attack;
    String attackType;
    String type;
    int initiative;
    int power;
    ArrayList<String> weaknesses;
    ArrayList<String> immunities;
    boolean isTargeted = false;
    boolean hasTarget = false;
    Group target;
    
    public Group(String t, int hp, int u, int a, String aT, int i, ArrayList<String> w, ArrayList<String> I){
        hitPoints = hp;
        type = t;
        units = u;
        attack = a;
        attackType = aT;
        initiative = i;
        weaknesses = w;
        immunities = I;
        power = a * u;
    }
    public void update(){
        power = units * attack;
    }
}