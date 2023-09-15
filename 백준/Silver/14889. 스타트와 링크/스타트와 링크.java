import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] stat;
    static boolean[] checked;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine()); // 축구를 하기 위해 모인 사람
        stat = new int[n][n];
        checked = new boolean[n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                stat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combi(0,0);
        System.out.println(min);
    }

    // idx 인덱스, depth 조합 개수
    private static void combi(int idx, int depth) {
        // 팀 조합 완성
        if (depth == n/2) {
            getStat(); // 각 팀 능력차이 계산
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

        if (val == 0) { // 점수차가 0이라면 가장 낮은 최솟값
            System.out.println(val);
            System.exit(0);
        }

        min = Math.min(min, val); // 최솟값 비교
    }
}