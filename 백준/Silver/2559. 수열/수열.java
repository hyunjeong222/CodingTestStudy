import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int k;
	static int[] arr;
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		int start = 0;
		int end = 0;
		int max = Integer.MIN_VALUE;
		int sum = arr[0];
		int day = 1;
		while (end < n) {
			if (day == k) {
				max = Math.max(max, sum);
				sum -= arr[start++];
				day--;
			} else {
				end++;
				if (end < n) {
					sum += arr[end];
				}
				day++;
			}
		}
		bw.append(max + "\n");
		bw.flush();
		bw.close();
	}
}
