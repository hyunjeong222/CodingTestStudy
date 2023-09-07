package src.jung.Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 두 용액(GOL5)
 * 시간 : 288ms
 * 메모리: 31712KB
 * 링크 : https://www.acmicpc.net/problem/2470
 * */
public class Boj_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); // 전체 용액의 수

        int[] arr = new int[n]; // 용액의 특성값
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        // -99, -2, -1, 4, 98

        int start = 0;
        int end = arr.length - 1;

        int max = Integer.MAX_VALUE; // int 범위 -2,147,483,648 ~ 2,147,483,647
        int sum = 0;

        int ans1 = 0; // 용액 1
        int ans2 = 0; // 용액 2

        while (start < end) {
            sum = arr[start] + arr[end]; // 용액의 합

            if (Math.abs(sum) < max) {
                ans1 = arr[start];
                ans2 = arr[end];
                max = Math.abs(sum);
            }

            if (sum > 0) {
                end--;
            }else {
                start++;
            }
        }
        bw.append(ans1 + " " + ans2 + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
