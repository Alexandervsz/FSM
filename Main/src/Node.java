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
        // Inserts a connection to a certain node.
        nodeConnections.put(letter, node);
    }


    public Map<String, ArrayList<String>> connect(String input, ArrayList<String> output) {
        /* This function tells the node whether to go to a next node or not. */
        output.add(nodeName);
        if (input.length() > 0) {
            if (nodeConnections.containsKey(input.substring(0, 1))) {
                Node new_node = nodeConnections.get(input.substring(0, 1));
                input = input.substring(1);
                new_node.connect(input, output);
            } else {
                Map<String, ArrayList<String>> outputMap = new HashMap<>();
                outputMap.put("Kwam bij ongeldige letter, dus gestopt.\nAfgelopen route: ", output);
                return outputMap;
            }
        } else {
            Map<String, ArrayList<String>> outputMap = new HashMap<>();
            outputMap.put("Letters op.\nAfgelopen route: ", output);
            return outputMap;
        }
        Map<String, ArrayList<String>> outputMap = new HashMap<>();
        outputMap.put("Letters op.\nAfgelopen route: ", output);
        return outputMap;
    }

}


