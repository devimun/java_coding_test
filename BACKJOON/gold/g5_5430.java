package gold;

/**
 * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 * 
 * problem 5430_Integer Lists
 * date 2025-11-28
 * 
 * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 * 
 * [문제]
 * The programming language Better And Portable Code (BAPC) is a language
 * for working with lists of integers.
 * The language has two built-in functions: ‘R’ (reverse) and ‘D’ (drop).
 * The function ‘R’ reverses its input list, and ’D’ drops the first
 * element of its input and returns the rest, or gives an error in case
 * its input is an empty list.
 * To get more advanced behavior, functions can be composed: “AB” is the
 * function that first applies ‘A’ to its input and then ‘B’ to the
 * resulting list.
 * For example, “RDD” is a function that reverses a list and then drops
 * the first two elements.
 * Unfortunately, our BAPC interpreter has bit rotted, so we ask you to
 * write a new one.
 * Given a BAPC program and its input, return its output or “error” in
 * case ‘D’ is applied to an empty list.
 * Lists are represented as the character ‘[’ followed by a
 * comma-separated list of integers followed by the character ‘]’.
 * Notice that the input and output lists can be quite long.
 * 
 * [해석]
 * 숫자 리스트들을 위한 언어다, 2개의 함수 있다 R : 뒤집어버림 ,D : 첫 번째 요소 버리고, 나머지 리턴함 비었으면
 * 에러리턴. 더 좋아지기 위해서 함수가 합쳐질 수 있다. RDD -> R->D->D
 * 
 * [입력]
 * On the first line one positive number: the number of test cases, at
 * most 100.
 * After that per test case:
 * 
 * - one line with a string p (1 ≤ length(p) ≤ 100 000): a BAPC program,
 * consisting of the
 * - characters ‘R’ and ‘D’.
 * - one line with an integer n (0 ≤ n ≤ 100 000): the number of
 * elements in the input.
 * - one line with a list of n integers in the form [x1, ..., xn] (1 ≤ xi
 * ≤ 100): the input list
 * 
 * [출력]
 * Per test case:
 * 
 * - one line with the resulting integer list or “error” in case of an
 * error.
 * 
 * [제한]
 * 
 * ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
 * [예제 입력 1]
 * 4
 * RDD
 * 4
 * [1,2,3,4]
 * DD
 * 1
 * [42]
 * RRD
 * 6
 * [1,1,2,3,5,8]
 * D
 * 0
 * []
 * 
 * [예제 출력 1]
 * [2,1]
 * error
 * [1,2,3,5,8]
 * error
 * ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
 * [풀이1]
 * - 접근 방식 : 그냥 R,D함수 만들어서 리스트 관리하면 될 것 같다.
 * R -> 뒤집기, D는 첫 번재 버리고 리턴하기
 * 근데 R이 짝수로 나오면 안뒤집어도 되고 홀수로 나오면 뒤집어야 됨
 * R이나 D가 연속으로 나오는 횟수에 따라서 직접 다 돌리지 않고 해도 될 거 같음
 * 입력 :
 * 1. Test case
 * 2. 함수 순서
 * 3. 요소 개수
 * 4. 배열 정보
 * - 결과 : X - 시간 초과
 * 
 * 
 */

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.StringTokenizer;

// public class Main {
// static BufferedReader br;
// static StringTokenizer st;

// public static void main(String[] args) throws Exception {
// br = new BufferedReader(new InputStreamReader(System.in));
// int tc = nextInt();
// for (int i = 0; i < tc; i++) {
// solve();
// }
// }

// public static void solve() throws Exception {
// String func = next();
// int size = nextInt();
// int[] list = new int[size];
// String slist = next();
// slist = slist.replace("[", "");
// slist = slist.replace("]", "");
// String[] nums = slist.split(",");
// for (int i = 0; i < size; i++) {
// list[i] = Integer.parseInt(nums[i]);
// }
// try {
// boolean stop = false;
// int stringIdx = 0;
// // 함수 순서대로 진행하기
// // 근데 만약에 R이나 D가 연속으로 나오면 굳이 여러번 할 필요 없긴 해.
// // 그니까 RRRRDDDD 이렇게 나오면 그냥 D에 4번째까지 지워달라는 요청 1번만 하면 됨
// while (!stop) {
// if (func.charAt(stringIdx) == 'R') {
// int rConsequence = 1;
// for (int i = stringIdx + 1; i < func.length();) {
// if (func.charAt(i) == 'R') {
// rConsequence++;
// stringIdx++;
// }
// break;
// }
// if (rConsequence % 2 != 0) {
// list = R(list);
// }
// } else {
// int dConsequence = 1;
// for (int i = stringIdx + 1; i < func.length();) {
// if (func.charAt(i) == 'D') {
// dConsequence++;
// stringIdx++;
// }
// break;
// }
// list = D(list, dConsequence);
// }
// if (stringIdx == func.length() - 1) {
// stop = true;
// }
// }
// } catch (Exception e) {
// System.out.print("error");
// return;
// }
// System.out.print(makeListToStirng(list));
// }

