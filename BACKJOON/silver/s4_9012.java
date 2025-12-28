package silver;
/*
[문제]
Parenthesis String (PS) consists of two parenthesis symbols ‘(’ and ‘)’ only.
In parenthesis strings, some strings are called a valid PS (shortly, VPS).
Let us give the formal definition of VPS. A single “( )” is a member of VPS, called the base VPS. Let x and y be a member of VPS.
Then “(x)”, a VPS which encloses a VPS x with a single pair of parenthesis, is also a member of VPS. And xy, the concatenation of two VPS x and y, is a member of VPS.
For example, “(())()” and ((()))” are all VPS, but “(()(”, “(())()))” and “(()” are not VPS.
You are given a set of PS. You should decide if the input string is VPS or not. 

[해석]
PS라는 게 있는데 ( , ) 만을 갖고 있다는 거 같다.
근데 PS안에는 VPS라는 게 들어있다.
VPS는 하나의 () 이거다.
결과적으로 ()안에 ()이 있는 건 몇 개가 있든 상관 없는듯하다. 어쨌든 열리면 닫혀야 한다.
어쨌든 이게 VPS인지 아닌지 맞춰봐라 

[입력]
Your program is to read from standard input.
The input consists of T test cases.
The number of test cases T is given in the first line of the input.
Then PS’s are given in the following T lines one by one.
The length of each PS is between 2 and 50, inclusively.

[출력]
Your program is to write to standard output.
Print the result in each line. If the input string is a VPS, then print “YES”. Otherwise print “NO”. 

[풀이]
이건 그냥 스택 쓰면 될듯?
'('이 나오면 스택에 넣고 ')'이 나오면 스택에서 꺼낸다.
그렇게 해서 길이만큼 다 순회했을 때 스택이 비어있으면 통과 아니면 탈락하면 될듯하다.

[예제 입력 1]
6
(())())
(((()())()
(()())((()))
((()()(()))(((())))()
()()()()(()()())()
(()((())()(

[예제 출력 1] 
NO
NO
YES
NO
YES
NO
*/

import java.util.Scanner;
import java.util.Stack;

public class s4_9012 {
    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 0; i < tc; i++) {
            solve();
        }
    }

    public static void solve() {
        Stack<Character> st = new Stack<>();
        String line = sc.next();
        boolean output = false;
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (i == 0 && ch == ')') {
                System.out.println("NO");
                return;
            }
            if (i == line.length() - 1 && st.isEmpty()) {
                System.out.println("NO");
                return;
            }
            if (ch == '(') {
                st.add(ch);
            } else {
                if (!st.isEmpty()) {
                    char prev = st.peek();
                    if (prev == '(') {
                        st.pop();
                    }
                } else {
                    System.out.println("NO");
                    return;
                }

            }
        }
        if (st.isEmpty()) {
            output = true;
        }
        String result = output ? "YES" : "NO";
        System.out.println(result);
    }

}