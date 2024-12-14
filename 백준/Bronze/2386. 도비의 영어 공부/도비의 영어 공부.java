import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        char alpha;
        char[] strArr;
        int ans;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            alpha = st.nextToken().charAt(0);
            if(alpha == '#') break;

            String str = "";
            while (st.hasMoreTokens()) str += st.nextToken();

            strArr = str.toLowerCase().toCharArray();

            ans = 0;
            for (char c : strArr) {
                if (alpha == c) ans++;
            }

            sb.append(alpha).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());

    }
}