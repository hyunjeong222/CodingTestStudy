import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	static int n;
	static boolean[] check;
	static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		
		br.close();
		
		isPrime(n);
		
		int start = 0;
		int end = 0;
		int sum = 0; 
		int result = 0; 
		
		while (true) {
			if (sum >= n) {
				sum -= list.get(start);
				start++;
			} else if (list.size() == end) {
				break;
			} else {
				sum += list.get(end);
				end++;
			}
			if (sum == n) result++;
		}
		
		bw.append(result + "\n");
		
		bw.flush();
		bw.close();
	}
	
	private static void isPrime(int n) {
		check = new boolean[n+1];
		for (int i = 2; i <= n; i++) {
			if (check[i] == true) continue;
			for (int j = i*2; j <= n; j+=i) {
				check[j] = true;
			}
		}
		for (int i = 2; i < check.length; i++) {
			if (!check[i]) {
				list.add(i);
			}
		}
	}
}
