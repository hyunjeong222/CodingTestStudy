import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        boolean[][] map = new boolean[101][101];
        int ans = 0; // 넓이
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int j = x; j < x+10; j++) { // 가로, 세로 크기가 각각 10
                for (int k = y; k < y+10; k++) {
                    if (!map[j][k]) {
                        map[j][k] = true;
                        ans++;
                    }
                }
            }
        }

        System.out.println(ans);

        br.close();
    }
}