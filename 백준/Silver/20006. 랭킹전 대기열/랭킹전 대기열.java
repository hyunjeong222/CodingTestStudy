import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Player {
        int level; String name;
        public Player (int level, String name) {
            this.level = level; this.name = name;
        }
    }
    static public class Room {
        int level; ArrayList<Player> player;
        public Room(int level, ArrayList<Player> player) {
            this.level = level; this.player = player;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken()); // 플레이어 수
        int m = Integer.parseInt(st.nextToken()); // 방의 정원

        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();

            boolean enter = false;
            Room r = null;

            for (int j = 0; j < rooms.size(); j++) {
                r = rooms.get(j);
                if (r.player.size() >= m) { // 정원 초과
                    continue;
                }
                if (Math.abs(r.level - l) <= 10) {
                    r.player.add(new Player(l, n));
                    enter = true;
                    break;
                }
            }

            if (!enter) { // 들어갈 방이 없다면
                // 새로운 방 만들기
                rooms.add(new Room(l, new ArrayList<>(Arrays.asList(new Player(l, n)))));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Room r : rooms) {
            r.player.sort((a, b) -> a.name.compareTo(b.name));
            if (r.player.size() >= m) {
                sb.append("Started!").append("\n");
            } else {
                sb.append("Waiting!").append("\n");
            }

            for (Player u : r.player) {
                sb.append(u.level).append(" ").append(u.name).append("\n");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
}