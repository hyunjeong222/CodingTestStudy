import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[] tmpArr; // 순열을 만들 임시배열
    static boolean[] checked;
    static String ans;
    static int num, totalIdx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String line;
        while((line=br.readLine()) != null) {
            st = new StringTokenizer(line);
            String str = st.nextToken();
            num = Integer.parseInt(st.nextToken()); // 위치

            tmpArr = new char[str.length()];
            checked = new boolean[str.length()];

            totalIdx = 0; // 순열 번호
            dfs(str, 0);

            if (totalIdx < num) ans = "No permutation";
            sb.append(str).append(" ").append(num).append(" = ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(String str, int depth) {
        if (depth == str.length()) {
            totalIdx++; // 순열의 개수 세어주기
            if (totalIdx == num) {
                ans = new String(tmpArr);
            }
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!checked[i]) {
                checked[i] = true;
                tmpArr[depth] = str.charAt(i);
                dfs(str, depth+1);
                checked[i] = false;
            }
        }
    }
}