import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int ans = 0; // 모든 문제를 다 풀 수 있는지
    static int min = Integer.MAX_VALUE; // 모든 문제를 풀 수 있는 최소한의 팀원 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 문제의 수
        int m = Integer.parseInt(st.nextToken()); // 학생들의 수

        arr = new int[m+1];
        for (int i = 1; i <= n; i++) {
            ans |= 1 << i;
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()); // 해당 학생이 풀 수 있는 문제 수
            while (num --> 0) {
                arr[i] |= 1 << Integer.parseInt(st.nextToken()); // 해당 학생이 풀 수 있는 문제 번호들
            }
        }

        dfs(m, 1, 0, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);

        br.close();
    }

    private static void dfs(int m, int start, int cnt, int number) {
        if (canCompleteAll(number)) {
            min = Math.min(min, cnt);
            return;
        }

        for (int i = start; i <= m; i++) {
            // 이번 학생 포함
            dfs(m, i+1, cnt+1, number|arr[i]);
            // 이번 학생 미포함
            dfs(m, i+1, cnt, number);
        }
    }

    private static boolean canCompleteAll(int number) {
        return number == ans;
    }
}