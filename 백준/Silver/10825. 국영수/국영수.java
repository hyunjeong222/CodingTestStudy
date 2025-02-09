import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Info implements Comparable<Info> {
        String name; int language; int english; int math;
        public Info(String name, int language, int english, int math) {
            this.name = name;
            this.language = language; this.english = english; this.math = math;
        }
        public String getName() {
            return name;
        }
        @Override
        public int compareTo(Info o) {
            if (this.language == o.language) {
                if (this.english == o.english) {
                    if (this.math == o.math) {
                        return this.getName().compareTo(o.getName());
                    }
                    return o.math - this.math;
                }
                return this.english - o.english;
            }
            return o.language - this.language;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Info> list = new ArrayList<>();
        StringTokenizer st;
        String name;
        int l, e, m;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            name = st.nextToken();
            l = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            list.add(new Info(name, l, e, m));
        }
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (Info i : list) {
            sb.append(i.name).append("\n");
        }

        System.out.println(sb.toString());
    }
}