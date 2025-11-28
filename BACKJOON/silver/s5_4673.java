package silver;

/**
 * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 * 
 * problem : 4673_Self Numbers
 * date : 2025-11-28
 * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 * 
 * [문제]
 * In 1949 the Indian mathematician D.R.
 * Kaprekar discovered a class of numbers called self-numbers.
 * For any positive integer n, define d(n) to be n plus the sum of the
 * digits of n.
 * (The d stands for digitadition, a term coined by Kaprekar.) For
 * example, d(75) = 75 + 7 + 5 = 87.
 * Given any positive integer n as a starting point, you can construct the
 * infinite increasing sequence of integers n, d(n), d(d(n)), d(d(d(n))),
 * ....
 * For example, if you start with 33, the next number is 33 + 3 + 3 = 39,
 * the next is 39 + 3 + 9 = 51, the next is 51 + 5 + 1 = 57, and so you
 * generate the sequence
 * 
 * 33, 39, 51, 57, 69, 84, 96, 111, 114, 120, 123, 129, 141, ...
 * 
 * The number n is called a generator of d(n).
 * In the sequence above, 33 is a generator of 39, 39 is a generator of
 * 51, 51 is a generator of 57, and so on.
 * Some numbers have more than one generator:
 * for example, 101 has two generators, 91 and 100.
 * A number with no generators is a self-number.
 * There are thirteen self-numbers less than 100: 1, 3, 5, 7, 9, 20, 31,
 * 42, 53, 64, 75, 86, and 97.
 * 
 * Write a program to output all positive self-numbers less than 10000 in
 * increasing order, one per line.
 * 
 * [해석]
 * 어딘가에서 누가 찾아냈다. 셀프넘버라고 불리는 클래스를.
 * 어떠한 양수든, d(n)하면 n+가 된다. digits of n 을 합친 값.
 * 예를 들어서 d(75) = 75 + 7 + 5 = 87
 * 근데 무한 증가 시퀀스를 만들 수 있다 재귀함수로 d((d(n))) 이런 식으로
 * 예를 들어서 d(d(33))이면 d(39) -> d(51)
 * 근데 이게 1:1 대응은 아니다. 101 같은 경우 91,100으로 만들어지기 때문이다.
 * 제너레이터가 없는 셀프 넘버가 있다. 100미만으로 13개가 있다.
 * 10000미만의 모든 셀프 넘버를 구해라. 오름 차순으로 구해야된다.
 * 그냥 일단 13개는 출력하고 97부터 d(97) -> d(d(97)) 이런 식으로 가야되나.
 * 근데 그럼 98,99이런 애들은 어캄 , 어차피 영문 해석 다 못했으니까 그렇게 해보자.
 * 
 * ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
 * ─ ─ ─ ─
 * [예제 입력 1]
 * 
 * 
 * [예제 출력 1]
 * 1
 * 3
 * 5
 * 7
 * 9
 * 20
 * 31
 * 42
 * 53
 * 64
 * |
 * | <-- a lot more numbers
 * |
 * 9903
 * 9914
 * 9925
 * 9927
 * 9938
 * 9949
 * 9960
 * 9971
 * 9982
 * 9993
 * ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
 * ─ ─ ─ ─
 * [풀이]
 * - 접근 방식 :
 * 셀프넘버가 되기 위해선 어떻게 해야되지? -> 생성자가 없어야 함.
 * 그러면 생성자로 만들어진 숫자는 셀프넘버가 아닌거임.
 * 만약에 1~10000까지 모두를 생성자로 써서, 그거로 만들어진 숫자는 생성자가 아니니까
 * 차라리 전체에서 셀프넘버가 아닌 애들을 구해서 빼버리는 것도 괜찮을듯
 * 
 * - 결과 : o
 */

public class s5_4673 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        boolean[] isNotSelfNumber = new boolean[10001];
        for (int i = 0; i < 10001; i++) {
            int ns = d(i);
            if (ns <= 10000) {
                isNotSelfNumber[ns] = true;
            }
        }
        for (int i = 0; i < 10001; i++) {
            if (!isNotSelfNumber[i]) {
                sb.append(i).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static int d(int seed) {
        int sum = seed;
        while (seed != 0) {
            sum += seed % 10;
            seed /= 10;
        }
        return sum;
    }

}
