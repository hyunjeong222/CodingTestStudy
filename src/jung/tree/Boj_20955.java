package src.jung.tree;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 민서의 응급 수술(GOL4)
 * 시간 : 312ms
 * 메모리 : 38840KB
 * 링크 : https://www.acmicpc.net/problem/20955
 * 사용 알고리즘 : 분리 집합 알고리즘 - 유니온 파인드
*/
public class Boj_20955 {
    static int n, m;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 뉴런의 개수
        int m = Integer.parseInt(st.nextToken()); // 시냅스의 개수

        parents = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
        int ans = 0; // 필요한 최소 연산 횟수
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (!union(u,v)) ans++; // 연결 끊기
        }

        // 그룹의 개수 세기
        for (int i = 1; i <= n; i++) {
            if (parents[i] == i) ans++;
        }

        // 그룹 연결 간선 - 1
        bw.append(ans-1 + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u == v) return false; // 같은 그룹에 속해있다면

        // 작은 노드로 부모 노드 변경
        if (u < v) parents[v] = u;
        else parents[u] = v;
        return true;
    }

    private static int find(int u) {
        if (parents[u] == u) return u;
        return parents[u] = find(parents[u]);
    }
}