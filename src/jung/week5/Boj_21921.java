package src.jung.week5;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 블로그(SIL3)
 * 시간 : 308ms
 * 메모리: 38940KB
 * 링크 : https://www.acmicpc.net/problem/21921
 * */
public class Boj_21921 {
    // 블로그를 시작하고 지난 일수 n
    // x일 동안 가장 많이 들어온 방문자 수와 기간이 몇 개 있는지
    // 만약 최대 방문자 수가 0명이라면 SAD를 출력
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 블로그를 시작하고 지난 일수
        int x = Integer.parseInt(st.nextToken()); // x일 동안

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) { // 1일차부터 n일차까지 하루 방문자 수 입력
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0; // 방문자수의 합
        int day = 1; // x일 동안 방문자가 가장 많이 들어온 기간
        for (int i = 0; i < x; i++) sum += arr[i]; // 1일부터 x일까지 방문자수의 합

        int max = sum; // 최대 방문자수
        for (int i = x; i < n; i++) {
            sum -= arr[i-x]; // 첫번째 제외
            sum += arr[i];

            if (max < sum) { // 최대 방문자수 갱신
                day = 0; // 기간 초기화 후 증가
                day++;
                max = sum;
            } else if (max == sum) { // 최대 방문자수가 같은 기간이면
                day++;
            }
        }

        if (max == 0) { // 최대 방문자 수가 0명
            bw.append("SAD" + "\n");
            bw.flush();
            return;
        }

        bw.append(max + "\n" + day);
        bw.flush();
        bw.close();
        br.close();
    }
}
