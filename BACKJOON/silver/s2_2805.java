package silver;
/*
Lumberjack Mirko needs to chop down M metres of wood.
It is an easy job for him since he has a nifty new woodcutting machine that can take down forests like wildfire.
However, Mirko is only allowed to cut a single row of trees.
Mirko's machine works as follows: Mirko sets a height parameter H (in metres),
and the machine raises a giant sawblade to that height and cuts off all tree parts higher than H
(of course, trees not higher than H meters remain intact).
Mirko then takes the parts that were cut off.
For example, if the tree row contains trees with heights of 20, 15, 10,and 17 metres,
and Mirko raises his sawblade to 15 metres, the remaining tree heights after cutting will be 15, 15, 10, and 15 metres,
respectively, while Mirko will take 5 metres off the first tree and 2 metres off the fourth tree (7 metres of wood in total).
Mirko is ecologically minded, so he doesn't want to cut off more wood than necessary.
That's why he wants to set his sawblade as high as possible.
Help Mirko find the maximum integer height of the sawblade that still allows him to cut off at least M metres of wood.

[해석]
미르코는 M 미터의 나무를 자르고 싶다.
미르코는 잘 자르는 도구 있다. 하지만, 오직 한 줄로 자르는 게 허용 된다.
도구는 이렇게 작동한다. : 높이를 정하고, 그 높이보다 높은 애들만 잘라낸다.
예를 들어서 20,15,10,17로 해놓고 도구를 15로 세팅하면 15,15,10,15가 된다.
그래서 총 7미터를 잘랐다. 
미르코는 환경을 아껴서 최소한으로 자르길 원한다.
그니까 도구를 최대한 높은 높이로 설정한다.

[입력]
1. N 트리 개수
2. M 필요한 용량
3. 나무 리스트 보여줌 

[풀이]
입력에서 나무 최댓값을 찾고 그것을 기계의 커팅 높이로 설정한다.
기계 커팅을 실행하고 잘린 총량을 M과 비교한다. 양이 부족하면 1씩 줄여가면서 계속 비교한다.
값이 맞아지면 그 기계 높이를 출력하면 될듯


입력
The first line of input contains two space-separated positive integers,
N (the number of trees, 1 ≤ N ≤ 1 000 000) and M (Mirko's required wood amount, 1 ≤ M ≤ 2 000 000 000).

The second line of input contains N space-separated positive integers less than 1 000 000 000, the heights of each tree (in metres).
The sum of all heights is greater than or equal to M, thus Mirko will always be able to obtain the required amount of wood.

출력
The first and only line of output must contain the required height setting.

예제 입력 1 
4 7
20 15 10 17
예제 출력 1 
15
 */

// [오답 - 시간 초과]
// import java.util.Scanner;

// public class uk_2805 {
//     static Scanner sc;

//     public static void main(String[] args) {
//         sc = new Scanner(System.in);
//         int tn = sc.nextInt();
//         int amount = sc.nextInt();
//         int max = 0;
//         int[] trees = new int[tn];
//         for (int i = 0; i < tn; i++) {
//             trees[i] = sc.nextInt();
//             if (trees[i] > max) {
//                 max = trees[i];
//             }
//         }
//         for (int i = max; i >= 0; i--) {
//             long sum = 0;
//             for (int tree : trees) {
//                 if (tree > i) {
//                     sum += tree - i;
//                 }
//             }
//             if (sum == amount) {
//                 System.out.print(i);
//                 break;
//             }
//         }
//     }
// }

