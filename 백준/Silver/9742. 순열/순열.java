import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[] tmpArr; // 순열을 만들 임시배열
    static boolean[] checked;
    static String str, ans;
    static int num, totalIdx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String line;
        while((line=br.readLine()) != null) {
            st = new StringTokenizer(line);
            str = st.nextToken();
            num = Integer.parseInt(st.nextToken()); // 위치

            tmpArr = new char[str.length()];
            checked = new boolean[str.length()];

            totalIdx = 0; // 순열 번호
            dfs(0);

            if (totalIdx < num) ans = "No permutation"; // 찾고자 하는 위치에 순열이 없는 경우
            sb.append(str).append(" ").append(num).append(" = ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int depth) {
        if (depth == str.length()) { // 순열 다 만들었으면
            totalIdx++; // 순열의 개수 세어주기
            if (totalIdx == num) { // 순열의 개수가 찾고자 하는 위치에 존재
                ans = new String(tmpArr); // 배열에 있는 문자들 String으로 변환
            }
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!checked[i]) {
                checked[i] = true;
                tmpArr[depth] = str.charAt(i); // 문자 조합해서 순열 만들기
                dfs(depth+1);
                checked[i] = false;
            }
        }
    }
}