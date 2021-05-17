import java.util.*;

public class FiniteStateMachine {
    public static void main(String[] args) {
        runNodesGame();
        //runFsmString();
    }

    public static void runFsmString() {
        System.out.println("Voer hier je input in: ");
        Scanner scanner2 = new Scanner(System.in);
        String input = scanner2.nextLine().toUpperCase();
        Map<Boolean, ArrayList<String>> nodeOutput = runNodesString(input);
        Map.Entry<Boolean, ArrayList<String>> entry = nodeOutput.entrySet().iterator().next();
        Boolean success = entry.getKey();
        ArrayList<String> outputArray = entry.getValue();
        String outputString = "";
        if (!success) {
            outputString = "Ongeldige letter tegengekomen.\n";
        }
        System.out.println(outputString + outputArray);
    }

    public static Map<Boolean, ArrayList<String>> runNodesString(String input) {
        Node s0 = initialiseNodesString();
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

    public static void runNodesGame() {
        Node currentNode = initialiseNodesGame();
        Random rand = new Random();
        int hurtCounter = 0;
        int hitCounter = 0;
        boolean isFirst = true;
        while (true) {
            switch (currentNode.getNodeName()) {
                case "Initial" -> {
                    if (isFirst) {
                        waitForUser("Welkom, druk op enter om verder te gaan");
                        isFirst = false;
                    }
                    currentNode.connect(1);
                    currentNode = currentNode.getCurrentNode();
                }
                case "Fight" -> {
                    waitForUser("Druk op enter om aan te vallen.");
                    currentNode.connect(rand.nextInt(2));
                    currentNode = currentNode.getCurrentNode();
                }
                case "Hit" -> {
                    waitForUser("Je hebt de vijand geraakt! Druk op enter om verder te gaan.");
                    hitCounter++;
                    currentNode.connect(hitCounter);
                    currentNode = currentNode.getCurrentNode();
                }
                case "Hurt" -> {
                    waitForUser("Je bent geraakt! Druk op enter om verder te gaan.");
                    hurtCounter++;
                    currentNode.connect(hurtCounter);
                    currentNode = currentNode.getCurrentNode();
                }
                case "Won" -> {
                    waitForUser("Je hebt gewonnen, gefeliciteerd! Druk op enter om af te sluiten.");
                    return;
                }
                case "Dead" -> {
                    waitForUser("Je bent dood, rust in vrede. Druk op enter om af te sluiten.");
                    return;
                }
            }
        }
    }

    public static void waitForUser(String message) {
        System.out.println(message);
        Scanner scanner2 = new Scanner(System.in);
        scanner2.nextLine();
    }

    public static Node initialiseNodesString() {
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

    public static Node initialiseNodesGame() {
        Node initial = new Node("Initial");
        Node fight = new Node("Fight");
        Node hurt = new Node("Hurt");
        Node dead = new Node("Dead");
        Node hit = new Node("Hit");
        Node won = new Node("Won");
        initial.makeConnection("1", fight);
        fight.makeConnection("0", hurt);
        fight.makeConnection("1", hit);
        hit.makeConnection("1", initial);
        hit.makeConnection("2", initial);
        hit.makeConnection("3", won);
        hurt.makeConnection("1", initial);
        hurt.makeConnection("2", initial);
        hurt.makeConnection("3", dead);

        return initial;
    }

}
