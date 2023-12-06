import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine(); // a와 b만으로 이루어진 문자열
        int ans = Integer.MAX_VALUE;
        int aCount = 0;
        int l = s.length();
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == 'a') aCount++;
        }
        for (int i = 0; i < l; i++) {
            int bCount = 0;
            for (int j = i; j < i+aCount; j++) {
                int idx = j % l;
                if (s.charAt(idx) == 'b') bCount++;
            }
            ans = Math.min(ans, bCount);
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}