import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> map = new HashMap<>();
        String[] nameMap = new String[n+1];
        for (int i = 1; i <= n; i++) {
            String name = br.readLine();
            map.put(name, i);
            nameMap[i] = name;
        }
        while (m --> 0) {
            String str = br.readLine();
            if (checkNum(str)) {
                sb.append(nameMap[Integer.parseInt(str)]).append("\n");
            } else {
                sb.append(map.get(str)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean checkNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) return true;
        }
        return false;
    }
}