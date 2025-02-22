import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Info implements Comparable<Info>{
        String name; int day; int month; int year;
        public Info(String name, int day, int month, int year) {
            this.name = name; this.day = day; this.month = month; this.year = year;
        }
        @Override
        public int compareTo(Info o) {
            if (this.year == o.year) {
                if (this.month == o.month) {
                    return this.day - o.day;
                }
                return this.month - o.month;
            }
            return this.year - o.year;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        String name; int day; int month; int year;
        ArrayList<Info> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            name = st.nextToken();
            day = Integer.parseInt(st.nextToken());
            month = Integer.parseInt(st.nextToken());
            year = Integer.parseInt(st.nextToken());

            list.add(new Info(name, day, month, year));
        }
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        sb.append(list.get(list.size()-1).name).append("\n");
        sb.append(list.get(0).name).append("\n");

        System.out.println(sb.toString());
    }
}