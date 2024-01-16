import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // nxm
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine()); // 조사 대상 영역의 개수
        int[][][] prefix = new int[n+1][m+1][3]; // 좌표, 정글 바다 얼음의 정보
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                for (int l = 0; l < 3; l++) {
                    prefix[i][j][l] = prefix[i-1][j][l] + prefix[i][j-1][l] - prefix[i-1][j-1][l];
                }
                switch (str.charAt(j-1)) {
                    case 'J' : // 정글
                        prefix[i][j][0]++;
                        break;
                    case 'O' : // 얼음
                        prefix[i][j][1]++;
                        break;
                    case 'I' : // 바다
                        prefix[i][j][2]++;
                        break;
                }
            }
        }
        while (k --> 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            for (int i = 0; i < 3; i++) {
                sb.append(prefix[c][d][i] - prefix[a-1][d][i] - prefix[c][b-1][i] + prefix[a-1][b-1][i]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}