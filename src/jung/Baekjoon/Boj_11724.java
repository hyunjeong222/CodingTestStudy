package src.jung.Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름(난이도) : 연결 요소의 개수(SIL2)
 * 시간 : 480ms (dfs), 608ms (bfs)
 * 메모리 : 118268KB, 144516KB
 * 링크 : https://www.acmicpc.net/problem/11724
 */
public class Boj_11724 {
    /*
    static int n, m;
    static int[][] arr;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        m = Integer.parseInt(st.nextToken()); // 간선의 개수

        arr = new int[n+1][n+1];
        checked = new boolean[n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 무방향 그래프 특성
            arr[u][v] = 1;
            arr[v][u] = 1;
        }
        // System.out.println(Arrays.deepToString(arr));
        int ans = 0; // 연결 요소의 개수
        for (int i = 1; i <= n; i++) {
            if (!checked[i]) {
                dfs(i);
                ans++;

            }
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int cur) {
        if (checked[cur]) return;

        checked[cur] = true;
        for (int i = 1; i <= n; i++) {
            if (arr[cur][i] == 1) {
                dfs(i);
            }
        }
    }
    */
    static int n, m;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        m = Integer.parseInt(st.nextToken()); // 간선의 개수

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        // System.out.println(list);

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list.get(u).add(v);
            list.get(v).add(u);
        }
        // System.out.println(list);

        checked = new boolean[n+1];
        int ans = 0; // 연결 요소의 개수
        for (int i = 1; i <= n; i++) {
            if (!checked[i]) {
                bfs(i);
                ans++;
            }
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int cur) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(cur);

        while (!que.isEmpty()) {
            int num = que.poll();

            for (int i = 0; i < list.get(num).size(); i++) {
                int new_num = list.get(num).get(i);

                if (!checked[new_num]) {
                    que.offer(new_num);
                    checked[new_num] = true;
                }
            }
        }
    }
}