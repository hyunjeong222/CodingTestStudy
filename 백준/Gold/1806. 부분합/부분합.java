import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int s;
	static int[] arr;
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		br.close();
		
		int start = 0;
		int end = 0;
		int sum = 0;
		int length = 100001;
		
		while (true) {
			if (sum >= s ) {
				sum -= arr[start];
				length = Math.min(length, (end - start));
				start++;
			} else if (end == n) {
				break;
			} else {
				sum += arr[end];
				end++;
			}
		}
		
		if (length == 100001) {
			 bw.append("0" + "\n");
		} else {
			bw.append(length + "\n");
		}
		bw.flush();
		bw.close();
	}
}
