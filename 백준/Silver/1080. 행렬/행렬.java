import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        A = new int[n][m];
        B = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                A[i][j] = str.charAt(j)-'0';
            }
        }
        // System.out.println(Arrays.deepToString(A));

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                B[i][j] = str.charAt(j)-'0';
            }
        }
        // System.out.println(Arrays.deepToString(B));

        int cnt = 0; // A를 B로 만들기 위한 최소 연산 횟수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] != B[i][j] && i+2 < n && j+2 < m) {
                    for (int r = i; r < i+3; r++) {
                        for (int c = j; c < j+3; c++) {
                            A[r][c] = A[r][c] == 0 ? 1 : 0;
                        }
                    }
                    // System.out.println(Arrays.deepToString(A));
                    cnt++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] != B[i][j]) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(cnt);

        br.close();
    }
}