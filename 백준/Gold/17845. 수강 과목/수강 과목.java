import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Pos> list;
    static public class Pos {
        int cost; int value;
        public Pos(int cost, int value) {
            this.cost = cost; this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.add(new Pos(cost, value));
        }

        int[][] dp = new int[list.size()+1][n+1];
        for (int i = 1; i <= list.size(); i++) {
            for (int j = 1; j <= n; j++) {
                if (list.get(i-1).cost <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-list.get(i-1).cost]+list.get(i-1).value);
                } else dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(dp[list.size()][n]);
    }
}