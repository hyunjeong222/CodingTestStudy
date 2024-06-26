import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 삼각형의 크기
        StringTokenizer st;
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = n-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                arr[i-1][j] += Math.max(arr[i][j], arr[i][j+1]);
            }
        }

        System.out.println(arr[0][0]);
    }
}