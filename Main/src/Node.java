import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node {
    private final String nodeName;
    private final Map<String, Node> nodeConnections = new HashMap<>();

    public Node(String nodeName) {
        this.nodeName = nodeName;
    }

    public void makeConnection(String letter, Node node) {
        nodeConnections.put(letter, node);
    }

    public void connect(String input) {
        ArrayList<String> output = new ArrayList<>();
        connect(input, output);
    }

    public void connect(String input, ArrayList<String> output) {

        /* This function tells the node whether to go to a next node or not (and which node should be activated,
         or stops the recursion if conditions are met. (No more letters left or invalid letter found) */
        output.add(nodeName);
        if (input.length() > 0) {
            if (nodeConnections.containsKey(input.substring(0, 1))) {
                Node new_node = nodeConnections.get(input.substring(0, 1));
                input = input.substring(1);
                new_node.connect(input, output);
            } else {
                System.out.println("Kwam bij ongeldige letter, dus gestopt.\nAfgelopen route: " + output);
            }
        } else {
            System.out.println("Letters op.\nAfgelopen route: " + output);
        }
    }

}


