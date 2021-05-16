import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FiniteStateMachine {
    public static void main(String[] args) {
        System.out.println("Voer hier je input in: ");
        Scanner scanner2 = new Scanner(System.in);
        String input = scanner2.nextLine().toUpperCase();
        Map<Boolean, ArrayList<String>> nodeOutput = runNodes(input);
        Map.Entry<Boolean, ArrayList<String>> entry = nodeOutput.entrySet().iterator().next();
        Boolean success = entry.getKey();
        ArrayList<String> outputArray = entry.getValue();
        String outputString = "";
        if (!success) {
            outputString = "Ongeldige letter tegengekomen.\n";
        }
        System.out.println(outputString + outputArray);
    }

    public static Map<Boolean, ArrayList<String>> runNodes(String input) {
        Node s0 = initialiseNodes();
        Map<Boolean, ArrayList<String>> outputMap = new HashMap<>();
        char[] inputChars = input.toCharArray();
        for (char c : inputChars) {
            s0.connect(c);
            s0 = s0.getCurrentNode();
        }
        ArrayList<String> output = s0.getOutput();
        output.add(s0.getCurrentNode().getNodeName());
        outputMap.put(s0.isSuccess(), output);
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
