package src.jung.Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 스타트와 링크(SIL1)
 * 시간 : 324ms
 * 메모리 : 15448KB
 * 링크 : https://www.acmicpc.net/problem/14889
 */
public class Boj_14889 {
    static int n;
    static int[][] stat;
    static boolean[] checked;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine()); // 축구를 하기 위해 모인 사람

        stat = new int[n][n]; // 능력치 담을 배열
        checked = new boolean[n]; // 조합하기 위한 방문배열

        // 능력치 입력
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                stat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combi(0, 0); // idx, 조합개수
        System.out.println(min);
    }

    private static void combi(int idx, int depth) {
        if (depth == (n/2)) { // 팀이 만들어졌다면
            getStat(); // 능력치 계산
            return;
        }

        for (int i = idx; i < n; i++) {
            if (!checked[i]) {
                checked[i] = true;
                combi(i+1, depth+1);
                checked[i] = false;
            }
        }
    }

    private static void getStat() {
        int startTeam = 0;
        int linkTeam = 0;

        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                // true : startTeam
                // false : linkTeam
                if (checked[i] && checked[j]) {
                    startTeam += stat[i][j];
                    startTeam += stat[j][i];
                } else if (!checked[i] && !checked[j]) {
                    linkTeam += stat[i][j];
                    linkTeam += stat[j][i];
                }
            }
        }

        // 두 팀의 점수차이
        int val = Math.abs(startTeam-linkTeam);

        if (val == 0) { // 점수차가 0이라면 가장 적은 점수차
            System.out.println(val);
            System.exit(0);
        }

        min = Math.min(val, min); // 최솟값 비교
    }
}