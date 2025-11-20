import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 돌의 개수

        // StringTokenizer st = new StringTokenizer(br.readLine());

        System.out.println((n%2==0) ? "CY" : "SK");


        br.close();
    }
}