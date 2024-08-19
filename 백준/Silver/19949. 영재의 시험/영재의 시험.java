import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] answer;
    static int[] youngjae;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        answer = new int[10];
        youngjae = new int[10];
        for (int i = 0; i < 10; i++) {
            answer[i] = Integer.parseInt(st.nextToken());
        }
        backtracking(0);

        System.out.println(ans);
    }

    private static void backtracking(int depth) {
        if (depth == 10) {
            int cnt = 0;
            for (int i = 0; i < 10; i++) {
                if (answer[i] == youngjae[i]) cnt++;
            }
            if (cnt >= 5) ans++;
            return;
        }

        for (int i = 1; i <= 5; i++) { // 5지 선다
            if (depth >= 2) { // 3개의 연속된 문제의 답이 같지 않아야 함
                if (i == youngjae[depth-1] && i == youngjae[depth-2]) continue;
            }
            youngjae[depth] = i;
            backtracking(depth+1);
        }
    }
}