package B2412;

import java.util.Scanner;

public class Java17 {
    public class Coordinate{
        int x,y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {return x;}
        public int getY() {return y;}
    }

    public void solution(){
        int n, m, k;
        int energy = 0;
        double sl, in;
        boolean clockwise;
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        Coordinate[] c1 = new Coordinate[n];
        Coordinate[] c2 = new Coordinate[m];
        Coordinate[] c3 = new Coordinate[k];

        for (int i=0 ; i<n ; i++) { c1[i] = new Coordinate(sc.nextInt(), sc.nextInt()); }
        for (int i=0 ; i<m ; i++) { c2[i] = new Coordinate(sc.nextInt(), sc.nextInt()); }
        for (int i=0 ; i<k ; i++) { c3[i] = new Coordinate(sc.nextInt(), sc.nextInt()); }

        //알고리즘 시작
        for (int i=0 ; i<n ; i++) {
            for (int j=0 ; j<m ; j++) {
                for (int l=0 ; l<k ; l++) { //for문 사용해서 모든 경우를 확인하도록 설정
                    /*1. 같은 직선상에 있는지 확인
                        두개의 점을 잇는 직선의 방정식을 구하고 그 식에 나머지 한 점을 대입하는 방법으로 진행
                        구현해야 하는 것
                        1)c1과 c2의 좌표를 가지고 기울기->(b-d)/(a-c), 절편->a((b-d)/(a-c))+b 구하기
                        2)c3의 좌표를 이용해서 y == (기울기)*x + (절편)이 성립하는지 확인
                        3)성립한다면 에너지만 구하고 패스, 성립하지 않는다면 다음 단계로 진행
                        구현할 함수
                        기울기 구하는 함수, 절편 구하는 함수 => 기울기, 절편, c3를 넣어주면 true, false 판별해주는 함수
                        !!!주의!!! -> x축이나 y축의 변화가 없는 좌표는 /0 오류가 발생 => 이거 먼저 확인해야함
                        !!!주의!!! -> 2개의 점이 한 직선에 있는 경우를 생각해야함
                    */
                    if((c1[i].getX() == c2[j].getX() && c2[j].getX() == c3[l].getX())
                            || (c1[i].getY() == c2[j].getY() && c2[j].getY() == c3[l].getY())){//축에 수직인 직선인 경우
                        energy += totalEnergy(c1[i], c2[j], c3[l]);
                    }
                    else if(c1[i].getX() == c2[j].getX() || c1[i].getY() == c2[j].getY()){// ㅡ or |
                        clockwise = isClockwise(c1[i], c2[j], c3[l], 0);
                        if(clockwise){ // 시계방향인 경우
                            double result = Math.round(totalEnergy(c1[i], c2[j], c3[l]) - 2*area(c1[i], c2[j], c3[l]));
                            energy += (int) result;
                        }
                        else{ // 반시계방향인 경우
                            double result = Math.round(totalEnergy(c1[i], c2[j], c3[l]) + 2*area(c1[i], c2[j], c3[l]));
                            energy += (int) result;
                        }
                    }
                    else{
                        sl = slope(c1[i], c2[j]);
                        in = intersect(sl, c1[i]);
                        if(sameLinear(sl, in, c3[l])){ //직선에 모든 점이 존재하는 경우
                            energy += totalEnergy(c1[i], c2[j], c3[l]);
                        }
                        /*
                        2. 직선이 아닌 삼각형을 이루는 경우
                            1) 시계방향인지 반시계인지 확인
                                - 시계라면 2abc만큼 에너지에서 제거하고
                                - 반시계라면 2abc만큼 에너지에 추가한다
                         */
                        else{//삼각형인 경우
                            //시계방향인지 확인
                            clockwise = isClockwise(c1[i], c2[j], c3[l], 1);
                            if(clockwise){ // 시계방향인 경우
                                double result = Math.round(totalEnergy(c1[i], c2[j], c3[l]) - 2*area(c1[i], c2[j], c3[l]));
                                energy += (int) result;
                            }
                            else{ // 반시계방향인 경우
                                double result = Math.round(totalEnergy(c1[i], c2[j], c3[l]) + 2*area(c1[i], c2[j], c3[l]));
                                energy += (int) result;
                            }
                        }
                    }
                }
            }
        }
        //모든 에너지 계산 완료
        int DIVISOR = (int) (Math.pow(10,9) + 7);
        System.out.println(energy % DIVISOR);
    }

    public double slope(Coordinate c1, Coordinate c2) {
        return (c1.getY() - c2.getY()) / ((c1.getX() - c2.getX())*1.0);
    }

    public double intersect(double slope, Coordinate c1) {
        return c1.getY() - c1.getX()*slope;
    }

    public boolean sameLinear(double slope, double intersect, Coordinate c){
        return c.getY() == slope * c.getX() + intersect;
    }

    public int totalEnergy(Coordinate c1, Coordinate c2, Coordinate c3){
        int[] len = lengths(c1, c2, c3);
        return len[0]+len[1]+len[2];
    }

    public int[] lengths(Coordinate c1, Coordinate c2, Coordinate c3){
        int[] l = new int[3];
        l[0] = (int) (Math.pow(c1.getX()-c2.getX(),2) + Math.pow(c1.getY()-c2.getY(),2));
        l[1] = (int) (Math.pow(c2.getX()-c3.getX(),2) + Math.pow(c2.getY()-c3.getY(),2));
        l[2] = (int) (Math.pow(c1.getX()-c3.getX(),2) + Math.pow(c1.getY()-c3.getY(),2));
        return l;
    }

    public boolean isClockwise(Coordinate c1, Coordinate c2, Coordinate c3, int option) {
        boolean clockwise = true;
        if (option == 0){
            if(c2.getX() > c1.getX()){
                if(c2.getY() < c3.getY()){//반시계방향
                    clockwise = false;
                }
            }
            else if(c2.getX() < c1.getX()){
                if(c2.getY() > c3.getY()){ //반시계방향
                    clockwise = false;
                }
            }
            else{ //c1, c2의 x축이 동일한 경우
                if(c2.getY() < c1.getY() && c2.getX() < c3.getX()){
                    clockwise = false;
                }
                else if(c2.getY() > c1.getY() && c2.getX() > c3.getX()){
                    clockwise = false;
                }
            }
        }
        else{ // c1과 c2가 기울기가 0<a<무한대 인 기울기를 가지는 경우
            double slope = slope(c1, c2);
            double intersect = intersect(slope, c1);
            if(c2.getX() > c1.getX()){
                if(slope*c3.getX()+intersect < c3.getY()){
                    clockwise = false;
                }
            }
            else if(c2.getX() < c1.getX()){
                if(slope*c3.getX()+intersect > c3.getY()){
                    clockwise = false;
                }
            }
        }
        return clockwise;
    }

    public double area(Coordinate c1, Coordinate c2, Coordinate c3){
        int[] len = lengths(c1, c2, c3);
        double a = Math.sqrt(len[0]);
        double b = Math.sqrt(len[1]);
        double c = Math.sqrt(len[2]);
        double s = (a+b+c)/2;
        double result = Math.sqrt(s*(s-a)*(s-b)*(s-c));
        return result;
    }

}