// public static String makeListToStirng(int[] list) {
// String output = "";
// for (int i = 0; i < list.length; i++) {
// if (i == 0) {
// output += "[";
// output += String.valueOf(list[i]) + ", ";
// } else if (i == list.length - 1) {
// output += String.valueOf(list[i]);
// output += "]";
// } else {
// output += String.valueOf(list[i]) + ", ";
// }
// }
// return output;
// }

// public static int[] R(int[] currents) {
// int idx = 0;
// int[] newlist = new int[currents.length];
// for (int i = currents.length - 1; i >= 0; i--) {
// newlist[idx] = currents[i];
// idx++;
// }
// return newlist;
// }

// public static int[] D(int[] currents, int howMany) throws Exception {
// int[] newlist = new int[currents.length - 1];
// int idx = 0;
// try {
// for (int i = howMany; i < currents.length; i++) {
// newlist[idx] = currents[i];
// idx++;
// }
// } catch (Exception e) {
// }
// return newlist;
// }

// public static int nextInt() throws Exception {
// return Integer.parseInt(next());
// }

// public static String next() throws Exception {
// while (st == null || !st.hasMoreTokens()) {
// st = new StringTokenizer(br.readLine());
// }
// return st.nextToken();
// }
// }

/*
 * [풀이 2]
 * - 접근 방식 : 이전 풀이에서 시간 초과가 나는 이유는 이것들 같다.
 * 1. 애초에 실제로 뒤집는 방식이 좋지 않은 것 같다.
 * 그렇다면 현재 상태 (뒤집,안뒤집)을 추적해서 뒤집은 상태면 뒤에서 빼고, 안뒤집었으면 앞에서 빼도록 해봐야겠다.
 * 2. string += 하는 것도 번거롭고 문제 의도에 맞지 않은 거 같다.StringBuilder를 쓰면 좀 나을까싶다.
 * 앞,뒤에서 편하게 뺄 수 있는 자료구조는 덱이 있다.
 * - 결과 : O
 * 
 * [소감]
 * 자료 구조를 잘 인식하고 있으면 좋을 것 같다. gemini3가 말하길, Stack,Queue 모두 Deque로 쓰면 된다고 한다.
 * stack은 LIFO, queue는 FIFO지만 덱은 앞 뒤로 다 뽑을 수 있기 때문에 아주 편하게 쓸 수 있다.
 * pollLast,pollFirst,offerFirst,offerLast 메서드로 앞,뒤 맘 편하게 뽑아쓰면 된다.
 * 단, 항상 비어있지 않은지 잘 확인해야 한다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.Deque;

public class g5_5430 {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        int tc = nextInt();
        for (int i = 0; i < tc; i++) {
            solve();
        }
    }

    public static void solve() throws Exception {
        String funSequence = next();
        int N = nextInt();
        Deque<Integer> list = new ArrayDeque<>();
        StringTokenizer sList = new StringTokenizer(next(), "[],");

        for (int i = 0; i < N; i++) {
            list.offer(Integer.parseInt(sList.nextToken()));
        }
        boolean error = false;
        boolean reverse = false;
        for (int i = 0; i < funSequence.length(); i++) {
            if (funSequence.charAt(i) == 'R') {
                reverse = !reverse;
            } else {
                if (list.isEmpty()) {
                    error = true;
                    break;
                }
                // 뒤집어져있으면 뒤에서 빼기
                if (reverse) {
                    list.pollLast();
                } else {
                    list.pollFirst();
                }
            }
        }
        // 남은 리스트 갖고 아웃풋 만들기
        if (error) {
            System.out.println("error");
        } else {
            System.out.println(makeOutput(list, reverse));
        }

    }

    public static String makeOutput(Deque<Integer> list, boolean reverse) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (!list.isEmpty()) {
            if (reverse) {
                sb.append(list.pollLast());
                while (!list.isEmpty()) {
                    sb.append(",").append(list.pollLast());
                }

            } else {
                sb.append(list.poll());
                while (!list.isEmpty()) {
                    sb.append(",").append(list.poll());
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static int nextInt() throws Exception {
        return Integer.parseInt(next());
    }

    public static String next() throws Exception {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
}