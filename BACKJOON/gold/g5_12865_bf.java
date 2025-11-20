package gold;

import java.util.ArrayList;
import java.util.Scanner;

/*
문제
이 문제는 아주 평범한 배낭에 관한 문제이다.
한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고 한다.
세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.
준서가 여행에 필요하다고 생각하는 N개의 물건이 있다. 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다. 아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다. 준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.

입력
첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다.
두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.
입력으로 주어지는 모든 수는 정수이다.

풀이

필요한 물건 : N개
물건 : 무게 W ,가치 V
물건 가치만큼 준서는 즐길 수 있음
무게는 최대 K

무게가 K 보다 작으면서 가치가 최댓값을 갖는 조합을 알려준다.
 
 입력값 
 1. 물품의 수(N)
 2. 버틸 수 있는 무게(K)
 3. 물품 무게(W)
 4. 물품 가치(V)
 
주어진 조합을 다 해보는 수 밖에 없나?
일단 무게를 합친 값이 K 보다 작아야 됨 그러면서 가치가 최댓값을 가져야함
일단 그러면 무게를 합쳐서 K 보다 작은 애들을 구해야 되나
예를 들어서 무개가 4개야
그러면 가능한 조합

일단 1개를 골라, 그러면 1개 기준 남은 무게 값이 나옴.
남은 물품들 중에 넣을 수 있는 물품이 있는지 확인
있다면, 조합해서 합을 구해. 멕스값을 변경해
 */

class Item {
	int w;
	int v;

	Item(int w, int v) {
		this.w = w;
		this.v = v;
	}
}

public class g5_12865_bf {
	public static Scanner sc = new Scanner(System.in);
	static int max = 0;

	public static void changeMax(int num) {
		if (num > max) {
			max = num;
		}
	}

	public static void findNext(ArrayList<Item> items, int currentV, int remainW) {
		for (Item item : items) {
			if (remainW - item.w >= 0) {
				ArrayList<Item> clone = (ArrayList<Item>) items.clone();
				clone.remove(item);
				findNext(clone, currentV + item.v, remainW - item.w);
			}
		}

		changeMax(currentV);
	}

	public static void main(String[] args) {
		// 물품 갯수
		int n = sc.nextInt();
		// 물품 최대 무게
		int k = sc.nextInt();
		ArrayList<Item> items = new ArrayList<>();
		// 물품 만들기
		for (int i = 0; i < n; i++) {
			// 물품 무게
			int w = sc.nextInt();
			// 물품 가치
			int v = sc.nextInt();
			items.add(new Item(w, v));
		}
		// 조회 시작
		findNext(items, 0, k);
		System.out.println(max);
	}
}