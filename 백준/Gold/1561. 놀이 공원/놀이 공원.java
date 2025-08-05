import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long n;
    static int m;
    static int[] time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken()); // 아이들 수
        m = Integer.parseInt(st.nextToken()); // 놀이기구 종류

        st = new StringTokenizer(br.readLine());
        time = new int[m]; // 각 놀이기구의 운행 시간
        for (int i = 0; i < m; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        // 아이들 수보다 놀이기구 종류가 같거나 더 많다면
        // 첫번째 턴에 모든 아이들이 놀이기구를 탈 수 있음
        if (n <= m) {
            System.out.println(n);
            return;
        }

        long result = binarySearch();
        long child = getChildNumInTime(result-1);

        for (int i = 0; i < m; i++) {
            if (result%time[i] == 0) {
                child++;
            }
            if (child == n) {
                System.out.println(i+1);
                break;
            }
        }

        br.close();
    }

    private static long binarySearch() {
        long left = 0; // 최소 시간
        long right = n*30; // 최대 시간, 모든 아이들이 놀이기구를 탈 수 있는 최대 시간

        while (left <= right) {
            long mid = (left+right)/2;
            long childNum = getChildNumInTime(mid); // mid 동안 놀이기구를 탈 수 있는 아이들의 수

            if (childNum < n) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        return left;
    }

    private static long getChildNumInTime(long t) {
        long childNum = m;
        for (int i = 0; i < m; i++) {
            childNum += t/time[i];
        }

        return childNum;
    }
}