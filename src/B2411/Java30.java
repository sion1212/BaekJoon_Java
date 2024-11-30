package B2411;

import java.util.*;

public class Java30 {
    public void solution(){
        Scanner sc = new Scanner(System.in);
        String inputString;
        int count;
        int[] strokeNum = {3, 2, 1, 2, 3, 3, 3, 3, 1, 1, 3, 1, 3, 3, 1, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 1};
        int[] strokeCal;

        inputString = sc.nextLine();
        count = inputString.length();
        strokeCal = new int[count];

        for(int i = 0; i < count; i++){
            strokeCal[i] = strokeNum[inputString.charAt(i) - 'A'];
        }

        while(count != 1){
            int i;
            for(i=0 ; i<count/2 ; i++){
                strokeCal[i] = mod10(strokeCal[i * 2],strokeCal[i * 2 + 1]);
            }
            if(count % 2 == 1){
                strokeCal[i] = strokeCal[i * 2];
                count = count / 2 + 1;
            }
            else{ count = count / 2; }
        }

        printMessage(strokeCal[0]);
    }

    public int mod10(int a, int b){
        int result = a + b;
        if(result >= 10) result = result % 10;
        return result;
    }

    public void printMessage(int a){
        if(a % 2 == 1){System.out.println("I'm a winner!");}
        else{System.out.println("You're the winner?");}
    }
}
