package src.jung.Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 두 용액(GOL5)
 * 시간 : 288ms
 * 메모리: 31712KB
 * 링크 : https://www.acmicpc.net/problem/2470
 */
public class Boj_2470 {
    // 산성 용액의 특성값 양수
    // 알칼리성 용액의 특성값 음수
    // 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 함
    // 같은 용액만으로 특성값 0에 가까운 혼합 용액을 만드는 경우도 존재
    // 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾기
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
