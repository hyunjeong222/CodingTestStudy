import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[10];
        String n = br.readLine();
        int num = 0;
        for (int i = 0; i < n.length(); i++) {
            num = n.charAt(i)-'0';
            if (num == 9) { // 6과 9는 서로 뒤집어서 사용 가능
                num = 6;
            }
            arr[num]++;
        }

        arr[6] = arr[6]/2 + (arr[6]%2); // 홀수면 하나 더 필요

        Arrays.sort(arr);
        System.out.println(arr[9]);
    }
}