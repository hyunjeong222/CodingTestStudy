import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(t --> 0) {
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                sb.append(a+b).append(" ").append(a*b).append("\n");
            }
        }
		System.out.println(sb.toString());
		
		br.close();
	}
}