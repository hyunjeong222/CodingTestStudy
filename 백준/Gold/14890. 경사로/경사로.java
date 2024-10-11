import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, l;
    static int[][] map;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cnt = 0;
        for (int i = 0; i < n; i++) {
            if (check(i, 0, true)) cnt++; // 행 체크
            if (check(0, i, false)) cnt++; // 열 체크
        }

        System.out.println(cnt);
    }

    private static boolean check(int x, int y, boolean flag) {
        int[] height = new int[n]; // 길에 대한 높이 정보
        boolean[] checked = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (flag) height[i] = map[x][i]; // 행
            else height[i] = map[i][y]; // 열
        }

        for (int i = 0; i < n-1; i++) {
            int diff = height[i]-height[i+1];
            if (height[i] == height[i+1]) continue;
            else if (diff == 1) { // 내려가는 경사
                for (int j = i+1; j <= i+l; j++) { // l개의 연속된 칸
                    // 범위를 넘어가거나 칸의 높이가 다르거나 이미 경사로가 있는 경우
                    if (j >= n || height[i+1] != height[j] || checked[j]) return false;
                    checked[j] = true;
                }
            } else if (diff == -1) { // 올라가는 경사
                for (int j = i; j > i-l; j--) { // l개의 연속된 칸
                    // 범위를 넘어가거나 칸의 높이가 다르거나 이미 경사로가 있는 경우
                    if (j < 0 || height[i] != height[j] || checked[j]) return false;
                    checked[j] = true;
                }
            } else { // 높이가 2이상 차이 - 길 X
                return false;
            }
        }
        return true;
    }
}