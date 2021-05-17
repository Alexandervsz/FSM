import java.util.*;

public class FiniteStateMachine {
    public static void main(String[] args) {
        String mode = getUserInput("Kies een mode, \"Letters\" of \"Spel\"").toLowerCase();
        while (true) {
            switch (mode) {
                case "letters", "l" -> {
                    runFsmString();
                    return;
                }
                case "spel", "s" -> {
                    runNodesGame();
                    return;
                }
                default -> mode = getUserInput("Verkeerde input, voer een andere optie in");
            }
        }
    }

    public static void runFsmString() {
        String input = getUserInput("Voer hier je input in: ").toUpperCase();
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
            s0 = changeState(c, s0);
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
                    currentNode = changeState(1, currentNode);
                }
                case "Fight" -> {
                    waitForUser("Druk op enter om aan te vallen.");
                    currentNode = changeState(rand.nextInt(2), currentNode);
                }
                case "Hit" -> {
                    waitForUser("Je hebt de vijand geraakt! Druk op enter om verder te gaan.");
                    hitCounter++;
                    currentNode = changeState(hitCounter, currentNode);
                }
                case "Hurt" -> {
                    waitForUser("Je bent geraakt! Druk op enter om verder te gaan.");
                    hurtCounter++;
                    currentNode = changeState(hurtCounter, currentNode);
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

    public static String getUserInput(String message) {
        System.out.println(message);
        Scanner scanner2 = new Scanner(System.in);
        return scanner2.nextLine();
    }

    public static Node changeState(char input, Node currentNode) {
        return changeState(String.valueOf(input), currentNode);
    }

    public static Node changeState(int input, Node currentNode) {
        return changeState(String.valueOf(input), currentNode);
    }

    public static Node changeState(String input, Node currentNode) {
        currentNode.connect(input);
        return currentNode.getCurrentNode();
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
