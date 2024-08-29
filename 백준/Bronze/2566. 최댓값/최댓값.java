import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[][] map = new int[10][10];
        StringTokenizer st;
        int max = 0;
        int r = 0, c = 0;
        for (int i = 1; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (i == 1 && j == 1) max = map[i][j];
                else if (max <= map[i][j]) {
                    max = map[i][j];
                    r = i; c = j;
                }
            }
        }
        sb.append(max).append("\n");
        sb.append(r).append(" ").append(c);

        System.out.println(sb);
    }
}