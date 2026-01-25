import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int count = 0; //창문이 열려있는 개수
        for(int i = 1; i * i <= N; i++) { //제곱수만 카운트
            count++;
        }

        System.out.println(count);

        br.close();
    }
}