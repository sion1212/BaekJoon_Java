package B2411;

import java.util.*;

public class Java29 {
    public void solution(){
        Scanner sc = new Scanner(System.in);
        int[] fixed_num = {1,1,2,2,2,8};
        int[] input_num = new int[6];
        int[] output_num = new int[6];

        //입력부분
        for(int i=0 ; i<6 ; i++){
            input_num[i] = sc.nextInt();
        }

        //계산부분
        for(int i=0 ; i<6 ; i++){
            output_num[i] = fixed_num[i] - input_num[i];
        }

        //출력부분
        for(int i=0 ; i<6 ; i++){
            System.out.print(output_num[i]+" ");
        }
    }
}
