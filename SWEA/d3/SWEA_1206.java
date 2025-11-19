/*
 * ※ SW Expert 아카데미의 문제를 무단 복제하는 것을 금지합니다.
 * 강변에 빌딩들이 옆으로 빽빽하게 밀집한 지역이 있다. *
 * 이곳에서는 빌딩들이 너무 좌우로 밀집하여, 강에 대한 조망은 모든 세대에서 좋지만 왼쪽 또는 오른쪽 창문을 열었을 때 바로 앞에 옆 건물이
 * 보이는 경우가 허다하였다.
 * 
 * 그래서 이 지역에서는 왼쪽과 오른쪽으로 창문을 열었을 때, 양쪽 모두 거리 2 이상의 공간이 확보될 때 조망권이 확보된다고 말한다.
 * 빌딩들에 대한 정보가 주어질 때, 조망권이 확보된 세대의 수를 반환하는 프로그램을 작성하시오.
 * 아래와 같이 강변에 8채의 빌딩이 있을 때, 연두색으로 색칠된 여섯 세대에서는 좌우로 2칸 이상의 공백이 존재하므로 조망권이 확보된다.
 * 따라서 답은 6이 된다.
 * A와 B로 표시된 세대의 경우는 왼쪽 조망은 2칸 이상 확보가 되었지만 오른쪽 조망은 한 칸 밖에 확보가 되지 않으므로 조망권을 확보하지
 * 못하였다.
 * C의 경우는 반대로 오른쪽 조망은 2칸이 확보가 되었지만 왼쪽 조망이 한 칸 밖에 확보되지 않았다.
 * 
 * [제약 사항]
 * 가로 길이는 항상 1000이하로 주어진다.
 * 맨 왼쪽 두 칸과 맨 오른쪽 두 칸에는 건물이 지어지지 않는다. (예시에서 빨간색 땅 부분)
 * 각 빌딩의 높이는 최대 255이다.
 * [입력]
 * 총 10개의 테스트케이스가 주어진다.
 * 각 테스트케이스의 첫 번째 줄에는 건물의 개수 N이 주어진다. (4 ≤ N ≤ 1000)
 * 그 다음 줄에는 N개의 건물의 높이가 주어진다. (0 ≤ 각 건물의 높이 ≤ 255)
 * 맨 왼쪽 두 칸과 맨 오른쪽 두 칸에 있는 건물은 항상 높이가 0이다. (예시에서 빨간색 땅 부분)
 * [출력]
 * #부호와 함께 테스트케이스의 번호를 출력하고, 공백 문자 후 조망권이 확보된 세대의 수를 출력한다.
 * 
 * [문제 풀이]
 * 그냥 반복문으로 풀면 될 거 같은데
 * {0 0 254 185 76 227 84 175 0 0} 예시대로 이렇게 들어왔으면
 * 반복문 돌 때 현재 층 수 기준 -2 ~ 2 인덱스랑 비교하면서 2층이 확보되는 최저층을 바꾸는 거임
 * 그렇게 구한 다음에 최고층-최저층+1 값을 구하면 해당 아파트에 조경이 확보된 세대 수 아닌가.
 * 그렇게 해봐야겠다.
 */
package d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class SWEA_1206 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 10; i++) {
            getCount(i);
        }
        System.out.println(sb);

    }

    static void getCount(int tc) throws Exception {
        // 건물 개수
        int num = nextInt();
        int sum = 0;

        // 배열에 넣기
        int[] apart = new int[num];
        for (int i = 0; i < num; i++) {
            apart[i] = nextInt();
        }
        // 0~9까지 중에 어차피 양쪽 2개는 0이니까 인덱스 2부터 num-2까지 풀면 될듯?
        for (int i = 0; i < num; i++) {
            // 현재 층 수와 현재 층 수를 제외한 나머지 층 중 가장 높은 층
            if (i < 2 || i > num - 3) {
                continue;
            }
            boolean sex = true;
            int maxStory = 0;
            int currentStory = apart[i];
            for (int j = i - 2; j <= i + 2; j++) {
                // 현재 층수와 -2~+2까지 층수랑 층수 비교.
                // 단 j == i 인 경우 넘어가야 됨
                if (j == i) {
                    continue;
                } else {
                    // 범위 내에 currentStroy보다 큰 층수가 있다면 break
                    // 없다면 낮은 것들 중 가장 큰 층 구하기
                    if (maxStory < apart[j]) {
                        maxStory = apart[j];
                    }
                    if (currentStory <= maxStory) {
                        sex = false;
                        break;
                    }
                }
            }
            if (sex) {
                sum += currentStory - maxStory;
            }
        }
        sb.append("#").append(tc).append(" ").append(sum).append("\n");
    }

    static String next() throws Exception {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();

    }

    static int nextInt() throws Exception {
        return Integer.parseInt(next());
    }
}
