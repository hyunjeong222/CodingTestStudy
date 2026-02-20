import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int T = 1000 - Integer.parseInt(input);
		int[] changes = {500, 100, 50, 10, 5, 1};
		int cnt = 0;
		
		for(int i : changes) {
			cnt += T / i;
			T %= i;
		}
		
		System.out.println(cnt);
	}
}