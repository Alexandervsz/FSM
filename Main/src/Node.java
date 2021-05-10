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

    public void makeConnection(String input, StringBuilder output) {
        if (input.length() > 0) {
            if (input.charAt(0) == 'A') {
                input = input.substring(1);
                output.append(nodeName);
                output.append(",");
                if (nodeA != null) {
                    nodeA.makeConnection(input, output);
                } else {
                    System.out.println("Kwam bij s2 met een A, dus gestopt.");
                }

            } else if (input.charAt(0) == 'B') {
                input = input.substring(1);
                output.append(nodeName);
                output.append(",");
                nodeB.makeConnection(input, output);

            } else {
                output.append(nodeName);
                System.out.println("Kwam bij ongeldige letter dus gestopt\nAfgelopen pad:" + output);
            }
        } else {
            output.append(nodeName);
            System.out.println("Letters op, afgelopen pad:" + output);
        }
    }

}


