package silver;
/*
Farmer John has been informed of the location of a fugitive cow and wants to catch her immediately.
He starts at a point N (0 <= N <= 100,000) on a number line and the cow is at a point K (0 <= K <= 100,000) on the same number line.
Farmer John has two modes of transportation: walking and teleporting.
Walking: FJ can move from any point X to the points X-1 or X+1 in a single minute
Teleporting: FJ can move from any point X to the point 2*X in a single minute.
If the cow, unaware of its pursuit, does not move at all, how long does it take for Farmer John to retrieve it?

입력
* Line 1: Two space-separated integers: N and K

출력
* Line 1: The least amount of time, in minutes, it takes for Farmer John to catch the fugitive cow.

예제 입력 1 
5 17
예제 출력 1 
4
힌트
The fastest way for Farmer John to reach the fugitive cow is to move along the following path: 5-10-9-18-17, which takes 4 minutes.

[해석]
농부 존이 소를 최대한 빨리 잡고싶다?
그는 Point N에서 시작한다. 소는 동일한 라인의 K에서 시작한다.
존은 걷기와 텔레포트가 있다.
걷기 : x축으로 1칸 이동 가능하다.
텔레포트 : 2*x 포인트로 이동 가능하다. 예를 들어서 5에서 시작하면 10으로 간다는 얘기인듯
소가 가만히 있을 때, 몇 분만에 잡을 수 있는가?
1번 움직일 때 마다 1분

[풀이]
풀이 방법은 BFS와 QUEUE를 사용해서 풀어야 했던 것이다.
나는 그리디 방식으로 현재 상황에서 '최선'인 대안을 도출하고자 시도했지만, 실제 풀이 방식은 'BFS' 방식이 적합했다.
매순간의 최선의 값을 구하는 것이 아니라, 현재 위치에서 도출되는 값들을 계속해서 활용하는 식이었다.

풀이의 핵심은 각 위치 도달에 걸린 시간을 저장해두고 이를 활용하는 것이다.
위 예제의 5,17을 활용해보면 이렇다.
사용자는 -1,+1,*2가 가능하다.  
즉 첫 움직임에는 4,6,10이 도출되고 위치 4,6,10에 도착한 시간은 position[5] + 1이다.
그리고 이제 4,6,10에서 -1,+1,*2를 다 진행하는 것이다. 

얼핏보면, 내가 최초에 생각했던 모든 경우의 수를 구하는 방식과 유사해보인다. 하지만 크게 2가지 차이점이 있다.
1. 나는 모든 경우의 수를 계산하기 때문에, 값이 겹치는 경우도 계산한다는 점
2. 모든 위치와 걸린 시간을 저장할 공간이 필요 없다는 점

이런 문제를 해결한 것이 queue 자료 구조를 활용하는 것과 방문 위치를 저장해두는 것이다.
queue 자료 구조를 사용함으로써 도출된 위치들 기준 -1,+1,*2 를 하고,
해당 위치에 대한 값이 이미 배열 내에 존재한다면 넘어가면 된다.
즉, 결과적으로 1~100,000까지 위치에 대한 값만 존재하기 때문에 메모리 문제 없이 충분히 사용이 가능하다.


 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class s1_1697 {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        int N = nextInt();
        int K = nextInt();
        if (N == K) {
            System.out.print(0);
            return;
        }
        // 해당 위치에 걸린 시간을 저장하기 위한 배열
        int[] positions = new int[100001];
        // 방문한 위치 정보들을 저장할 queue
        Queue<Integer> q = new LinkedList<>();
        // 현재 위치를 큐에 저장하고, 걸린 시간도 positions에도 저장
        positions[N] = 1;
        q.add(N);
        // 이제 q에 저장된 위치를 기반으로 -1,+1,*2 를 실행하고, positions에 저장하면서 +1을 한다.
        boolean stop = false;
        while (!stop) {
            int current = q.poll();
            // 다음 위치 생성
            int[] nextPositions = { current - 1, current + 1, current * 2 };
            for (int po : nextPositions) {
                // 조건 처리
                if (po < 0 || po > 100000)
                    continue;
                // 값이 이미 존재한다면, 넘어간다
                if (positions[po] != 0)
                    continue;
                // 값이 이미 존재하지 않고, 1<=N<=100000 라면 해당 위치에 걸린 시간을 저장한다.
                // 해당 위치에 걸린 시간은 -1,+1,*2 하기 전의 위치까지 걸린 시간 + 1 이다.
                positions[po] = positions[current] + 1;
                if (po == K) {
                    stop = true;
                    break;
                }
                // 이 위치를 queue에 저장하여, 다음에 활용할 수 있도록 한다.
                q.add(po);
            }
        }
        System.out.print(positions[K] - 1);
    }

    public static String next() throws Exception {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    public static int nextInt() throws Exception {
        return Integer.parseInt(next());
    }
}
