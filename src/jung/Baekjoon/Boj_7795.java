package src.jung.Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 먹을 것인가 먹힐 것인가(SIL3)
 * 시간 : 1100ms
 * 메모리 : 44692KB
 * 링크 : https://www.acmicpc.net/problem/7795
 */
public class Boj_7795 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int cnt = 0;

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int[] a_arr = new int[a];
            int[] b_arr = new int[b];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < a; j++) {
                a_arr[j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < b; j++) {
                b_arr[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(a_arr);
            Arrays.sort(b_arr);

            for (int j = 0; j < a; j++) {
                for (int k = 0; k < b; k++) {
                    if (a_arr[j] <= b_arr[k]) break;
                    else cnt++;
                }
            }
            bw.append(cnt + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }
}
