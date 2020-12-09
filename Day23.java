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
public class Day23 {
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input23.txt"));
        ArrayList<Bot> bots = new ArrayList();
        String bot = "";
        int max = -1;
        
        while(s.hasNextLine()){
            String temp = s.nextLine();
            bots.add(new Bot(Integer.parseInt(temp.split(", ")[0].split("=")[1].split(",")[0].replace("<", "")), Integer.parseInt(temp.split(", ")[0].split("=")[1].split(",")[1]), Integer.parseInt(temp.split(", ")[0].split("=")[1].split(",")[2].replace(">", "")), Integer.parseInt(temp.split(", ")[1].split("=")[1])));
            if(Integer.parseInt(temp.split(", ")[1].split("=")[1]) > max){
                bot = temp;
                max = Integer.parseInt(temp.split(", ")[1].split("=")[1]);
            }
            
        }
        s.close();
        
        int count = 0;
        int signal = Integer.parseInt(bot.split(", ")[1].split("=")[1]);
        int X = Integer.parseInt(bot.split(", ")[0].split("=")[1].split(",")[0].replace("<", ""));
        int Y = Integer.parseInt(bot.split(", ")[0].split("=")[1].split(",")[1]);
        int Z = Integer.parseInt(bot.split(", ")[0].split("=")[1].split(",")[2].replace(">", ""));
        for(Bot b : bots){
            int dist = Math.abs(b.x - X) + Math.abs(b.y - Y) + Math.abs(b.z - Z);
            if(dist <= signal){
                count++;
            }
        }
        System.out.println(count);
    }
}
class Bot {
    int signal;
    int x;
    int y;
    int z;
    public Bot(int x, int y, int z, int r){
        this.x = x;
        this.y = y;
        this.z = z;
        signal = r;
    }
}
