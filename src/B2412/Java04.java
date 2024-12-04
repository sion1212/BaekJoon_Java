package B2412;

import java.util.Scanner;

public class Java04 {
    public void solution(){
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        int len = inputString.length();

        for(int i = 0; i < len ; i++){
            if(inputString.charAt(i) != inputString.charAt(len - 1 - i)){
                System.out.println("0");
                return;
            }
        }
        System.out.println("1");
    }
}
