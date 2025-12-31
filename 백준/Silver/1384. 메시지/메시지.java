import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        int n, cnt;
        int idx = 0;
        String[][] arr;
        while (true) {
            n = Integer.parseInt(br.readLine());

            if (n == 0) break; // 종료조건

            idx++;
            sb.append("Group ").append(idx).append("\n");

            arr = new String[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = st.nextToken();
                }
            }
            // System.out.println(Arrays.deepToString(arr));

            cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j].equals("N")) {
                        if (i < j) sb.append(arr[n-(j-i)][0]).append(" was nasty about ").append(arr[i][0]).append("\n");
                        else sb.append(arr[i-j][0]).append(" was nasty about ").append(arr[i][0]).append("\n");
                        cnt++;
                    }
                }
            }

            // 아무도 나쁜 말을 하지 않음
            if (cnt == 0) sb.append("Nobody was nasty").append("\n");

            sb.append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}