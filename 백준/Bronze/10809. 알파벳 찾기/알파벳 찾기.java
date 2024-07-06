import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] arr = new int[26];
        boolean[] checked = new boolean[26];
        Arrays.fill(arr, -1);
        for (int i = 0; i < str.length(); i++) {
            if (!checked[str.charAt(i)-97]) {
                arr[str.charAt(i)-97] = i;
            }
            checked[str.charAt(i)-97] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}