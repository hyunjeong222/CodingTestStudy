package src.jung.Baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 숨바꼭질(SIL)
 * 시간 : 124ms
 * 메모리 : 17428KB
 * 링크 : https://www.acmicpc.net/problem/1697
 */
public class Boj_1697 {
    static int n, k;
    static int[] checked;
    static int size = 100001;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        checked = new int[size];

        if (n == k) bw.append(0 + "\n");
        else {
            ans = bfs(n);
            bw.append(ans + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int x) {
        Queue<Integer> que  = new LinkedList<>();
        que.offer(x);
        checked[x] = 1;

        while (!que.isEmpty()) {
            x = que.poll();
            for (int i = 0; i < 3; i++) {
                int next;

                if (i == 0) next = x - 1;
                else if (i == 1) next = x + 1;
                else next = x * 2;

                if (next == k) return checked[x];

                if (next >= 0 && next < checked.length && checked[next] == 0) {
                    que.offer(next);
                    checked[next] = checked[x]+1;
                }
            }
        }
        return -1;
    }
}
