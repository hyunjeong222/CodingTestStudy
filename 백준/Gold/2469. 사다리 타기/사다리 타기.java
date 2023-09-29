import java.io.*;

public class Main {
    static int k, n, lineIndex;
    static char[] results, origins;
    static char[][] ladder;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        k = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());

        origins = new char[k];
        for (int i = 0; i < k; i++) {
            origins[i] = (char)('A' + i);
        }

        results = new char[k];
        String p = br.readLine();
        for (int i = 0; i < p.length(); i++) {
            results[i] = p.charAt(i);
        }
        
        ladder = new char[n][k-1];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < k-1; j++) {
                ladder[i][j] = s.charAt(j);
                if (s.charAt(j) == '?') {
                    lineIndex = i;
                    continue;
                }
            }
        }
        
        for (int i = 0; i < lineIndex; i++) {
            for (int j = 0; j < k-1; j++) {
                if (ladder[i][j] == '-') {
                    char tmp = origins[j];
                    origins[j] = origins[j+1];
                    origins[j+1] = tmp;
                }
            }
        }

        for (int i = n-1; i > lineIndex; i--) {
            for (int j = 0; j < k-1; j++) {
                if (ladder[i][j] == '-') {
                    char tmp = results[j];
                    results[j] = results[j+1];
                    results[j+1] = tmp;
                }
            }
        }

        sb = new StringBuilder();
        for (int i = 0; i < k-1; i++) {
            if (origins[i] == results[i]) sb.append('*');
            else if (origins[i] == results[i+1]) {
                sb.append('-');
                char tmp = origins[i];
                origins[i] = origins[i+1];
                origins[i+1] = tmp;
            } else {
                sb = new StringBuilder();
                for (int j = 0; j < k-1; j++) sb.append('x');
                break;
            }
        }
        bw.append(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}