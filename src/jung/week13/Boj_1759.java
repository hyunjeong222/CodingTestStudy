package src.jung.week13;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 암호 만들기(GOL)
 * 시간 : 84ms
 * 메모리 : 12728KB
 * 링크 : https://www.acmicpc.net/problem/1759
 */
public class Boj_1759 {
    static int l, c;
    static String[] arr;
    static String[] combi;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new String[c];
        combi = new String[l];
        arr = br.readLine().split(" ");
        Arrays.sort(arr);
        // System.out.println(Arrays.toString(arr));

        dfs(0, 0);
        System.out.println(sb);
    }

    private static void dfs(int num, int depth) {
        if (depth == l) {
            if (isVal(combi)) {
                for (String val : combi) {
                    sb.append(val);
                }
                sb.append("\n");
            }
            return;
        }

        for (int i = num; i < c; i++) {
            combi[depth] = arr[i];
            dfs(i+1, depth+1);
        }
    }

    private static boolean isVal(String[] combi) {
        int vowelCnt = 0;
        int consonantCnt = 0;

        for (String val : combi) {
            if (val.equals("a") || val.equals("e") || val.equals("i") || val.equals("o") || val.equals("u")) vowelCnt++;
            else consonantCnt++;
        }

        if (vowelCnt >= 1 && consonantCnt >= 2) return true;
        else return false;
    }
}
