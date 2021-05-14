import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] kwargs) {
        Node s0 = initialiseNodes();
        System.out.println("Voer hier je input in: ");
        Scanner scanner2 = new Scanner(System.in);
        String input = scanner2.nextLine().toUpperCase();

        Map<Integer, ArrayList<String>> output = s0.connect(input);
        for (Map.Entry<Integer, ArrayList<String>> entry : output.entrySet()) {
            Integer resultState = entry.getKey();
            ArrayList<String> visitedNodes = entry.getValue();
            String message = switch (resultState) {
                case 0 -> "Kwam bij ongeldige letter, dus gestopt.\nAfgelopen route: ";
                case 1 -> "Letters op.\nAfgelopen route: ";
                default -> "Onbekende fout opgetreden.";
            };
            System.out.println(message);
            for (String node: visitedNodes){
                System.out.println(node);
            }
        }

    }

    public static Node initialiseNodes() {
        Node s0 = new Node("s0");
        Node s1 = new Node("s1");
        Node s2 = new Node("s2");
        Node s3 = new Node("s3");
        s0.makeConnection("A", s2);
        s0.makeConnection("B", s1);
        s1.makeConnection("A", s1);
        s1.makeConnection("B", s2);
        s2.makeConnection("B", s3);
        s3.makeConnection("A", s3);
        s3.makeConnection("B", s0);
        return s0;
    }


}
