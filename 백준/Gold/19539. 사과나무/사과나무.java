import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tmp;
        int sum = 0; // 사과 나무 높이 총합
        int num = 0; // 홀수 개수
        for (int i = 0; i < n; i++) {
            tmp = Integer.parseInt(st.nextToken());
            sum += tmp;
            if (tmp%2==1) num++;
        }
        if (sum%3==0 && num <= (sum/3)) {
            System.out.println("YES");
        } else  System.out.println("NO");
    }
}