import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int r, c, q;
    static int[][] map;
    static int[][] prefix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        map = new int[r+1][c+1];
        prefix = new int[r+1][c+1];
        for (int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                prefix[i][j] = map[i][j] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
            }
        }
        while (q --> 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int ans = prefix[x][y] - prefix[i-1][y] - prefix[x][j-1] + prefix[i-1][j-1];
            int cnt = (x-i+1)*(y-j+1);
            sb.append(ans/cnt).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}