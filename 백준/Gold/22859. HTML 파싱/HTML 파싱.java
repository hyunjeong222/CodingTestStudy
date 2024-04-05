import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String html = br.readLine();

        Matcher p1 = Pattern.compile("div title=\"(\\w|_|\\s)*\"").matcher(html);
        Matcher p2 = Pattern.compile("<p>(\\w|\\s|</?[^p]>|</?\\w{2,}\\s?>|\\.)*</p>").matcher(html);

        HashMap<Integer, String> map = new HashMap<>();
        while (p1.find()) {
            map.put(p1.start(), "title : "+p1.group().split("\"")[1]+"\n");
        }
        while (p2.find()) {
            String tmp = p2.group().replaceAll("<[\\w\\s/]*>", "");
            map.put(p2.start(), tmp.replaceAll("\\s{2,}", " ")+"\n");
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for (int i : list) {
            sb.append(map.get(i));
        }
        System.out.println(sb);
    }
}