import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static public class pos implements Comparable<pos>{
        int age; String name;
        public pos (int age, String name) {
            this.age = age;
            this.name = name;
        }
        @Override
        public int compareTo(pos o) {
            return this.age - o.age;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayList<pos> list = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new pos(Integer.parseInt(st.nextToken()), st.nextToken()));
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).age).append(" ").append(list.get(i).name);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}