import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 새의 수
        int m = Integer.parseInt(st.nextToken()); // 명상하는 초

        int[][] birds = new int[n+1][m];
        int[] sum = new int[m];
        String sounds;
        char dir;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            dir = st.nextToken().charAt(0);
            sounds = st.nextToken();

            for (int j = 0; j < m; j++) {
                if (sounds.charAt(j) == '1') { // 새가 지저귐
                    int value = dir == 'L' ? -1 : 1;
                    birds[i][j] = value;
                    sum[j] += value;
                }
            }
        }

        int idx = 0; int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int total = 0;
            int maxTotal = 0;

            for (int j = 0; j < m; j++) {
                total += sum[j] - birds[i][j];
                maxTotal = Math.max(maxTotal, Math.abs(total));
            }

            if (maxTotal < ans) {
                idx = i;
                ans = maxTotal;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(idx).append("\n").append(ans);

        System.out.println(sb.toString());

        br.close();
    }
}