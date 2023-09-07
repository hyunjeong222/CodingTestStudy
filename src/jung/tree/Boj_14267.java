package src.jung.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 회사 문화 1(GOL4)
 * 시간 : 612ms, 524ms
 * 메모리 : 95532KB, 61936KB
 * 링크 : https://www.acmicpc.net/problem/14267
 */
public class Boj_14267 {
    /*
    static int n, m;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 회사의 직원수
        m = Integer.parseInt(st.nextToken()); // 최초의 칭찬 횟수

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        // System.out.println(list); [[], [], [], [], [], []]

        st = new StringTokenizer(br.readLine());
        st.nextToken(); // 사장 건너띄기
        // 직원 번호 입력
        for (int i = 2; i <= n; i++) {
            int member = Integer.parseInt(st.nextToken());
            list.get(member).add(i);
        }
        // System.out.println(list); [[], [2], [3], [4], [5], []]

        // 직원 번호에 칭찬 수치 입력
        count = new int[n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()); // 칭찬 받은 직원 번호
            int w = Integer.parseInt(st.nextToken()); // 칭찬 수치

            count[num] += w;
        }
        // System.out.println(Arrays.toString(count)); [0, 0, 2, 4, 0, 6]

        dfs(1);

        for (int i = 1; i <= n; i++) {
            bw.append(count[i] + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int cur) {
        for (int next : list.get(cur)) {
            count[next] += count[cur]; // 본인의 칭찬 누적치를 바로 아래 부하 직원 칭찬 누적치에 누적
            dfs(next);
        }
    }
    */

    static int n, m;
    static int[] count, parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 회사의 직원수
        m = Integer.parseInt(st.nextToken()); // 최초의 칭찬 횟수

        st = new StringTokenizer(br.readLine());
        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
        }
        // System.out.println(Arrays.toString(parent)); [0, -1, 1, 2, 3, 4]

        count = new int[n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()); // 칭찬 받은 직원 번호
            int w = Integer.parseInt(st.nextToken()); // 칭찬 수치

            count[num] += w;
        }
        // System.out.println(Arrays.toString(count)); [0, 0, 2, 4, 0, 6]

        for (int i = 2; i <= n; i++) {
            count[i] += count[parent[i]];
        }
        // System.out.println(Arrays.toString(count)); [0, 0, 2, 4, 0, 6]
        for (int i = 1; i <= n; i++) {
            bw.append(count[i] + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}