import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//약수의 순서를 저장할 변수 선언
		int count = 0;
		//K번째 약수를 저장할 변수 선언
		int result = 0;
		
		// 약수 구하는 반복문
		for(int i = 1; i <= N; i++) {
			//약수가 하나 나올떄마다 count에 +1
			if(N % i == 0) {
				count++;
			}
			//count가 K와 같아지면 곧 그 약수가 K번째 약수란 뜻이기에 result에 저장 후 반복문 종료
			if(count == K) {
				result = i;
				break;
			}
		}
		System.out.println(result);
	}

}