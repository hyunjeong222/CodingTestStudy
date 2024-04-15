import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int day = 365;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 일정의 개수

        StringTokenizer st;
        int s, e;
        int[] dayCnt = new int[day+1];
        while (n --> 0) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()); // 시작날짜
            e = Integer.parseInt(st.nextToken()); // 종료날짜
            for (int i = s; i <= e; i++) {
                dayCnt[i]++;
            }
        }
        int max = 0;
        int width = 0;
        int sum = 0;
        for (int i = 1; i <= day; i++) {
            if (dayCnt[i] == 0) {
                sum += (max*width);
                max = 0; width = 0;
                continue;
            }
            width++;
            max = Math.max(max, dayCnt[i]);
        }
        sum += (max*width);
        System.out.println(sum);
    }
}