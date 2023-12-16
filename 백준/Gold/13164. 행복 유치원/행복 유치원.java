import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 원생의 수
        int k = Integer.parseInt(st.nextToken()); // 조의 개수
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            list.add(arr[i]-arr[i-1]);
        }
        Collections.sort(list);
        
        int ans = 0;
        for (int i = 0; i < n-k; i++) {
            ans += list.get(i);
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
