import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int bird = 1; // 하늘을 향해 날아가는 새의 수
        int time = 0;
        while (n >= 1) {
            if (n < bird) { // 앉아있는 새의 수가 작을 때
                bird = 1; // 1부터 게임 다시 시작
            }
            n -= bird;
            bird++;
            time++;
        }

        System.out.println(time);

        br.close();
    }
}