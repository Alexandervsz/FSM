public class Node implements NodeInterface {
    private String nodeName;
    private Node nodeA;
    private Node nodeB;

    public Node(String nodeName, Node nodeA, Node nodeB) {
        this.nodeName = nodeName;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public Node(String nodeName, Node nodeA) {
        this.nodeName = nodeName;
        this.nodeA = nodeA;
    }

    public String makeConnection(String input, StringBuilder output) {
        if (input.charAt(0) == 'A') {
            input = input.substring(1);
            output.append(nodeName);
            nodeA.makeConnection(input, output);

        }
        else if (input.charAt(0) == 'B'){
            input = input.substring(1);
            output.append(nodeName);
            if (nodeB != null){
            nodeB.makeConnection(input, output);}
            else{
                return "Kwam bij s2 met een A, dus gestopt.";
            }
        }
        else {
            return "Kwam bij ongeldige letter dus gestopt\n afgelopen pad:"+ output.toString();
        }
        return "Mysterious error";
    }
}
