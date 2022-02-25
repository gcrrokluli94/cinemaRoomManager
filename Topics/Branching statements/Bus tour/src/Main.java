import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int height = in.nextInt();
        int numberOfBridges = in.nextInt();
        int[] bridges = new int[numberOfBridges];
        boolean crash = false;
        int theBridge = 0;
        for (int i = 0; i < numberOfBridges; i++) {
            bridges[i] = in.nextInt();
        }
        for (int i = 0; i < numberOfBridges && !crash; i++) {
            if (bridges[i] <= height) {
                theBridge = i + 1;
                crash = true;
                break;
            }
        }
        if (crash) {
            System.out.println("Will crash on bridge " + theBridge);
        } else
            System.out.println("Will not crash");
    }
}