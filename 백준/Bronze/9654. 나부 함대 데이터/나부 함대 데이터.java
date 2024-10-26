import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        sb.append("SHIP NAME      CLASS          DEPLOYMENT IN SERVICE").append("\n");
        sb.append("N2 Bomber      Heavy Fighter  Limited    21        ").append("\n");
        sb.append("J-Type 327     Light Combat   Unlimited  1         ").append("\n");
        sb.append("NX Cruiser     Medium Fighter Limited    18        ").append("\n");
        sb.append("N1 Starfighter Medium Fighter Unlimited  25        ").append("\n");
        sb.append("Royal Cruiser  Light Combat   Limited    4         ");

        System.out.println(sb.toString());
    }
}