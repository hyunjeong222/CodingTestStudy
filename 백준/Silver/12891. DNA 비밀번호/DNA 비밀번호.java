import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] my;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()); // 문자열의 길이
        int p = Integer.parseInt(st.nextToken()); // 부분 문자열의 길이
        char[] dna = br.readLine().toCharArray(); // DNA 문자열
        arr = new int[4]; // A, C, G, T
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        my = new int[4];
        for (int i = 0; i < p; i++) {
            switch (dna[i]) {
                case 'A' : my[0]++; break;
                case 'C' : my[1]++; break;
                case 'G' : my[2]++; break;
                case 'T' : my[3]++; break;
            }
        }
        int ans = 0;
        if (check()) ans++;
        int i = 0;
        for (int j = p; j < s; j++) {
            i = j - p;
            switch (dna[i]) {
                case 'A' : my[0]--; break;
                case 'C' : my[1]--; break;
                case 'G' : my[2]--; break;
                case 'T' : my[3]--; break;
            }
            switch (dna[j]) {
                case 'A' : my[0]++; break;
                case 'C' : my[1]++; break;
                case 'G' : my[2]++; break;
                case 'T' : my[3]++; break;
            }
            if (check()) ans++;
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean check() {
        for (int i = 0; i < 4; i++) {
            if (arr[i] > my[i]) return false;
        }
        return true;
    }
}