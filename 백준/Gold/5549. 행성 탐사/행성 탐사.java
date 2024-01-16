import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][][] prefix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // nxm
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine()); // 조사 대상 영역의 개수
        prefix = new int[n+1][m+1][3]; // 0: 정글, 1: 바다, 2: 얼음
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                for (int l = 0; l < 3; l++) {
                    prefix[i][j][l] = prefix[i-1][j][l] + prefix[i][j-1][l] - prefix[i-1][j-1][l];
                }
                char type = str.charAt(j-1);
                if (type == 'J') { // 정글
                    prefix[i][j][0]++;
                } else if (type == 'O') { // 얼음
                    prefix[i][j][1]++;
                } else { // 바다
                    prefix[i][j][2]++;
                }
            }
        }
        while (k --> 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int jungle = prefix[c][d][0] - prefix[a-1][d][0] - prefix[c][b-1][0] + prefix[a-1][b-1][0];
            int sea = prefix[c][d][1] - prefix[a-1][d][1] - prefix[c][b-1][1] + prefix[a-1][b-1][1];
            int ice = prefix[c][d][2] - prefix[a-1][d][2] - prefix[c][b-1][2] + prefix[a-1][b-1][2];
            sb.append(jungle + " " + sea + " " + ice).append("\n");
        }

        System.out.println(sb);
    }
}