/*
 * [다음 풀이]
 * 시간 초과가 나왔다. 즉, 효율적인 방법이 아니라는 건데.
 * 극단적으로 생각해서 높이가 10억인 나무, 100만 개의 나무가 있다면
 * 10억부터 1씩 낮춰갈 때 마다, 100만개 나무를 다 돌려봐야 한다.
 * 만약에 10억번 다 돌려야 된다면, 10억 * 100만 번 횟수가 실행되니 당연히 문제다.
 * 그러면 다른 방법으로 풀어야 한다. 모든 것들을 다 계산하면 안되니까 이전 값들을 활용하게 해야 될 거 같다.
 * 뭐를 기준으로 해야 시간 복잡도를 낮출 수 있을까?
 * bfs,dp 해설을 본적이 있지만, 여전히 즉흥적으로 어떤 알고리즘으로 풀어야겠다는 생각이 안든다.
 * 근데 아무리 생각해도 1씩 줄여가는 것은 무리다.
 * 그러면 탐색 범위를 반씩 줄여가는 것은 어떨까?
 * 최소,최대의 중간값을 기준으로 잘라보고 총합이 원하는 값보다 많으면 기계의 높이는 더 높아져야 한다.
 * 즉, 기계의 최솟값이 정해진다. -> 최솟값이 중간값보다 높다는 것
 * 반대로 부족하면, 중간값보다 더 잘라야 한다는 소리다. 그렇다면 기계의 높이는 더 낮아져야 한다.
 * 즉, 기계의 최댓값이 정해진다. -> 최댓값이 중간값보다 낮다는 것
 * 그렇게되면, 범위가 반으로 줄어든다. 근데 범위를 한 번만 줄이면 되나? 최대한으로 줄이고 싶지 않나?
 * 그럼 중요한 것은 언제까지 범위를 변경할래? 라는 것이다.
 * 만약에 0~10억의 높이가 있다. 중간값인 5억을 기계의 높이로 설정하고, 잘랐을 떄 총합이 부족했다.
 * 그러면 기계의 높이를 더 낮춰야하니, 최댓값이 5억 최소가 0인 상태다. 이때 2.5억을 기준으로 또 잘라본다.
 * 그리고 부족하면, 최댓값이 2.5억 최소가 0이 된다.
 * 이런 과정을 반복하다보면 중간값이 목표값이 되는 순간이 오지 않을까?
 * 만약에 목표값이 49, 최소 최대가 각각 0,50이라면 25~50 -> 37~50 -> 43~50 -> 46~50 -> 48 ~ 50 ->
 * 49~50 이런 식이다. 그러면 중간값이 목표값이 아니라면, 최소,최댓값을 바꾸는 형식으로 하면 될 것 같다.
 */

// [결과 - 정답]
// import java.util.Scanner;

// public class uk_2805 {
//     static Scanner sc;

//     public static void main(String[] args) {
//         sc = new Scanner(System.in);
//         int tc = sc.nextInt();
//         int amount = sc.nextInt();
//         int max = 0;
//         int min = 0;
//         int[] trees = new int[tc];
//         // 최댓값 구하기
//         for (int i = 0; i < tc; i++) {
//             int tree = sc.nextInt();
//             trees[i] = tree;
//             if (tree > max) {
//                 max = tree;
//             }
//         }
//         // 최대,최소를 활용한 재귀 함수 호출
//         int goal = getValue(min, max, trees, amount);
//         System.out.println(goal);
//     }

//     public static int getValue(int min, int max, int[] trees, int goal) {
//         if (min > max) {
//             return max;
//         }
//         int mid = (min + max) / 2;
//         long sum = 0;
//         for (int tree : trees) {
//             if (tree > mid) {
//                 sum += tree - mid;
//             }
//         }
//         // 같거나 많이 자른 경우 -> 기계의 높이의 최솟값을 올린다. : 기계가 높아질 수록 적게 자르니까.
//         if (sum >= goal) {
//             return getValue(mid + 1, max, trees, goal);
//         }
//         // 너무 조금 자른 경우 -> 기계의 최댓값을 낮춘다. : 기계가 낮아질 수록 많이 자르니까.
//         else {
//             return getValue(min, mid - 1, trees, goal);
//         }
//     }
// }

/*
[GEMINI 3.0 답변]
재귀를 써도 풀리지만, 반복문을 쓰는 것이 성능에서 압도적이다.
함수를 호출할 때 마다 메모리와 CPU에게 부담을 주고 JAVA의 경우 탐색 깊이가 깊어지면 프로그램이 죽을 수도 있다.
따라서 반복문으로 풀어봐라.
그리고 Scanner쓰지마라
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class s2_2805 {
    static BufferedReader br;
    static StringTokenizer st;

    public static int nextInt() throws Exception {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return Integer.parseInt(st.nextToken());
    }

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        int tc = nextInt();
        long amount = nextInt();
        int max = 0;
        int min = 0;

        int[] trees = new int[tc];
        // 최댓값 구하기
        for (int i = 0; i < tc; i++) {
            int tree = nextInt();
            trees[i] = tree;
            if (tree > max) {
                max = tree;
            }
        }
        /*
         * 최대,최소를 활용한 재귀가 아니라 반복문으로 풀어보기
         * 최대,최소가 값이 변경되기 때문에 min,max를 활용해서 조건을 짜야할듯하다.
         * 결과적으로 더 많이 남을 때 mid + 1 하고 부족하면 mid-1을 하기 때문에 min>max이 오는 순간이 있다.
         * min>max가 되는 순간 멈추고 그때 당시 max값을 쓰면 된다.
         */
        long ans = 0;
        while (min <= max) {
            long sum = 0;
            int mid = (min + max) / 2;
            for (int tree : trees) {
                if (tree > mid) {
                    sum += (tree - mid);
                }
            }
            // 총합이 많거나 같으면 최솟값 늘려보기
            if (sum >= amount) {
                // 정답 후보로 두기
                ans = mid;
                min = mid + 1;
            }
            // 총합이 부족하면 최댓값 낮추기
            else {
                max = mid - 1;
            }

        }

        System.out.println(ans);
    }

}