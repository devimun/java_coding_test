package d2;

import java.util.Scanner;

/*
 * ※ SW Expert 아카데미의 문제를 무단 복제하는 것을 금지합니다.
N x N 배열 안의 숫자는 해당 영역에 존재하는 파리의 개수를 의미한다.
아래는 N=5 의 예이다.

M x M 크기의 파리채를 한 번 내리쳐 최대한 많은 파리를 죽이고자 한다.
죽은 파리의 개수를 구하라!
예를 들어 M=2 일 경우 위 예제의 정답은 49마리가 된다.

[제약 사항]
1. N 은 5 이상 15 이하이다.
2. M은 2 이상 N 이하이다.
3. 각 영역의 파리 갯수는 30 이하 이다.

[입력]
가장 첫 줄에는 테스트 케이스의 개수 T가 주어지고, 그 아래로 각 테스트 케이스가 주어진다.
각 테스트 케이스의 첫 번째 줄에 N 과 M 이 주어지고,
다음 N 줄에 걸쳐 N x N 배열이 주어진다.


[출력]
출력의 각 줄은 '#t'로 시작하고, 공백을 한 칸 둔 다음 정답을 출력한다.
(t는 테스트 케이스의 번호를 의미하며 1부터 시작한다.)
 
[풀이]
주어진 N*N 배열에서 순회를 돌아서 각 지점에서 m*m 크기의 파리 죽인 값을 구한다.
거기서 최댓값을 출력한다.
단, 순회를 돌 때 범위를 넘어가지 않게 잘 체크한다.
 */

public class SWEA_2001 {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        // test_case 받기
        int tc = sc.nextInt();
        // 테스트케이스 만큼 반복문 돌리기
        for (int i = 1; i <= tc; i++) {
            sb.append("#").append(i).append(" ").append(getMaxCount()).append("\n");
        }
        System.out.println(sb);
    }

    public static int getMaxCount() {
        // 맵 크기
        int mapSize = sc.nextInt();
        // 파리채 크기
        int weaponSize = sc.nextInt();
        // 맵 정의
        int[][] map = new int[mapSize][mapSize];
        // 맵 채우기
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        // 최댓값 지정하기
        int max = 0;
        // 시작 지점 정하기
        for (int i = 0; i <= mapSize - weaponSize; i++) {
            for (int j = 0; j <= mapSize - weaponSize; j++) {
                int sum = 0;
                for (int r = 0; r < weaponSize; r++) {
                    for (int c = 0; c < weaponSize; c++) {
                        sum += map[i + r][j + c];
                    }
                }
                if (max < sum) {
                    max = sum;
                }
            }
        }

        return max;
    }
}
