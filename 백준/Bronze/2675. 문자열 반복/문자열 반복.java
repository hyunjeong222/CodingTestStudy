import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            String str = st.nextToken();
            for (int i = 0; i < str.length(); i++) {
                for (int j = 0; j < r; j++) {
                    sb.append(str.charAt(i));
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}