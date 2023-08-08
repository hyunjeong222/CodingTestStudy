package src.jung.bruteforce;

import java.io.*;

/**
 * 문제 이름(난이도) : 팰린드롬 만들기(SIL3)
 * 시간 : 128ms
 * 메모리: 14192KB
 * 링크 : https://www.acmicpc.net/problem/1213
 * */
public class Boj_1213 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine(); // 임한수의 영어 이름

        int[] alpha = new int[26]; // 알파벳 26개
        for (int i = 0; i < s.length(); i++) {
            alpha[s.charAt(i)-'A']++; // 문자열 알파벳의 수 저장
        }

        // 홀수 개수 체크
        int oddCnt = 0;
        int oddInx = -1;
        for (int i = 0; i < alpha.length; i++) {
            if (alpha[i] % 2 == 1) {
                oddCnt++;
                oddInx = i;
            }
        }

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        // 길이가 짝수  -> 모든 알파벳의 개수가 짝수
        // 길이가 홀수 -> 하나의 알파벳만 개수만 홀수
        if ((s.length()%2==0 && oddCnt>0) || (s.length()%2==1 && oddCnt!=1)) {
            System.out.println("I'm Sorry Hansoo");
            return;
        } else { // 팰린드롬 만들기
            for (int i = 0; i < alpha.length; i++) {
                for (int j = 0; j < alpha[i]/2; j++) {
                    sb1.append((char)(i + 'A')); // 앞부분
                    sb2.append((char)(i + 'A')); // 뒷부분
                }
            }
            if (oddInx != -1) { // 홀수가 있다면 중간에 위치
                sb1.append((char)(oddInx + 'A'));
            }
            sb2.reverse();
            sb1.append(sb2.toString());
        }
        System.out.println(sb1.toString());
        br.close();
    }
}
