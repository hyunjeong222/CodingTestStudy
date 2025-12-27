import java.io.*;
import java.util.*;

public class Main {

    static class Chocolate {
        int h, w;
        Chocolate(int h, int w) {
            this.h = h;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] H = new int[N];
        int[] W = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) H[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) W[i] = Integer.parseInt(st.nextToken());

        Chocolate[] arr = new Chocolate[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Chocolate(H[i], W[i]);
        }

        // 1. 세로 길이 오름차순 정렬
        Arrays.sort(arr, Comparator.comparingInt(a -> a.h));

        // 2. 뒤에서부터 가로 길이 누적합
        long[] suffixSum = new long[N];
        suffixSum[N - 1] = arr[N - 1].w;
        for (int i = N - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + arr[i].w;
        }

        // 3. 각 높이를 기준으로 넓이 계산
        long answer = 0;
        for (int i = 0; i < N; i++) {
            long area = (long) arr[i].h * suffixSum[i];
            answer = Math.max(answer, area);
        }

        System.out.println(answer);
    }
}