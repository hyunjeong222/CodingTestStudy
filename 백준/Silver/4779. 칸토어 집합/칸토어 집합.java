import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            n = Integer.parseInt(str);
            sb = new StringBuilder();
            int len = (int)Math.pow(3, n);
            for (int i = 0; i < len; i++) {
                sb.append("-");
            }
            recur(0, len);
            System.out.println(sb);
        }

        br.close();
    }

    private static void recur(int start, int size) {
        if (size == 1) return;

        int newSize = size/3;
        // 3등분한 문자열의 2번째 부분 공백처리
        for (int i = start+newSize; i < start+2*newSize; i++) {
            sb.setCharAt(i, ' ');
        }

        recur(start, newSize);
        recur(start+2*newSize, newSize);
    }
}