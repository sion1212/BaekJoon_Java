package B2412;

import java.util.*;

public class Java05 {
    public void solution(){
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();
        int len = inputStr.length();
        char[] inChar = new char[len];
        int charCount = 0;
        int[] inCount = new int[len];

        inputStr = inputStr.toUpperCase();
        for(int i=1;i<len;i++){
            if(!containChar(inChar,inputStr.charAt(i),charCount)){
                 inChar[charCount++] = inputStr.charAt(i);
                for(int j=i ; j<len ; j++){
                    if(inChar[charCount - 1] == inputStr.charAt(j)){
                        inCount[charCount - 1]++;
                    }
                }
            }
        }

        System.out.println(maxChar(inChar, inCount, charCount));
    }

    public boolean containChar(char[] inChar, char c, int count){
        for(int i=1;i<count;i++){
            if(inChar[i]==c){
                return true;
            }
        }
        return false;
    }

    public char maxChar(char[] inChar, int[] inCount, int count){
        int max_index = 0;
        boolean duplicate = false;
        for(int i=1 ; i<count ; i++){
            if(inCount[i]>inCount[max_index]){
                max_index = i;
            }
        }

        for(int i=max_index ; i<count ; i++){
            if(i != max_index && inCount[i] == inCount[max_index]){
                duplicate = true;
                break;
            }
        }

        if(duplicate){
            return '?';
        }
        else{
            return inChar[max_index];
        }
    }
}
