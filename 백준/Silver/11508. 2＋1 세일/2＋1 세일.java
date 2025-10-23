import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> milk = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            milk.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(milk, Collections.reverseOrder());
        // System.out.println(milk);

        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (i%3==2) continue;
            sum += milk.get(i);
        }

        System.out.println(sum);

        br.close();
    }
}