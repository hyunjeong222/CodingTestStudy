import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        String b = st.nextToken();
        int ans = a.length();
        for (int i = 0; i < b.length()-a.length()+1; i++) {
            int tmp = 0;
            for (int j = 0; j < a.length(); j++) {
                if (a.charAt(j) != b.charAt(j+i)) tmp++;
            }
            if (ans > tmp) ans = tmp;
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}