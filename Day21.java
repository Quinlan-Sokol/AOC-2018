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
public class Day21 {
    static ArrayList<Integer> reg = new ArrayList(Arrays.asList(0,0,0,0,0,0));
    static int pValue = 0;
    static int pIndex = 0;
    static ArrayList<Integer> reg2;
    static int pValue2 = 0;
    public static void main(String args[]) throws FileNotFoundException{
        Scanner s = new Scanner(new File("src/input21.txt"));
        ArrayList<String> input = new ArrayList();
        
        
        while(s.hasNextLine()){
            String str = s.nextLine();
            if(str.contains("#")){
                pIndex = Integer.parseInt(str.split(" ")[1]);
            }else{
                input.add(str);}
        }
        s.close();
        
        ArrayList<String> values = new ArrayList();
        int maxInstr = -1;
        int maxVal = -1;
        
        while(pValue < input.size()){
            if(pValue == 28){
                if(!values.contains(Integer.toString(reg.get(4)))){
                    values.add(Integer.toString(reg.get(4)));
                    
                    reg2 = new ArrayList(Arrays.asList(reg.get(4),0,0,0,0,0));
                    pValue2 = 0;
                    int counter = 0;
                    while(pValue2 < input.size()){
                        performInstruction2(input.get(pValue2));
                        counter++;
                    }
                    if(counter > maxInstr){
                        maxInstr = counter;
                        maxVal = reg.get(4);
                    }else if(counter == maxInstr && reg.get(4) < maxVal){
                        maxVal = reg.get(4);
                    }
                }else{
                    break;
                }
                System.out.println(maxInstr + ": " + maxVal);
            }
            performInstruction(input.get(pValue));
        }
    }
    public static void performInstruction(String input){
        String opcode = input.split(" ")[0];
        int A = Integer.parseInt(input.split(" ")[1]);
        int B = Integer.parseInt(input.split(" ")[2]);
        int C = Integer.parseInt(input.split(" ")[3]);
        reg.set(pIndex, pValue);
        
        switch(opcode){
            case "addr":
                reg.set(C, reg.get(A)+reg.get(B));
                break;
            case "addi":
                reg.set(C, reg.get(A)+B);
                break;
            case "mulr":
                reg.set(C, reg.get(A)*reg.get(B));
                break;
            case "muli":
                reg.set(C, reg.get(A)*B);
                break;
            case "banr":
                reg.set(C, reg.get(A)&reg.get(B));
                break;
            case "bani":
                reg.set(C, reg.get(A)&B);
                break;
            case "borr":
                reg.set(C, reg.get(A)|reg.get(B));
                break;
            case "bori":
                reg.set(C, reg.get(A)|B);
                break;
            case "setr":
                reg.set(C, reg.get(A));
                break;
            case "seti":
                reg.set(C, A);
                break;
            case "gtir":
                if(A > reg.get(B))
                    reg.set(C, 1);
                else
                    reg.set(C, 0);
                break;
            case "gtri":
                if(reg.get(A) > B)
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
            case "eqir":
                if(A == reg.get(B))
                    reg.set(C, 1);
                else
                    reg.set(C, 0);
                break;
            case "eqri":
                if(reg.get(A) == B)
                    reg.set(C, 1);
                else
                    reg.set(C, 0);
                break;
            case "eqrr":
                if(Objects.equals(reg.get(A), reg.get(B)))
                    reg.set(C, 1);
                else
                    reg.set(C, 0);
                break;
        }
        pValue = reg.get(pIndex);
        pValue++;
    }
    public static void performInstruction2(String input){
        String opcode = input.split(" ")[0];
        int A = Integer.parseInt(input.split(" ")[1]);
        int B = Integer.parseInt(input.split(" ")[2]);
        int C = Integer.parseInt(input.split(" ")[3]);
        reg2.set(pIndex, pValue2);
        
        switch(opcode){
            case "addr":
                reg2.set(C, reg2.get(A)+reg2.get(B));
                break;
            case "addi":
                reg2.set(C, reg2.get(A)+B);
                break;
            case "mulr":
                reg2.set(C, reg2.get(A)*reg2.get(B));
                break;
            case "muli":
                reg2.set(C, reg2.get(A)*B);
                break;
            case "banr":
                reg2.set(C, reg2.get(A)&reg2.get(B));
                break;
            case "bani":
                reg2.set(C, reg2.get(A)&B);
                break;
            case "borr":
                reg2.set(C, reg2.get(A)|reg2.get(B));
                break;
            case "bori":
                reg2.set(C, reg2.get(A)|B);
                break;
            case "setr":
                reg2.set(C, reg2.get(A));
                break;
            case "seti":
                reg2.set(C, A);
                break;
            case "gtir":
                if(A > reg2.get(B))
                    reg2.set(C, 1);
                else
                    reg2.set(C, 0);
                break;
            case "gtri":
                if(reg2.get(A) > B)
                    reg2.set(C, 1);
                else
                    reg2.set(C, 0);
                break;
            case "gtrr":
                if(reg2.get(A) > reg2.get(B))
                    reg2.set(C, 1);
                else
                    reg2.set(C, 0);
                break;
            case "eqir":
                if(A == reg2.get(B))
                    reg2.set(C, 1);
                else
                    reg2.set(C, 0);
                break;
            case "eqri":
                if(reg2.get(A) == B)
                    reg2.set(C, 1);
                else
                    reg2.set(C, 0);
                break;
            case "eqrr":
                if(Objects.equals(reg2.get(A), reg2.get(B)))
                    reg2.set(C, 1);
                else
                    reg2.set(C, 0);
                break;
        }
        pValue2 = reg2.get(pIndex);
        pValue2++;
    }
}
