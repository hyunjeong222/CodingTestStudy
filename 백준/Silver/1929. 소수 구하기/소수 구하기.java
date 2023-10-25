import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        boolean[] checked = new boolean[n+1];
        checked[0] = checked[1] = true;
        
        for (int i = 2; i <= Math.sqrt(n+1); i++) {
            if (checked[i]) continue;
            for (int j = i * i; j < checked.length; j += i) {
                checked[j] = true;
            }
        }
        for (int i = m; i <= n; i++) {
            if (!checked[i]) {
                bw.append(i + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}