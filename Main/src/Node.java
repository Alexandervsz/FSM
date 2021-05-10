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

    public String makeConnection(String input, StringBuilder output) {
        if (input.length() > 0) {
            if (input.charAt(0) == 'A') {
                input = input.substring(1);
                output.append(nodeName);
                output.append(",");
                nodeA.makeConnection(input, output);

            } else if (input.charAt(0) == 'B') {
                input = input.substring(1);
                output.append(nodeName);
                output.append(",");
                if (nodeB != null) {
                    nodeB.makeConnection(input, output);
                } else {
                    return "Kwam bij s2 met een A, dus gestopt.";
                }
            } else {
                return "Kwam bij ongeldige letter dus gestopt\n afgelopen pad:" + output.toString();
            }
        }
        else
        {
            output.append(nodeName);
        }
        return "Letters op, afgelopen pad:" + output;
    }
}

