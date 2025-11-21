package d3;

import java.util.Scanner;

/*SW Expert 아카데미의 문제를 무단 복제하는 것을 금지합니다.

원재가 컴퓨터를 만지다가 실수를 저지르고 말았다. 메모리가 초기화된 것이다.

다행히 원래 메모리가 무슨 값이었는지 알고 있었던 원재는 바로 원래 값으로 되돌리려고 했으나 메모리 값을 바꿀 때 또 문제가 생겼다.

메모리 bit중 하나를 골라 0인지 1인지 결정하면 해당 값이 메모리의 끝까지 덮어씌우는 것이다.

예를 들어 지금 메모리 값이 0100이고, 3번째 bit를 골라 1로 설정하면 0111이 된다.

원래 상태가 주어질 때 초기화 상태 (모든 bit가 0) 에서 원래 상태로 돌아가는데 최소 몇 번이나 고쳐야 하는지 계산해보자.

[입력]

첫 번째 줄에 테스트 케이스의 수 T가 주어진다.

각 테스트 케이스는 한 줄로 이루어져 있으며, 메모리의 원래 값이 주어진다.

메모리의 길이는 1이상 50이하이다.

[출력]

각 테스트 케이스마다 ‘#x’(x는 테스트케이스 번호를 의미하며 1부터 시작한다)를 출력하고,

초기값(모든bit가 0)에서 원래 값으로 복구하기 위한 최소 수정 횟수를 출력한다.

*/
public class SWEA_1289 {
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tc = sc.nextInt();
		for (int i = 1; i <= tc; i++) {
			int output = getCount();
			sb.append("#").append(i).append(" ").append(output).append("\n");
		}
		System.out.println(sb);
	}

	// 값이 바뀌면 뒤에 모든 값이 하나의 값으로 바뀌고 시작값이 0으로 고정돼있기 때문에, 현재 값과 입력 값이 다르다면 업데이트 시키기
	public static int getCount() {
		int count = 0;

		char current = '0';
		String line = sc.next();
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) != current) {
				count++;
				current = line.charAt(i);
			}
		}
		return count;
	}
	// 다른 방법으로도 풀 수 있을듯? 첫 번째 거를 계산해버리고 지금 나랑 내 전거랑 비교해서 바뀌면 +1하기
	// public static int getCount() {
	// int count = 0 ;
	// String line = sc.next();
	// if(line.charAt(0) == '1') {
	// count ++;
	// }
	// for(int i = 1 ; i < line.length(); i++) {
	// if(line.charAt(i) != line.charAt(i-1)) {
	// count ++;
	// }
	// }
	// return count;
	// }
}
