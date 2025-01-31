import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        while (n --> 0) {
            list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list, Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (int i : list) {
            sb.append(i).append("\n");
        }
        System.out.println(sb.toString());
    }
}