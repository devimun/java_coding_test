package d3;

import java.util.Scanner;

/*※ SW Expert 아카데미의 문제를 무단 복제하는 것을 금지합니다.

카드를 퍼펙트 셔플 한다는 것은, 카드 덱을 정확히 절반으로 나누고 나눈 것들에서 교대로 카드를 뽑아 새로운 덱을 만드는 것을 의미한다. 

정확한 방식은 다음 그림과 같다.


N개의 카드가 있는 덱이 주어질 때 이를 퍼펙트 셔플하면 어떤 순서가 되는지 출력하는 프로그램을 작성하라.

만약 N이 홀수이면, 교대로 놓을 때 먼저 놓는 쪽에 한 장이 더 들어가게 하면 된다.


[입력]

첫 번째 줄에 테스트 케이스의 수 T가 주어진다.

각 테스트 케이스의 첫 번째 줄에는 자연수 N(1 ≤ N ≤ 1,000)이 주어진다.

두 번째 줄에는 덱에 카드가 놓인 순서대로 N개의 카드 이름이 공백으로 구분되어 주어진다.

카드의 이름은 알파벳 대문자와 ‘-’만으로 이루어져 있으며, 길이는 80이하이다.

[출력]

각 테스트 케이스마다 주어진 덱을 퍼펙트 셔플한 결과를 한 줄에 카드 이름을 공백으로 구분하여 출력한다.
[풀이]
일단 한 번에 다 받는 게 아니라, 배열을 2개 만들어서 돌아가면서 받으면 될듯? 몇개가 들어올지 알잖아.
그러면 String[] firstDeck 이랑 String[] secondDeck을 만들어버려
그리고 돌아가면서 인풋받아.
*/

public class SWEA_3499 {
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		int tc = sc.nextInt();
		for (int i = 1; i <= tc; i++) {
			output(i);
		}
		System.out.println(sb);
	}

	public static void output(int tc) {
		sb.append("#").append(tc);
		int N = sc.nextInt();
		int firLen = 0;
		int secLen = 0;
		if (N % 2 == 0) {
			firLen = N / 2;
			secLen = N / 2;
		} else {
			firLen = N / 2 + 1;
			secLen = N / 2;
		}
		String[] fir = new String[firLen];
		String[] sec = new String[secLen];
		for (int i = 0; i < firLen; i++) {
			fir[i] = sc.next();
		}
		for (int i = 0; i < secLen; i++) {
			sec[i] = sc.next();
		}

		boolean stop = false;
		int currentF = 0;
		int currentS = 0;
		while (!stop) {
			if (currentF < firLen) {
				sb.append(" ").append(fir[currentF]);
				currentF++;
			}
			if (currentS < secLen) {
				sb.append(" ").append(sec[currentS]);
				currentS++;
			}

			if (currentF == firLen) {
				stop = true;
			}
		}
		sb.append("\n");
	}
}
