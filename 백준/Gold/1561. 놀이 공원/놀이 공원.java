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

        // 아이들 수가 놀이기구 종류보다 작다면
        // 모두 바로 탑승 가능
        if (n <= m) {
            System.out.println(n);
            return;
        }

        long min = binarySearch(); // n번째 아이까지 탑승 가능한 최소 시간
        long child = getChildNumInTime(min-1); // n번째 아이가 탑승하기 직전까지 탑승한 아이 수

        for (int i = 0; i < m; i++) {
            if (min%time[i] == 0) { // 놀이기구 i는 새 아이를 태울 준비가 된 상태
                child++; // 한 명 탑승
            }
            if (child == n) { // 모두 탑승했다면
                System.out.println(i+1); // 마지막 아이가 타게 되는 놀이기구의 번호 출력
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
        long childNum = m; // 0분에 아이들이 차례대로 m개의 놀이기구 탑승
        for (int i = 0; i < m; i++) {
            // 각 놀이기구 당 t분 동안 탈 수 있는 인원 수 누적
            childNum += t/time[i];
        }

        return childNum;
    }
}