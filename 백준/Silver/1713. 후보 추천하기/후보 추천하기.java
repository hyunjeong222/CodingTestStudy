import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Student implements Comparable<Student> {
        int num; int cnt; int time;
        public Student(int num, int cnt, int time) {
            this.num = num; this.cnt = cnt; this.time = time;
        }
        @Override
        public int compareTo(Student o) {
            if (this.cnt == o.cnt) return o.time-this.time;
            // ArrayList : 앞에서 삭제하는 것보다 뒤에서 삭제하는 것이 작은 시간 복잡도를 가진다.
            return o.cnt - this.cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int total = Integer.parseInt(br.readLine());

        Student[] students = new Student[total+1];
        ArrayList<Student> photos = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num;
        for (int i = 1; i <= total; i++) {
            num = Integer.parseInt(st.nextToken());

            if (students[num] != null) { // 사진이 존재
                students[num].cnt++;
            } else { // 사진이 존재 X
                Collections.sort(photos);
                if (photos.size() == n) {
                    Student del = photos.remove(n-1);
                    students[del.num] = null;
                }
                students[num] = new Student(num, 1, i);
                photos.add(students[num]);
            }
        }
        Collections.sort(photos, (o1, o2) -> o1.num - o2.num);
        StringBuilder sb = new StringBuilder();
        for (Student s : photos) {
            sb.append(s.num).append(" ");
        }

        System.out.println(sb.toString());
    }
}