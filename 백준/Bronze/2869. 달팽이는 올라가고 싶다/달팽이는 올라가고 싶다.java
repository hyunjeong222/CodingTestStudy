import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long v = Long.parseLong(st.nextToken());

        long dayDist = a-b; // 하루에 움직이는 거리
        long goal = v-b; // 최종 목적지
        // 나눠떨어진다는 것은 낮에 도착
        if (goal%dayDist == 0) System.out.println(goal/dayDist);
        // 나눠떨어지지 않는 것은 하루를 더 추가로 필요
        else System.out.println(goal/dayDist+1);
        
        br.close();
    }
}