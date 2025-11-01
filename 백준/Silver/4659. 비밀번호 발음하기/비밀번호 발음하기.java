import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());

        while (true) {
            String pw = br.readLine();

            if (pw.equals("end")) {
                break;
            }

            if (check(pw)) {
                sb.append("<").append(pw).append(">").append(" is acceptable.").append("\n");
            } else {
                sb.append("<").append(pw).append(">").append(" is not acceptable.").append("\n");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static boolean check(String pw) {
        int cnt = 0; // 같은 글자 개수
        char prev = '.';
        boolean flag = false;
        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);

            // 모음(a,e,i,o,u) 하나를 반드시 포함
            if (isVowel(c)) flag = true;

            // 같은 종류(모음, 자음) 몇 번 나왔는지 카운트
            if (isVowel(c) != isVowel(prev)) cnt = 1;
            else cnt++;

            // 모음이 3개 혹은 자음이 3개 연속으로 오면 안됨
            // 같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용
            if (cnt > 2 || (prev == c && (c != 'e' && c != 'o'))) {
                flag = false;
                break;
            }

            prev = c;
        }

        return flag;
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}