import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int number;
		
		for (int i=0; i<n; i++) {
			number = Integer.parseInt(br.readLine());
			dfs(number, 1, 1, 1, 0, "1");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
    static void dfs(int max, int now, int num, int sign, int sum, String str) {
		if(max == now) {
			sum = sum + (num * sign);
			if (sum == 0) {
				sb.append(str +"\n");
			}
			return;
		}
		dfs(max, now+1, num*10+(now+1), sign, sum, str+ " " +String.valueOf(now+1)); 
		dfs(max, now+1, now+1, 1, sum + (num*sign), str+ "+" +String.valueOf(now+1));
		dfs(max, now+1, now+1, -1, sum + (num*sign), str+ "-" +String.valueOf(now+1));
	}
}