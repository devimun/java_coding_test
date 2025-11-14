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
그냥 갈 때 마다 자기 앞이 막혀있으면 다른 방향으로 가게 하는 방식
가다가 막히면 이제 오->아->왼->위->오 순으로 순회하게 하면 될듯
일단 2차원 배열 값을 int[n][n]으로 만들어두고 각 경계들을 정해두는 거임 
그 다음에 현재 방향에 맞춰서 숫자를 증가시키다가 경계에 닿으면 방향 전환. 그리고 경계값 변경
이런 식으로 하면 될듯
 */
package d2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// public calss Solution {

public class SWEA_1954_d2 {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        int tc = nextInt();
        for (int i = 0; i < tc; i++) {

            getBox(i + 1);
        }

    }

    private static void getBox(int tc) throws Exception {
        // dir: 0=오른쪽, 1=아래, 2=왼쪽, 3=위 (막히면 이 순서대로 회전)
        int dir = 0; // 처음엔 그냥 오른쪽으로 밀고 간다
        int size = nextInt();
        // 크기 지정
        int[][] box = new int[size][size];
        int x = 0, y = 0;
        int minX = 0; // 위쪽 경계 (위 한 줄 채울 때마다 안으로 한 칸)
        int minY = 0; // 왼쪽 경계
        int maxX = size - 1; // 아래쪽 경계
        int maxY = size - 1; // 오른쪽 경계
        for (int j = 0; j < size * size; j++) {
            if (dir == 0) {
                // 오른쪽으로 갈 수 있는 동안은 그냥 전진
                if (y <= maxY && box[x][y] == 0) {
                    box[x][y] = j + 1;
                    if (y == maxY) {
                        dir = 1; // 벽 찍었으니 바로 아래로
                        minX += 1; // 위쪽 테두리는 끝났으니 경계 한 칸 줄이기
                        x++;
                    } else {
                        y++;
                    }
                }
            } else if (dir == 1) {
                // 아래로 쭉
                if (x <= maxX && box[x][y] == 0) {
                    box[x][y] = j + 1;
                    if (x == maxX) {
                        dir = 2; // 바닥 찍었으면 왼쪽으로 턴
                        maxY -= 1; // 오른쪽 테두리도 줄여주기
                        y--;
                    } else {
                        x++;
                    }
                }
            } else if (dir == 2) {
                // 왼쪽으로 백
                if (y >= minY && box[x][y] == 0) {
                    box[x][y] = j + 1;
                    if (y == minY) {
                        dir = 3; // 왼쪽 끝에 닿았으니 위로 올라감
                        maxX -= 1; // 아래쪽 경계 한 칸 올리기
                        x--;
                    } else {
                        y--;
                    }
                }
            } else if (dir == 3) {
                // 위로 올라가다가, 막히면 다시 오른쪽 반복
                if (x >= minX && box[x][y] == 0) {
                    box[x][y] = j + 1;
                    if (x == minX) {
                        dir = 0; // 한 층 완주, 다시 오른쪽
                        minY += 1; // 왼쪽 경계도 안쪽으로
                        y++;
                    } else {
                        x--;
                    }
                }

            }
        }

        System.out.println("#" + tc);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(box[i][j]);
                if (!(j == (size - 1))) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static String next() throws Exception {
        // st가 null이거나 더 토큰이 없으면 기다려
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    public static int nextInt() throws Exception {
        return Integer.parseInt(next());
    }
}
