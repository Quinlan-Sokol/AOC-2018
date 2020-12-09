/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode.pkg2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author QDS
 */
public class Day19 {
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input19.txt"));
        ArrayList<String> input = new ArrayList();
        int pValue = 0;
        int pIndex = 0;
        
        while(s.hasNextLine()){
            String str = s.nextLine();
            if(str.contains("#"))
                pIndex = Integer.parseInt(str.split(" ")[1]);
            else
                input.add(str);
        }
        s.close();
        
        ArrayList<Integer> reg = new ArrayList(Arrays.asList(1,0,0,0,0,0));
        int counter = 0;
        
        while(pValue < input.size()){
            System.out.println(pValue + ": " + reg);
            
            String opcode = input.get(pValue).split(" ")[0];
            int A = Integer.parseInt(input.get(pValue).split(" ")[1]);
            int B = Integer.parseInt(input.get(pValue).split(" ")[2]);
            int C = Integer.parseInt(input.get(pValue).split(" ")[3]);
            reg.set(pIndex, pValue);
            
            switch(opcode){
                case "seti":
                    reg.set(C, A);
                    break;
                case "setr":
                    reg.set(C, reg.get(A));
                    break;
                case "addi":
                    reg.set(C, reg.get(A) + B);
                    break;
                case "addr":
                    reg.set(C, reg.get(A) + reg.get(B));
                    break;
                case "muli":
                    reg.set(C, reg.get(A) * B);
                    break;
                case "mulr":
                    reg.set(C, reg.get(A) * reg.get(B));
                    break;
                case "eqrr":
                    if(Objects.equals(reg.get(A), reg.get(B)))
                        reg.set(C, 1);
                    else
                        reg.set(C, 0);
                    break;
                case "gtrr":
                    if(reg.get(A) > reg.get(B))
                        reg.set(C, 1);
                    else
                        reg.set(C, 0);
                    break;
            }
            pValue = reg.get(pIndex);
            pValue++;
        }
        System.out.println(reg);
    }
}
