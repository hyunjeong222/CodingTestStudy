import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int min = 21;
    static int[] correct;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 시험지 개수
        k = Integer.parseInt(st.nextToken()); // 그룹의 수

        correct = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            correct[i] = Integer.parseInt(st.nextToken());
            min = Math.min(min, correct[i]);
        }

        int ans = minGroupSum();
        System.out.println(ans);
    }

    private static int minGroupSum() {
        int left = min;
        int right = 0;

        for (int num : correct) right += num;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (divideGroup(mid) < k) { // 그룹의 개수가 k보다 많다면
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        return right;
    }

    private static int divideGroup(int mid) {
        int sum = 0; int group = 0;

        for (int num : correct) {
            sum += num;
            if (sum >= mid) {
                group++;
                sum = 0;
            }
        }

        return group;
    }
}