import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();

        //(0 < A,B < 10^10000) : 자료형 BigDecimal 을 사용
        BigDecimal A = new BigDecimal(st.nextToken());
        BigDecimal B = new BigDecimal(st.nextToken());

        //BigDecimal는 숫자를 정밀하게 저장하표현할 수 있는 객체
        //단점 :  느린 속도 & 복잡한 사용법
        System.out.println(String.valueOf(A.add(B))); //A+B식으로 하지 못한다. add() 메서드를 사용해야함
    }
}