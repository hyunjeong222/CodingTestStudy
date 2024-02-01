import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int p = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] arr = new int[20];
        while (p-->0){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            for (int i = 0; i < 20; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int ans = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr[j] > arr[i]) ans++;
                }
            }
            bw.append(t + " " + ans + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}