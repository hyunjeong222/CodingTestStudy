import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, String> map = new HashMap<>();
        map.put("black","0");
        map.put("brown", "1");
        map.put("red", "2");
        map.put("orange", "3");
        map.put("yellow", "4");
        map.put("green", "5");
        map.put("blue", "6");
        map.put("violet", "7");
        map.put("grey", "8");
        map.put("white", "9");

        String color1 = br.readLine();
        String color2 = br.readLine();
        String color3 = br.readLine();

        long ans = Long.parseLong(map.get(color1) + map.get(color2));
        ans *= Math.pow(10.0, Long.parseLong(map.get(color3)));
        System.out.println(ans);

    }
}