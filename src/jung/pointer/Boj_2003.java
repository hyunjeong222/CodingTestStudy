package src.jung.pointer;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 수들의 합 2(SIL4)
 * 시간 : 116ms
 * 메모리 : 14456KB
 * 링크 : https://www.acmicpc.net/problem/2003
 */
public class Boj_2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int cnt = 0;
        while (true) {
            if (sum >= m) sum -= arr[start++];
            else if (end == n) break;
            else sum += arr[end++];

            if (sum == m) cnt++;
        }
        bw.append(cnt + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
