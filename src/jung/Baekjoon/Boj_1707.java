package src.jung.Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 이분 그래프(GOL)
 * 시간 : 912ms
 * 메모리 : 281520KB
 * 링크 : https://www.acmicpc.net/problem/1707
 */
public class Boj_1707 {
    static int t, n, m;
    static int u, v;
    static ArrayList<Integer>[] list;
    static int[] checked;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        StringTokenizer st;
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken()); // 정점의 개수
            m = Integer.parseInt(st.nextToken()); // 간선의 개수

            checked = new int[n + 1];
            flag = true; // 이분 그래프면 true, 아니면 false

            list = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                // 정점의 정보
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());

                list[u].add(v);
                list[v].add(u);
            }

            for (int i = 1; i <= n; i++) {
                if (checked[i] == 0) dfs(i, 1); // 시작 노드, 색 정보
                if (!flag) break; // 이분 그래프가 아니라면 탐색 종료
            }

            bw.append(flag ? "YES" + "\n" : "NO" + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }


    private static void dfs(int start, int color) {
        checked[start] = color;
        for (int next : list[start]) {
            if (checked[next] == color) { // 같은 색이라면
                flag = false; // 이분 그래프 아님
                return;
            }
            if (checked[next] == 0) { // 방문하지 않은 노드라면
                dfs(next, color*-1); // 1과 -1로 정점 색을 구별
            }
        }
    }
}