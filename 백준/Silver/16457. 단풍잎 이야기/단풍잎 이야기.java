import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int max = 0;
    static int[][] arr;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb  = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 키의 개수
        m = Integer.parseInt(st.nextToken()); // 퀘스트의 개수
        k = Integer.parseInt(st.nextToken()); // 퀘스트 당 사용해야 하는 스킬 수

        arr = new int[m][k];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        checked = new boolean[101];
        dfs(0, 1);

        System.out.println(max);

        br.close();
    }

    private static void dfs(int depth, int idx) { // 현재까지 선택한 스킬 개수, 다음 선택 시작 번호
        if (depth == n) {
            int cnt = 0;
            loop :
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < k; j++) {
                    if (!checked[arr[i][j]]) continue loop;
                }
                cnt++;
            }
            max = Math.max(max, cnt);
            return;
        }

        for (int i = idx; i <= n*2; i++) {
            if (checked[i]) continue;

            checked[i] = true;
            dfs(depth+1, i+1);
            checked[i] = false;
        }
    }
}