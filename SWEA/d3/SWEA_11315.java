package d3;

import java.util.Scanner;

/*
 * N X N 크기의 판이 있다. 판의 각 칸에는 돌이 있거나 없을 수 있다. 
 * 돌이 가로, 세로, 대각선 중 하나의 방향으로 다섯 개 이상 연속한 부분이 있는지 없는지 판정하는 프로그램을 작성하라.
 * [입력]
 * 첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
 * 각 테스트 케이스의 첫 번째 줄에는 하나의 정수 N(5 ≤ N ≤ 20)이 주어진다.
 * 다음 N개의 줄의 각 줄에는 길이 N인 문자열이 주어진다.
 * 각 문자는 ‘o’또는 ‘.’으로, ‘o’는 돌이 있는 칸을 의미하고, ‘.’는 돌이 없는 칸을 의미한다.
 * [출력]
 * 각 테스트 케이스 마다 돌이 다섯 개 이상 연속한 부분이 있으면 “YES”를,
 * 아니면 “NO”를 출력한다.
 * [풀이]
 * 일단 2차원 배열을 만들고 , o가 나올 때 까지 순회하다가, 
 * 아니다 이거 .을 넣으면 그냥 말이 안됨 반복 횟수가 너무 늘어남
 * 그냥 o있는 애들만 모아놓으면 될듯
 * o가 나오면 o들 중에서 x+-1, y+-1 범위에 o가 있는지 확인
 * 범위에 o가 있으면 방향을 지정해서, +-1에도 돌이 있는지 확인.
 * 계속 더해가면서 길이 5가 되면 반복문 중단, 결과 발표
 */

public class SWEA_11315 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			char[][] map = new char[N][N];

			// 1. 맵 입력 받기 (String을 char 배열로 변환)
			for (int i = 0; i < N; i++) {
				String line = sc.next();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);
				}
			}

			// 2. 오목 판정 로직 호출
			if (solve(N, map)) {
				System.out.println("#" + tc + " YES");
			} else {
				System.out.println("#" + tc + " NO");
			}
		}
		sc.close();
	}

	static boolean solve(int n, char[][] map) {
		// 배열을 왼쪽 위 부터 오른쪽으로 갔기 때문에 다음 공의 위치는 무조건 우,하,좌하,우하이다.
		// row,col 증감량
		int[] dr = { 0, 1, 1, 1 };
		int[] dc = { 1, 0, -1, 1 };
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == '.')
					continue;
				// 만약에 o가 발견됐다면, 4가지 방향으로 다 찾아보기
				for (int k = 0; k < 4; k++) {
					// 현재 칸으로부터 4칸 가야 됨.
					int count = 1;
					for (int h = 1; h < 5; h++) {
						// 다음 row
						// 다음 col
						int nr = i + dr[k] * h;
						int nc = j + dc[k] * h;
						// 현재 좌표가 경계를 벗어나거나, 'o' 가 아니라면
						if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] != 'o')
							break;
						count++;
					}
					if (count == 5) {
						return true;
					}
				}
			}
		}
		return false;
	}
}