import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int H = Integer.parseInt(split[0]);
		int W = Integer.parseInt(split[1]);
		int N = Integer.parseInt(split[2]);
		int M = Integer.parseInt(split[3]);
		
		int f = (H-1)/(N+1)+1; 
		int s = (W-1)/(M+1)+1;
		
		System.out.println(f*s);
	}
	
}