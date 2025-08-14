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