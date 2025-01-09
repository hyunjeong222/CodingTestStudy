import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] start = new int[26]; // 시작점
        int[] end = new int[26]; // 끝점

        String str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'A';
            if (start[idx] == 0) start[idx] = i+1;
            else end[idx] = i+1;
        }

        int ans = 0;
        for (int i = 0; i < 26; i++) { // 소
            for (int j = 0; j < 26; j++) { // 다른 소
                if (start[i] < start[j] && end[i] < end[j] && start[j] < end[i]) ans++;
            }
        }

        System.out.println(ans);
    }
}