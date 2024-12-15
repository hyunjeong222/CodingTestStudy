import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] strArr = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : strArr) {
            if (c >= 65 && c <= 90) { // 대문자 아스키코드
                sb.append(Character.toLowerCase(c));
            } else sb.append(Character.toUpperCase(c));
        }

        System.out.println(sb.toString());
    }
}