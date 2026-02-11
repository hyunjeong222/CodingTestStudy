import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, nStar, nBang;
    static StringBuilder sb  = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int idx = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            nStar = Integer.parseInt(st.nextToken());
            nBang = Integer.parseInt(st.nextToken());

            if (n == 0 && nStar == 0 && nBang == 0) break;;

            sb.append("Sequence set ").append(idx++).append("\n");

            dfs("", 0, 0, 0);
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void dfs(String cur, int len, int startCount, int bangCount) {
        if (len == n) {
            sb.append(cur).append("\n");
            return;
        }

        // * 추가 가능
        if (nStar > startCount) {
            dfs(cur+"*", len+1, startCount+1, 0);
        }

        // ! 추가 가능
        if (nBang > bangCount) {
            dfs(cur+"!", len+1, 0, bangCount+1);
        }
    }
}