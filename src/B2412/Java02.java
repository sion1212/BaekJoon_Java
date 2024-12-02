package B2412;

import java.util.Scanner;

public class Java02 {
    public void solution(){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        for(int i=0 ; i <num ; i++){
            for(int j=i ; j<num-1 ; j++){printBlank();}
            for(int k=0 ; k<2*i+1 ; k++){printStar();}
            printNewLine();
        }

        for(int i=1 ; i <num ; i++){
            for(int j=num-1-i ; j<num-1 ; j++){printBlank();}
            for(int k=0 ; k<2*(num-i-1)+1 ; k++){printStar();}
            printNewLine();
        }

    }

    public void printBlank(){
        System.out.print(" ");
    }

    public void printStar(){
        System.out.print("*");
    }

    public void printNewLine(){
        System.out.print("\n");
    }
}
