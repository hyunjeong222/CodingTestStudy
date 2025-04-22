import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] A = new int[n+1];
        int[] sum = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1]+A[i];
        }

        HashMap<Integer, Integer> map = new HashMap<>(); // 누적합 sum[i] 값, 등장 횟수
        map.put(0, 1); // sum[i] 자체가 K인 경우

        long cnt = 0;
        for (int i = 1; i <= n; i++) {
            cnt += map.getOrDefault(sum[i]-k, 0);
            map.put(sum[i], map.getOrDefault(sum[i], 0)+1);
        }

        System.out.println(cnt);

        br.close();
    }
}