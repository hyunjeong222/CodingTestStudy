package src.jung.Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : N과 M (2)(SIL3)
 * 시간 : 80ms
 * 메모리 : 11628KB
 * 링크 : https://www.acmicpc.net/problem/15650
 */
public class Boj_15650 {
    static int[] arr;
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];

        dfs(1, 0);

        System.out.println(sb);
        br.close();
    }

    private static void dfs(int num, int depth) {
        if (depth == m) {
            for (int val : arr) {
                sb.append(val + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = num; i <= n; i++) {
            arr[depth] = i;
            dfs(i+1, depth+1);
        }
    }
}
