import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static String[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t --> 0) {
            n = Integer.parseInt(br.readLine());
            arr = new String[n];
            for (int i = 0; i < n; i++) {
                arr[i] = br.readLine();
            }
            Arrays.sort(arr);
            boolean flag = isInclude();

            sb.append(flag ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }

    private static boolean isInclude() {
        for (int i = 0; i < n-1; i++) {
            // startsWith : 대상 문자열이 특정 문자 또는 문자열로 시작하는지 체크하는 함수
            if (arr[i+1].startsWith(arr[i])) return false;
        }
        return true;
    }
}