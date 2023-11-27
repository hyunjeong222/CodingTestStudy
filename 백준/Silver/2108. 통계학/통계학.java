import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine()); // 수의 개수
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        sb.append(Math.round(sum/n)).append("\n");

        Arrays.sort(arr);

        sb.append(arr[n/2]).append("\n");

        int[] plus = new int[4002];
        int[] minus = new int[4001];
        for (int i = 0; i < n; i++) {
            if (arr[i] < 0) minus[Math.abs(arr[i])]++;
            else plus[arr[i]]++;
        }

        ArrayList<Integer> list = new ArrayList<>();

        int max = 0;
        for (int i = 0; i < plus.length; i++) {
            max = Math.max(max, plus[i]);
        }
        for (int i = 0; i < minus.length; i++) {
            max = Math.max(max, minus[i]);
        }

        for (int num : arr) {
            if (num < 0) {
                if (minus[Math.abs(num)] == max && !list.contains(num)) list.add(num);
            } else {
                if (plus[num] == max && !list.contains(num)) list.add(num);
            }
        }

        if (list.size() >= 2) sb.append(list.get(1)).append("\n");
        else sb.append(list.get(0)).append("\n");

        sb.append(arr[n-1]-arr[0]).append("\n");

        System.out.println(sb.toString());
    }
}