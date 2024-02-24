import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //
        String now = br.readLine();
        String want = br.readLine();

        int[] nowArr1 = new int[n];
        int[] nowArr2 = new int[n];
        int[] wantArr = new int[n];
        for (int i = 0; i < n; i++) {
            nowArr1[i] = now.charAt(i)-'0';
            nowArr2[i] = now.charAt(i)-'0';
            wantArr[i] = want.charAt(i)-'0';
        }
        
        nowArr1[0] = 1-nowArr1[0];
        nowArr1[1] = 1-nowArr1[1];

        int ans1 = 1; int ans2 = 0; int ans3 = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            if (nowArr1[i-1] != wantArr[i-1]) {
                nowArr1[i-1] = 1 - nowArr1[i-1];
                nowArr1[i] = 1 - nowArr1[i];
                ans1++;
                if (i != n-1) {
                    nowArr1[i+1] = 1 - nowArr1[i+1];
                }
            }
            if (nowArr2[i-1] != wantArr[i-1]) {
                nowArr2[i-1] = 1 - nowArr2[i-1];
                nowArr2[i] = 1 - nowArr2[i];
                ans2++;
                if (i != n-1) {
                    nowArr2[i+1] = 1 - nowArr2[i+1];
                }
            }
        }
        
        if (nowArr1[n-1] != wantArr[n-1]) ans1 = ans3;
        if (nowArr2[n-1] != wantArr[n-1]) ans2 = ans3;

        if (ans1 == ans3 && ans2 == ans3) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(ans1, ans2));
        }
    }
}