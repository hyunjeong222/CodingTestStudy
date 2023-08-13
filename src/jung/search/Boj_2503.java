package src.jung.search;

import java.io.*;

/**
 * 문제 이름(난이도) : 숫자 야구(SIL3)
 * 시간 : ms
 * 메모리: KB
 * 링크 : https://www.acmicpc.net/problem/2503
 * https://velog.io/@gale4739/%EB%B0%B1%EC%A4%80-2503-%EC%88%AB%EC%9E%90-%EC%95%BC%EA%B5%ACJava-m6qspvyk
 * */
public class Boj_2503 {
    static boolean[] check = new boolean[988]; // 가능한 숫자 체크
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 123; i < 987; i++) { // 0이 들어가면 안되고, 숫자가 중복되면 안됨
            String num  = Integer.toString(i);

            if (num.charAt(0) == '0' || num.charAt(1) == '0' || num.charAt(2) == '0') continue;
            if (num.charAt(0) == num.charAt(1) || num.charAt(0) == num.charAt(2) ||num.charAt(1) == num.charAt(2)) continue;

            check[i] = true;
        }

    }
}
