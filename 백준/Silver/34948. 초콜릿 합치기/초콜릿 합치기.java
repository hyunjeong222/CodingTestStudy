import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Chocolate {
        int h, w;
        Chocolate(int h, int w) {
            this.h = h;
            this.w = w;
        }
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 초콜릿의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] height = new int[n]; // 세로
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] width = new int[n]; // 가로
        for (int i = 0; i < n; i++) {
            width[i] = Integer.parseInt(st.nextToken());
        }

        Chocolate[] chocolates = new Chocolate[n];
        for (int i = 0; i < n; i++) {
            chocolates[i] = new Chocolate(height[i], width[i]);
        }
        // 세로 길이 기준 내림차순 정렬
        Arrays.sort(chocolates, (a, b) -> b.h - a.h);
        // 5 5 3 2 1
        // 2 1 2 1 1

        // 앞에서부터 가로 길이 누적합
        long[] prefixSum = new long[n];
        prefixSum[0] = chocolates[0].w;
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i-1]+chocolates[i].w;
        }
        // System.out.println(Arrays.toString(prefixSum));

        long ans = 0;
        for (int i = 0; i < n; i++) {
            long area = (long) chocolates[i].h*prefixSum[i];
            ans = Math.max(ans, area);
        }

        System.out.println(ans);

        br.close();
    }
}