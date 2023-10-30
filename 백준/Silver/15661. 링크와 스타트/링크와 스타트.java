import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] power;
    static boolean[] checked;
    static int ans = Integer.MAX_VALUE; // 두 팀의 능력치 차이의 최솟값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine()); // 축구를 하기 위해 모인 사람의 인원
        power = new int[n][n]; // 능력치 표
        checked = new boolean[n]; // 조합을 위한 방문배열
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                power[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combi(0);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void combi(int idx) {
        if (idx == n) { // 모든 인덱스를 다 탐색했다면
            getPower();
            return;
        }

        // 현재 인덱스 포함
        checked[idx] = true;
        combi(idx+1);
        // 현재 인덱스 포함 안함
        checked[idx] = false;
        combi(idx+1);
    }

    private static void getPower() {
        int start = 0;
        int link = 0;

        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (checked[i] && checked[j]) {
                    start += power[i][j];
                    start += power[j][i];
                } else if (!checked[i] && !checked[j]) {
                    link += power[i][j];
                    link += power[j][i];
                }
            }
        }

        int val = Math.abs(start-link); // 두 팀간의 능력치 차이

        if (val == 0) {
            System.out.println(val);
            System.exit(0);
        }

        ans = Math.min(ans, val);
    }
}