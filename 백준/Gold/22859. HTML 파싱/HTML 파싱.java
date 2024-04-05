import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[] html;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        html = br.readLine().split("<p>|</p>|</div>");
        parsing();
        System.out.println(sb);
    }

    private static void parsing() {
        for (String str : html) {
            if (str.isEmpty()) continue;

            if (str.contains("<div ")) {
                String tmp = str.split("\"")[1];
                sb.append("title : ").append(tmp).append("\n");
            } else {
                String tmp = str.replaceAll("<[\\w\\s/]*>", "");
                tmp = tmp.replaceAll("\\s{2,}", " ");
                sb.append(tmp).append("\n");
            }
        }
    }
}