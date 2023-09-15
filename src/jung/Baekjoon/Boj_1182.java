package src.jung.Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 부분수열의 합(SIL2)
 * 시간 : 100ms
 * 메모리 : 11620KB
 * 링크 : https://www.acmicpc.net/problem/1182
 */
public class Boj_1182 {
    static int n, s;
    static int[] arr;
    static int count; // 부분 수열의 개수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정수의 개수
        s = Integer.parseInt(st.nextToken()); // 그 수열의 원소를 다 더한 값

        // 정수 입력
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        System.out.println(s == 0 ? count-1 : count); // s가 0인 경우 공집합이 될 수도 있음
    }

    private static void dfs(int depth, int sum) {
        if (depth == n) { // 끝까지 탐색했는데
            if (sum == s) { // 합이 원하는 값과 같다면
                count++;
            }
            return;
        }

        dfs(depth+1, sum+arr[depth]); // 원소 선택해서 합에 더하고 넘어가기
        dfs(depth+1, sum); // 원소 선택안하고 넘어가기
    }
}
