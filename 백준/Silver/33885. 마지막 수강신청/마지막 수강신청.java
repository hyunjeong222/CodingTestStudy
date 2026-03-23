import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static List<Course> courses = new ArrayList<>();
    static boolean[][] used = new boolean[5][24]; // 요일(0~4), 시간(0~23)
    static class Lecture {
        int day, hour;
        Lecture (int day, int hour) {
            this.day = day; this.hour = hour;
        }
    }
    static class Course {
        int credit;
        List<Lecture> lectures = new ArrayList<>();
        Course(int credit) {
            this.credit = credit;
        }
    }
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 후보 과목의 개수
        m = Integer.parseInt(st.nextToken()); // 수강해야 하는 최소 학점

        int c, s;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken()); // 얻는 학점
            s = Integer.parseInt(st.nextToken()); // 강의 횟수

            Course course = new Course(c);
            for (int j = 0; j < s; j++) {
                String d = st.nextToken(); // 강의 요일
                int h = Integer.parseInt(st.nextToken()); // 시작 시간
                course.lectures.add(new Lecture(dayToInt(d), h));
            }

            courses.add(course);
        }

        dfs(0, 0);

        System.out.println(flag ? "YES" : "NO");

        br.close();
    }

    private static void dfs(int idx, int sum) {
        if (flag) return;

        if (sum >= m) {
            flag = true;
            return;
        }

        if (idx == n) return;

        Course now = courses.get(idx);
        if (canTake(now)) { // 선택
            take(now);
            dfs(idx+1, sum+now.credit);
            untake(now);
        }

        dfs(idx+1, sum); // 선택 x
    }

    private static boolean canTake(Course c) {
        for (Lecture lec : c.lectures) {
            if (used[lec.day][lec.hour]) return false;
        }

        return true;
    }

    private static void take(Course c) {
        for (Lecture lec : c.lectures) {
            used[lec.day][lec.hour] = true;
        }
    }

    private static void untake(Course c) {
        for (Lecture lec : c.lectures) {
            used[lec.day][lec.hour] = false;
        }
    }

    private static int dayToInt(String d) {
        switch (d) {
            case "MON": return 0;
            case "TUE": return 1;
            case "WED": return 2;
            case "THU": return 3;
            case "FRI": return 4;
        }
        return -1;
    }
}