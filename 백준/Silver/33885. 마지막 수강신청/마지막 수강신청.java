import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static List<Course> courses = new ArrayList<>();
    static boolean[][] checked = new boolean[5][24]; // 요일(0~4), 시간(0~23)
    static class Lecture {
        int day; int hour;
        Lecture (int day, int hour) {
            this.day = day; this.hour = hour;
        }
    }
    static class Course {
        int credit;
        List<Lecture> lectures = new ArrayList<>();
        Course (int credit) {
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
            c = Integer.parseInt(st.nextToken()); // 얻을 수 있는 학점
            s = Integer.parseInt(st.nextToken()); // 진행되는 강의 횟수

            Course course = new Course(c); // credit
            for (int j = 0; j < s; j++) {
                String day = st.nextToken(); // 강의가 열리는 요일
                int hour = Integer.parseInt(st.nextToken()); // 강의가 열리는 시각

                course.lectures.add(new Lecture(dayToInt(day), hour));
            }

            courses.add(course);
        }

        dfs(0, 0);

        System.out.println(flag ? "YES" : "NO");

        br.close();
    }

    private static void dfs(int idx, int sum) {
        if (flag) return;

        // m학점 이상 수강 가능
        if (sum >= m) {
            flag = true;
            return;
        }

        // 모든 탐색 종료
        if (idx == n) return;

        Course now = courses.get(idx);

        // 선택할 때
        if (canTake(now)) {
            take(now);
            dfs(idx+1, sum+now.credit);
            untake(now);
        }

        // 선택 안 할 때
        dfs(idx+1, sum);
    }

    // 시간 겹치는지 체크
    private static boolean canTake(Course c) {
        for (Lecture l : c.lectures) {
            if (checked[l.day][l.hour]) return false;
        }

        return true;
    }

    // 시간 표시
    private static void take(Course c) {
        for (Lecture l : c.lectures) {
            checked[l.day][l.hour] = true;
        }
    }

    // 되돌리기
    private static void untake(Course c) {
        for (Lecture l : c.lectures) {
            checked[l.day][l.hour] = false;
        }
    }

    private static int dayToInt(String day) {
        switch (day) {
            case "MON" : return 0;
            case "TUE" : return 1;
            case "WED" : return 2;
            case "THU" : return 3;
            case "FRI" : return 4;
        }

        return -1;
    }
}