import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long x = Long.parseLong(st.nextToken()); // 게임 횟수
        long y = Long.parseLong(st.nextToken()); // 이긴 게임

        int target = (int)((y*100)/x);

        // 1판이라도 졌으므로, 이후에 아무리 이겨도 100%가 안 되고, 승률은 99%에서 고정
        if (target >= 99) {
            System.out.println(-1);
            return;
        }

        int left = 0;
        int right = 1000000000;
        int ans = 0;
        while (left <= right) {
            int mid = (left+right)/2;
            int z = (int)((y+mid)*100/(x+mid));

            if (z <= target) {
                left = mid+1;
            } else right = mid -1;
        }

        System.out.println(left);

        br.close();
    }
}