import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][5];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0, leader = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < n; k++) {
                    if (map[i][j] == map[k][j] && i != k) {
                        set.add(k);
                    }
                }
            }

            if (set.size() > max) {
                leader = i;
                max = set.size();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(leader+1).append("\n");

        System.out.println(sb.toString());

        br.close();
    }
}