package src.jung.Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 겹치는 건 싫어(SIL1)
 * 시간 : 300ms
 * 메모리 : 37668KB
 * 링크 : https://www.acmicpc.net/problem/20922
 */
public class Boj_20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 100,000 이하 양의 정수로 이루어진 수열의 길이
        int k = Integer.parseInt(st.nextToken()); // 들어갈 수 있는 원소 개수, k개 이하

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n]; // 나열된 수열
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] num = new int[100001]; // 같은 정수의 개수를 확인 할 배열
        // 같은 지점에 포인터 두고 시작
        int start = 0;
        int end = 0;
        int max = 0; // 연속 수열 길이의 최댓값
        while (end < n) {
            if (end <= n-1 && num[arr[end]] < k) {
                num[arr[end]]++;
                end++;
            }else if (num[arr[end]] == k) {
                num[arr[start]]--;
                start++;
            }
            max = Math.max(end-start, max);
        }
        bw.append(max + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
