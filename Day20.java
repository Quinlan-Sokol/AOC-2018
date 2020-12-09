/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode.pkg2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author QDS
 */
public class Day20 {
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input20.txt"));
        String regex = "";
        
        while(s.hasNextLine()){
            regex += s.nextLine();
        }
        s.close();
        regex = regex.replace("^", "").replace("$", "");
        
        
    }
}
