import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ip = br.readLine();
        if (ip.contains("::")) {
            ip = ip.replace("::", ":zero:");
        }
        String[] group = ip.split(":");
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < group.length; i++) {
            String str = group[i];
            if (str.equals("")) continue;
            while (str.length() < 4) {
                str = "0" + str;
            }
            list.add(str);
        }

        String[] ans = new String[8];
        int len = 8 - list.size() + 1;
        int idx = 0;
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);

            if (str.equals("zero")) {
                while (len --> 0) {
                    ans[idx] = "0000";
                    idx++;
                }
            } else {
                ans[idx] = str;
                idx++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            if (i == ans.length-1) sb.append(ans[i]);
            else sb.append(ans[i]).append(":");
        }
        System.out.println(sb);
    }
}