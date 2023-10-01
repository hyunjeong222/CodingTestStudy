package src.jung.Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 상근이의 여행(SIL)
 * 시간 : 396ms
 * 메모리 : 54068KB
 * 링크 : https://www.acmicpc.net/problem/9372
 * 최소 신장 트리 : 간선의 개수는 정점의 개수 - 1
 * 즉, 모든 국가가 연결되어 있기 때문에 비행기 종류의 최소 개수는 국가 수 - 1
 */
public class Boj_9372 {
    static int t;
    static int n, m;
    static int u, v;
    static ArrayList<Integer>[] list;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine()); // 테스트 케이스의 수

        StringTokenizer st;

        while (t --> 0) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken()); // 국가의 수 (정점)
            m = Integer.parseInt(st.nextToken()); // 비행기의 종류 (간선)

            checked = new boolean[n+1];
            list = new ArrayList[n+1];
            for (int i = 1; i <= n; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                // 정점의 정보
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());

                // 연결
                list[u].add(v);
                list[v].add(u);
            }

            System.out.println(dfs(1, 0));
        }
        br.close();
    }

    private static int dfs(int start, int count) {
        checked[start] = true;

        for (int next : list[start]) {
            if (checked[next]) continue;
            count = dfs(next, count+1);
        }
        return count;
    }
}
