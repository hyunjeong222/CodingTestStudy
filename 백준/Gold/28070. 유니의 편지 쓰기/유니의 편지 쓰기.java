import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        int n = Integer.parseInt(br.readLine()); // 군대에 가는 유니의 친구 수
        StringTokenizer st;
        int year, month;
        int[] sum = new int[120002];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            // 입대 시기
            String[] strA = st.nextToken().split("-");
            year = Integer.parseInt(strA[0]);
            month = Integer.parseInt(strA[1]);
            sum[year*12+month]++;

            // 전역 시기
            String[] strB = st.nextToken().split("-");
            year = Integer.parseInt(strB[0]);
            month = Integer.parseInt(strB[1]);
            sum[year*12+month+1]--;
        }

        int max = 0;
        year = 0; month = 0;
        for (int i = 24000; i < sum.length; i++) {
            sum[i] += sum[i-1];
            if (sum[i] > max) {
                max = sum[i];
                year = i / 12;
                month = i % 12;
            }
        }

        if (month == 0) {
            year--;
            month = 12;
        }

        sb = new StringBuilder();
        if (month < 10) {
            sb.append(year).append("-0").append(month);
        } else {
            sb.append(year).append("-").append(month);
        }

        System.out.println(sb);
    }
}