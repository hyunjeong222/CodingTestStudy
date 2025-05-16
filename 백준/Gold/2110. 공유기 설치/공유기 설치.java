import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 집의 개수
        int c = Integer.parseInt(st.nextToken()); // 공유기 개수

        int[] home = new int[n];
        for (int i = 0; i < n; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(home);

        int left = 1;
        int right = home[n-1];
        while (left <= right) {
            int mid = (left + right) / 2;

            //  공유기 설치
            int pos = 0; // 공유기 설치 위치, 첫번째는 무조건 설치한다고 가정
            int cnt = 1;
            for (int i = 1; i < n; i++) {
                // 첫번째에서 mid 이상 거리를 두고 있는 집 중, 가장 가까운 집에 공유기 설치
                if (home[i]-home[pos] >= mid) {
                    pos = i;
                    cnt++;
                }
            }

            if (cnt >= c) left = mid+1;
            else right = mid-1;
        }

        System.out.println(right);

        br.close();
    }
}