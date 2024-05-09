import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken()); // 세로
        int w = Integer.parseInt(st.nextToken()); // 가로

        int[] height = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        int ans = 0;
        for (int i = 1; i < w-1; i++) {
            left = 0;
            right = 0;

            for (int j = 0; j < i; j++) {
                left = Math.max(left, height[j]);
            }

            for (int j = i+1; j < w; j++) {
                right = Math.max(right, height[j]);
            }

            if (left > height[i] && right > height[i]) {
                ans += Math.min(left, right) - height[i];
            }
        }

        System.out.println(ans);

    }
}