import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine(); // 문자열
        int ans = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (isPalind(s.substring(i))) break;
            ans++;
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isPalind(String s) {
        int left = 0;
        int right = s.length()-1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}