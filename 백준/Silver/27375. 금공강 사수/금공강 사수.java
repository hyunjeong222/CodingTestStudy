import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int ans;
    static int[][] lessons;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb  = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 수업 개수
        k = Integer.parseInt(st.nextToken()); // 듣고 싶은 학점

        lessons = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                lessons[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, new boolean[5][11]);

        System.out.println(ans);

        br.close();
    }

    private static void dfs(int depth, int idx, boolean[][] checked) {
        if (depth == k) { // 정확히 k학점
            ans++; // 금요일에 수업이 하나도 없는 시간표의 가짓수
            return;
        }

        for (int i = idx; i < n; i++) {
            if (isPossible(checked, lessons[i])) {
                setStatus(lessons[i], checked, true);
                dfs(depth+((lessons[i][2]-lessons[i][1])+1), i+1, checked);
                setStatus(lessons[i], checked, false);
            }
        }
    }

    private static void setStatus(int[] lesson, boolean[][] checked, boolean status) {
        for (int i = lesson[1]; i <= lesson[2]; i++) {
            checked[lesson[0]][i] = status;
        }
    }

    private static boolean isPossible(boolean[][] checked, int[] lesson) {
        if (lesson[0] == 5) return false; // 금공강

        // 같은 요일, 같은 교시에 열리는 두 수업은 동시에 수강 불가
        for (int i = lesson[1]; i <= lesson[2]; i++) {
            if (checked[lesson[0]][i]) return false;
        }

        return true;
    }
}