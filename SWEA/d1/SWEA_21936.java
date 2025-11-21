package d1;

import java.util.Scanner;

/*
 * 길이가 N인 문자열 안에 길이가 M인 회문이 있으면 그 회문을 출력하고, 회문이 없으면 NONE을 출력하는 프로그램을 만드시오.
(2<=M<=N<100)

[입력]
첫 줄에 테스트케이스 개수 T, 다음 줄부터 각 케이스 별로 첫 줄에 N과 M, 다음 줄에 길이가 N인 문자열이 주어진다.

[출력]
#과 테스트케이스번호, 빈칸에 이어 회문 또는 NONE을 출력한다.
*/

public class SWEA_21936 {
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		int tc = sc.nextInt();
		for (int i = 1; i <= tc; i++) {
			String output = solve();
			sb.append("#").append(tc).append(" ").append(output).append("\n");
		}
		System.out.println(sb);
	}

	public static String solve() {
		int inputLen = sc.nextInt();
		// 회문 길이
		int distance = sc.nextInt();
		String inputS = sc.next();
		for (int i = 0; i <= inputLen - distance; i++) {
			boolean out = true;
			int lastIdx = i + distance - 1;
			if (inputS.charAt(i) == inputS.charAt(i + distance - 1)) {
				// 이제 검사해볼만 함 그 안에 것들을 비교해보면 됨
				for (int j = i + 1; j < lastIdx; j++) {
					int mirrorIdx = lastIdx - (j - i);
					if (inputS.charAt(j) != inputS.charAt(mirrorIdx)) {
						out = false;
						break;
					}
					;
				}
				if (out)
					return inputS.substring(i, i + distance);
			}

		}
		return "NONE";
	}

}
