package src.jung.Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 촌수계산(SIL)
 * 시간 : 80ms
 * 메모리 : 11712KB
 * 링크 : https://www.acmicpc.net/problem/2644
 */
public class Boj_2644 {
    static int n, m;
    static int a, b;
    static int x, y;
    static int ans = -1;
    static ArrayList<ArrayList<Integer>> map;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine()); // 사람의 수
        map = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }
        checked = new boolean[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 촌수를 계산해야 하는 서로 다른 두 사람의 번호
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine()); // 부모 자식들 간의 관계의 개수
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()); // 부모
            y = Integer.parseInt(st.nextToken()); // 자식

            // 부모 자식은 연결되어 있으므로 노드로 생각
            map.get(x).add(y);
            map.get(y).add(x);
        }

        dfs(a, b, 0);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int a, int b, int cnt) {
        checked[a] = true;

        for (int next : map.get(a)) {
            if (!checked[next]) {
                if (next == b) {
                    ans = cnt + 1;
                    return;
                }
                dfs(next, b, cnt+1);
            }
        }
    }
}
