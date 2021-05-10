import java.util.ArrayList;

public class Node implements NodeInterface {
    private final String nodeName;
    private Node nodeA;
    private Node nodeB;

    public void setNodeA(Node nodeA) {
        this.nodeA = nodeA;
    }

    public void setNodeB(Node nodeB) {
        this.nodeB = nodeB;
    }

    public Node(String nodeName) {
        this.nodeName = nodeName;
    }

    public void makeConnection(String input, ArrayList<String> output) {
        if (input.length() > 0) {
            if (input.charAt(0) == 'A') {
                input = input.substring(1);
                output.add(nodeName);
                if (nodeA != null) {
                    nodeA.makeConnection(input, output);
                } else {
                    System.out.println("Kwam bij s2 met een A, dus gestopt.\nAfgelopen route: " + output);
                }

            } else if (input.charAt(0) == 'B') {
                input = input.substring(1);
                output.add(nodeName);
                nodeB.makeConnection(input, output);

            } else {
                output.add(nodeName);
                System.out.println("Kwam bij ongeldige letter dus gestopt\nAfgelopen route: " + output);
            }
        } else {
            output.add(nodeName);
            System.out.println("Letters op.\nAfgelopen route: " + output);
        }
    }

}


