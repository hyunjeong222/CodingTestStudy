import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static int zero, one, two;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][m];
        for (int i = 0; i < m; i++) {
            Arrays.fill(map[i], 1);
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            zero = Integer.parseInt(st.nextToken());
            one = Integer.parseInt(st.nextToken());
            two = Integer.parseInt(st.nextToken());
            
            sizeUp();
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < m; j++) {
                map[i][j] = Math.max(map[i-1][j-1], Math.max(map[i-1][j], map[i][j-1]));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void sizeUp() {
        for (int i = m-1; i > 0; i--) {
            if (zero != 0) zero--;
            else if (one != 0) {
                one--;
                map[i][0]+=1;
            } else {
                two--;
                map[i][0]+=2;
            }
        }
        for (int i = 0; i < m; i++) {
            if (zero != 0) zero--;
            else if (one != 0) {
                one--;
                map[0][i]+=1;
            } else {
                two--;
                map[0][i]+=2;
            }
        }
    }
}