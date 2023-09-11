package src.jung.Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : N과 M (1)(SIL3)
 * 시간 : 208ms
 * 메모리 : 46188KB
 * 링크 : https://www.acmicpc.net/problem/15649
 * 사용 알고리즘 : 순열/조합, 백트래킹
 */
public class Boj_15649 {
    // 1부터 N까지의 수 중에서 M 개의 조합 nCm을 모두 구하는 문제
    static int[] arr;
    static boolean[] checked;
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        checked = new boolean[n];

        dfs(0);
        bw.append(sb);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int depth) throws IOException {
        // 재귀의 깊이가 m과 같아지면 탐색과정에서 담았던 배열을 출력
        if (depth == m) {
            for (int val : arr) {
                sb.append(val + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            // 만약 해당 노드를 방문하지 않았다면 ?
            if (!checked[i]) {
                checked[i] = true; // 해당 노드 방문 상태로 변경
                arr[depth] = i+1; // 해당 깊이를 index로 하여 i+1 값 저장
                dfs(depth+1); // 다음 자식 노드 방문을 위해 depth 1 증가시키면서 재귀 호출

                checked[i] = false; // 자식 노드 방문이 끝나고 돌아오면 방문 노드를 방문하지 않은 상태로 변경
            }
        }
    }
}
