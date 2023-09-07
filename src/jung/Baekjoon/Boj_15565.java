package src.jung.Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 귀여운 라이언(SIL1)
 * 시간 : 404ms
 * 메모리 : 91168KB
 * 링크 : https://www.acmicpc.net/problem/15565
 * */
public class Boj_15565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int end = 0;
        int cnt = 0;
        int min = Integer.MAX_VALUE;

        bw.append(min + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
