package src.jung.Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름(난이도) : DFS와 BFS(SIL)
 * 시간 : 192ms
 * 메모리 : 18040KB
 * 링크 : https://www.acmicpc.net/problem/1260
 */
public class Boj_1260 {
    static int n, m, v;
    static int a, b;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] checked;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        m = Integer.parseInt(st.nextToken()); // 간선의 개수
        v = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호

        checked = new boolean[n+1];

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        // 방문할 수 있는 정점이 여러개인 경우 더 작은 정점 번호부터 방문
        for (int i = 0; i <= n; i++) {
            Collections.sort(list.get(i));
        }

        dfs(v);
        System.out.println(sb);

        sb = new StringBuilder();
        checked = new boolean[n+1]; // bfs 하기위해 방문상태 초기화
        bfs(v);
        System.out.println(sb);
    }

    private static void bfs(int v) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(v);
        checked[v] = true;

        while (!que.isEmpty()) {
            int now = que.poll();
            sb.append(now + " ");
            for (int next : list.get(now)) {
                if (!checked[next]) {
                    checked[next] = true;
                    que.offer(next);
                }
            }
        }
    }

    private static void dfs(int start) {
        checked[start] = true;
        sb.append(start + " ");
        for (int next : list.get(start)) {
            if (!checked[next]) {
                dfs(next);
            }
        }
    }
}
