import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 교실
        int k = Integer.parseInt(st.nextToken()); // 학생

        int[] arr = new int[n+1];
        long[] prefix = new long[n+1]; // 학생의 목적지가 i번일 경우 누적 점수 기여도
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefix[i] = arr[i] + prefix[i-1];
        }
        // System.out.println(Arrays.toString(prefix));

        // 학생이 i번 교실을 목적지로 정하면,
        // 해당 학생은 prefix[i]만큼의 점수 기여를 한다
        Long[] pf = new Long[n];
        for (int i = 0; i < n; i++) {
            pf[i] = prefix[i+1];
        }
        Arrays.sort(pf, Collections.reverseOrder());

        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += pf[i];
        }

        System.out.println(sum);

        br.close();
    }
}