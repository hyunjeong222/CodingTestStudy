import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int cnt;
        while (t --> 0) {
            br.readLine();
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            char[][] map = new char[r][c];
            for (int i = 0; i < r; i++) {
                String str = br.readLine();
                for (int j = 0; j < c; j++) {
                    map[i][j] = str.charAt(j);
                }
            }
            // System.out.println(Arrays.deepToString(map));

            cnt = 0;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (j < c-2 && map[i][j] == '>' && map[i][j+1] == 'o' && map[i][j+2] == '<') cnt++;
                    if (i < r-2 && map[i][j] == 'v' && map[i+1][j] == 'o' && map[i+2][j] == '^') cnt++;
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}