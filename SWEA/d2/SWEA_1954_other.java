/*
※ SW Expert 아카데미의 문제를 무단 복제하는 것을 금지합니다.
달팽이는 1부터 N*N까지의 숫자가 시계방향으로 이루어져 있다.
다음과 같이 정수 N을 입력 받아 N크기의 달팽이를 출력하시오.

[예제]
N이 3일 경우, 3*3 
1 2 3
8 9 4
7 6 5
N이 4일 경우, 4*4

[제약사항]
달팽이의 크기 N은 1 이상 10 이하의 정수이다. (1 ≤ N ≤ 10)

[입력]
가장 첫 줄에는 테스트 케이스의 개수 T가 주어지고, 그 아래로 각 테스트 케이스가 주어진다.
각 테스트 케이스에는 N이 주어진다.

[출력]
각 줄은 '#t'로 시작하고, 다음 줄부터 빈칸을 사이에 두고 달팽이 숫자를 출력한다.
(t는 테스트 케이스의 번호를 의미하며 1부터 시작한다.)


[문제 풀이]
같은 문제를 푸는 다른 방식.
여전히 경계를 판단하지만, 이전처럼 조건을 나눠서 판단하는 것이 아니라 하나의 조건으로 진행한다.
x,y증가량을 선언해두고 경계값에 맞닿으면 dir값에 따라 변경되도록 한다.
 */
package d2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// public calss Solution {

public class SWEA_1954_other {
    // 버퍼리더랑 스트링빌더 스트링토크나이저가 필요하다
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        // 실행 횟수;
        int tc = nextInt();
        for (int i = 0; i < tc; i++) {
            // 실행 로직
            // 박스의 사이즈가 몇인지
            printBox(nextInt(), i);
        }
        System.out.println(sb);
    }

    /**
     * 
     * @param n : 사이즈
     */
    public static void printBox(int n, int tc) {
        // 일단 달팽이 집 만들기
        int[][] box = new int[n][n];
        // x,y의 초기값
        int x = 0;
        int y = 0;
        // dir 0,1,2,3 오른,아래,왼,위 시계방향이므로 초기값 0
        int dir = 0;
        // dir에 따른 x,y 증가량 배열 선언
        int[] dx = { 0, 1, 0, -1 };
        int[] dy = { 1, 0, -1, 0 };

        // n*n 만큼 반복문 실행
        for (int i = 1; i <= n * n; i++) {
            box[x][y] = i;
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            // 경계에 벗어나는 경우 dir을 바꾸는 로직
            if (nx < 0 || ny < 0 || nx >= n || ny >= n || box[nx][ny] != 0) {
                // 순서는 '오->아->왼->위' 기 때문에
                dir = (dir + 1) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }
            x = nx;
            y = ny;
        }
        // 반복문 끝나면 출력하기
        sb.append("#").append(tc + 1).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(box[i][j]);
            }
            sb.append("\n");
        }
    }

    private static String next() throws Exception {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();

    }

    private static int nextInt() throws Exception {
        return Integer.parseInt(next());
    };
}
