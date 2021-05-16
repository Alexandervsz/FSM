import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node {
    private final String nodeName;
    private final Map<String, Node> nodeConnections = new HashMap<>();
    private ArrayList<String> output = new ArrayList<>();
    private Node currentNode = this;
    private boolean success = true;

    public Node(String nodeName) {
        this.nodeName = nodeName;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setOutput(ArrayList<String> output) {
        this.output = output;
    }

    public String getNodeName() {
        return nodeName;
    }

    public ArrayList<String> getOutput() {
        return this.output;
    }

    public void makeConnection(String letter, Node node) {
        // Inserts a connection to a certain node.
        nodeConnections.put(letter, node);
    }

    public void connect(char input) {
        /* This changes the node to the next state. */
        if (nodeConnections.containsKey(String.valueOf(input))) {
            output.add(nodeName);
            Node new_node = nodeConnections.get(String.valueOf(input));
            new_node.setOutput(output);
            currentNode = new_node;
        } else {
            success = false;
        }

    }

}



