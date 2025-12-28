package silver;

/**
 * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 * 
 * problem: 2941_LJESNJAK
 * date: 2025-11-28
 * 
 * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 * 
 * [문제]
 * Not so long ago, before operating systems were as powerful as they are
 * today,
 * computers (which often had turbo buttons on the exterior) couldn't
 * handle certain letters of the Croatian alphabet.
 * Programmers agreed to use two or three-letter substitutions as in this
 * table:
 * 
 * <pre>
 * | Letter | Characters |
 * | ------ | ---------- |
 * | č      | c=         |
 * | ć      | c-         |
 * | dž     | dz=        |
 * | đ      | d-         |
 * | lj     | lj         |
 * | nj     | nj         |
 * | š      | s=         |
 * | ž      | z=         |
 * </pre>
 * For example, the word ljes=njak consists of six letters in the Croatian
 * alphabet: lj, e, š, nj, a, k.
 * 
 * Write a program that calculates the number of letters in the given
 * word.
 * 
 * [해석]
 * 옛날에, OS가 안좋을 때, 크로아티아 언어를 못만졌다. 프로그래머는 아래 테이블처럼 했다
 * cv = c= , c` = c- , dzv = dz = 만약에 영어로 ljes=njak라 돼있으면, 6개로 이루어진 거다.
 * 입력값을 크로아티아 언어로 몇 개인지 계산하는 프로그램을 짜세요.
 * 입력은 한줄이 들어옵니다. 오직 소문자,-,=만 들어갑니다. 입력값은 위 테이블 기준으로 인코딩 됐습니다.
 * 
 * [입력]
 * The first line contains a string of at most 100 characters.
 * Only lowercase letters of the English alphabet and characters '–' and
 * '=' will appear.
 * The string will represent a word encoded as described above.
 * 
 * 
 * [출력]
 * Output the number of letters in the input word.
 * 
 * ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
 * [예제 입력 1]
 * ljes=njak
 * 
 * [예제 출력 1]
 * 6
 * 
 * [예제 입력 2]
 * ddz=z=
 * 
 * [예제 출력 2]
 * 3
 * 
 * [예제 입력 3]
 * nljj
 * 
 * [예제 출력 3]
 * 3
 * 
 * [예제 입력 4]
 * c=c=
 * 
 * [예제 출력 4]
 * 2
 * 
 * [예제 입력 5]
 * dz=ak
 * 
 * [예제 출력 5]
 * 3
 * ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
 * [풀이]
 * - 접근 방식 : 저 테이블이 크로아티아 언어 표현 방법의 전부라면, 알파벳 목록들 저장해두고 다 1글자로 replace하면 될듯함
 * 같다.
 * - 결과 : O
 */
import java.util.Scanner;

public class s5_2941 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        // 크로아티아 알파벳 목록
        String[] croatia = { "c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=" };

        // 목록에 있는 단어를 발견하면 한 글자로 바꾸기
        for (String val : croatia) {
            str = str.replace(val, "*");
        }

        sc.close();
        System.out.println(str.length());
    }
}