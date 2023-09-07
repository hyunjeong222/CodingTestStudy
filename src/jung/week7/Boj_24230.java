package src.jung.week7;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 트리 색칠하기(GOL5)
 * 시간 : 1800ms
 * 메모리 : 128560KB
 * 링크 : https://www.acmicpc.net/problem/24230
 */
public class Boj_24230 {
    static int[] colors;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static boolean[] checked;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); // 정점의 개수

        colors = new int[n+1]; // 각 정점의 색 정보를 담은 배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            colors[i] = Integer.parseInt(st.nextToken());
        }
        // System.out.println(Arrays.toString(colors)); [0, 0, 0, 2, 0, 1, 2, 2]

        // 정점 연결 정보를 담을 list
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        // System.out.println(list);

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 정점 연결
            list.get(a).add(b);
            list.get(b).add(a);
        }
        // System.out.println(list); [[], [2, 3, 4], [1, 5], [1, 6, 7], [1], [2], [3], [3]]

        count = 0; // 최소 색칠 횟수
        checked = new boolean[n+1];
        dfs(1, 0); // 정점, 색 정보

        bw.append(count + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int i, int c) {
        checked[i] = true;

        if (colors[i] != c) { // 컬러가 같지 않다면
            count++; // 색칠 횟수 증가
            c = colors[i]; // 색 변경
        }

        for (int next : list.get(i)) {
            if (!checked[next]) dfs(next, c);
        }
    }
}
