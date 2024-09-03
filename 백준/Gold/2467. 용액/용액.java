import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // 산성 용액의 특성값 양수 (1 ~ 1,000,000,000)
    // 알칼리성 용액의 특성값 음수 (-1 ~ -1,000,000,000)
    // 같은 용액만으로 특성값 0에 가까운 혼합 용액을 만드는 경우도 존재
    // 산성 용액과 알칼리성 용액의 특성값이 정렬된 순서로 주어짐
    // 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = arr.length - 1;

        int max = Integer.MAX_VALUE;

        int ans1 = 0;
        int ans2 = 0;

        while (start < end) {
            int sum = arr[start] + arr[end];

            if(Math.abs(sum) < max) {
                ans1 = arr[start];
                ans2 = arr[end];
                max = Math.abs(sum);
            }else if (sum > 0) {
                end--;
            }else {
                start++;
            }
        }
        bw.append(ans1 + " " + ans2 + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
