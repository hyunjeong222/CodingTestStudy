import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] snowHeight;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 앞마당의 길이
        m = Integer.parseInt(st.nextToken()); // 대회의 시간

        snowHeight = new int[n+2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            snowHeight[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, 0, 0);

        System.out.println(ans);
    }

    private static void dfs(int size, int pos, int time) {
        // 대회의 시간이 끝나거나, 눈덩이가 앞마당 끝에 도착한 경우
        if (time == m || pos >= n) {
            ans = Math.max(ans, size);
            return;
        }

        for (int i = 1; i <= 2; i++) {
            if (i == 1) { // 굴리는 경우
                dfs(size+snowHeight[pos+i], pos+i, time+1);
            } else { // 던지는 경우
                dfs((size/2)+snowHeight[pos+i], pos+i, time+1);
            }
        }
    }
}