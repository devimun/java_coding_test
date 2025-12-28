import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class uk_2839 {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            solve();
        }
    }

    public static void solve() throws Exception {
        int output = -1;
        int N = nextInt();
        int min = 5001;
        if (N > 5) {
            int mok5 = N / 5;
            int mok3 = N / 3;
            int remain = N % 5;
            if (remain > 0) {
                for (int i = mok5; i >= 0; i--) {
                    for (int j = 0; j <= mok3; j++) {
                        if (5 * i + 3 * j == N) {
                            if (i + j < min) {
                                min = i + j;
                                output = i + j;
                            }
                        }
                    }
                }
            }
        } else {
            if (N % 5 == 0) {
                output = N / 5;
            }
            if (N % 3 == 0) {
                output = N / 3;
            }
        }
        System.out.println(output);
    }

    public static int nextInt() throws Exception {
        return Integer.parseInt(next());
    }

    public static String next() throws Exception {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
}
