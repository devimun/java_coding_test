package silver;
/*
[문제]
Mirko works in a sugar factory as a delivery boy.
He has just received an order:
he has to deliver exactly N kilograms of sugar to a candy store on the Adriatic coast.
Mirko can use two types of packages, the ones that contain 3 kilograms, and the ones with 5 kilograms of sugar.
Mirko would like to take as few packages as possible.
For example, if he has to deliver 18 kilograms of sugar, he could use six 3-kilogram packages. 
But, it would be better to use three 5-kilogram packages, and one 3-kilogram package, resulting in the total of four packages.
Help Mirko by finding the minimum number of packages required to transport exactly N kilograms of sugar.

[입력]
The first and only line of input contains one integer N (3 ≤ N ≤ 5000).

[출력]
The first and only line of output should contain the minimum number of packages Mirko has to use.
If it is impossible to deliver exactly N kilograms, output -1. 

[해석]
미르코 슈가 배달함
정확히 N 무게를 배달해야함
2개의 패키지 있음 3,5키로짜리
최대한 패키지를 적게 쓰고 싶음
예를 들어 18키로 주문 왔을 때 3*6으로 나감
하지만 5*3 + 3*1 로 나가는 게 좋았음
미르코를 도와주세요

1. 첫 줄은 무게 총량
2. 출력은 미르코가 최대한 적게 패키지 쓰게 하는 것 정확히 5*i + 3*j = N 이 아니라면 -1 리턴하세요

[풀이]
만약에 19면 5*2 3*3 이 돼야함 그니까 무작정 n/5를 하면 안됨 하나씩 늘려야 되나. 
아니다 그래도 일단 n/5하고나서 남은 거로 3이 안되면 5를 하나씩 줄여가는 식으로 하면 될듯?
19 -> 5*3 -> 4%3 != 0 -> 5*2 -> 9%3 == 0 => 2+ (9/3) 이런 식으로 하면 될듯
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class s4_2839 {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        solve();
    }

    public static void solve() throws Exception {
        int output = -1;
        int N = nextInt();
        int mok5 = N / 5;
        for (int i = mok5; i >= 0; i--) {
            int remainder = N - (5 * i);
            if (remainder % 3 == 0) {
                int mok3 = remainder / 3;
                output = i + mok3;
                break;
            }
        }
        System.out.println(output);
    }

    public static int nextInt() throws Exception {
        return Integer.parseInt(next());
    }

    public static String next() throws Exception {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        ;
        return st.nextToken();
    }
}
