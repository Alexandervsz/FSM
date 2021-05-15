import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FiniteStateMachine {
    public static void main(String[] args) {
        System.out.println("Voer hier je input in: ");
        Scanner scanner2 = new Scanner(System.in);
        String input = scanner2.nextLine().toUpperCase();
        Map<String, ArrayList<String>> nodeOutput = runNodes(input);
        Map.Entry<String,ArrayList<String>> entry = nodeOutput.entrySet().iterator().next();
        String key = entry.getKey();
        ArrayList<String> outputArray = entry.getValue();
        String outputString = switch (key) {
            case "0" -> "Ongeldige letter tegen gekomen.\nAfgelegd pad: ";
            case "1" -> "Afgelegd pad: ";
            default -> "Onbekende fout.\n Afgelegd pad: ";
        };
        System.out.println(outputString+outputArray);

    }

    public static Map<String, ArrayList<String>> runNodes(String input){
        Node s0 = initialiseNodes();
        Map<String, ArrayList<String>> outputMap = new HashMap<>();
        outputMap.put(s0.connect(input), s0.getOutput());
        return outputMap;

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
