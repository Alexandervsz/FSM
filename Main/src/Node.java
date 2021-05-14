import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node {
    private final String nodeName;
    private final Map<String, Node> nodeConnections = new HashMap<>();
    private int endState;
    ArrayList<String> output = new ArrayList<>();

    public Node(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setOutput(ArrayList<String> output) {
        this.output = output;
    }

    public int getEndState() {
        return this.endState;
    }

    public ArrayList<String> getOutput() {
        return this.output;
    }

    public void makeConnection(String letter, Node node) {
        // Inserts a connection to a certain node.
        nodeConnections.put(letter, node);
    }

    public Node connect(String input) {
        /* This function tells the node whether to go to a next node or not. */
        output.add(nodeName);
        if (input.length() > 0) {
            if (nodeConnections.containsKey(input.substring(0, 1))) {
                Node new_node = nodeConnections.get(input.substring(0, 1));
                input = input.substring(1);
                new_node.setOutput(output);
                new_node.connect(input);
            } else {
                endState = 0;
                return this;
            }
        } else {
            endState = 1;
            return this;
        }
        endState = 1;
        return this;
    }
}


