import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Tests {

    @Test
    public void testString() {
        Map<Boolean, ArrayList<String>> testOne = new HashMap<>();
        ArrayList<String> testListOne = new ArrayList<>();
        testListOne.add("s0");
        testListOne.add("s2");
        testOne.put(true, testListOne);

        Map<Boolean, ArrayList<String>> testTwo = new HashMap<>();
        ArrayList<String> testListTwo = new ArrayList<>();
        testListTwo.add("s0");
        testListTwo.add("s1");
        testListTwo.add("s1");
        testListTwo.add("s1");
        testListTwo.add("s2");
        testTwo.put(true, testListTwo);

        Map<Boolean, ArrayList<String>> testThree = new HashMap<>();
        ArrayList<String> testListThree = new ArrayList<>();
        testListThree.add("s0");
        testListThree.add("s2");
        testThree.put(false, testListThree);

        Map<Boolean, ArrayList<String>> testFour = new HashMap<>();
        ArrayList<String> testListFour = new ArrayList<>();
        testListFour.add("s0");
        testListFour.add("s2");
        testFour.put(false, testListFour);

        Map<Boolean, ArrayList<String>> testFive = new HashMap<>();
        ArrayList<String> testListFive = new ArrayList<>();
        testListFive.add("s0");
        testFive.put(false, testListFive);

        Map<Boolean, ArrayList<String>> testSix = new HashMap<>();
        ArrayList<String> testListSix = new ArrayList<>();
        testListSix.add("s0");
        testSix.put(true, testListSix);

        Map<Boolean, ArrayList<String>> nodeOutput = FiniteStateMachine.runNodesString("B".repeat(1000000));
        Map.Entry<Boolean, ArrayList<String>> entry = nodeOutput.entrySet().iterator().next();
        ArrayList<String> outputArray = entry.getValue();
        int testSeven = outputArray.size();

        Assertions.assertAll(
                () -> assertEquals(testOne, FiniteStateMachine.runNodesString("A")),
                () -> assertEquals(testTwo, FiniteStateMachine.runNodesString("BAAB")),
                () -> assertEquals(testThree, FiniteStateMachine.runNodesString("AA")),
                () -> assertEquals(testFour, FiniteStateMachine.runNodesString("AX")),
                () -> assertEquals(testFive, FiniteStateMachine.runNodesString("1453")),
                () -> assertEquals(testSix, FiniteStateMachine.runNodesString("")),
                () -> assertEquals(1000001, testSeven)
        );
    }

    @Test
    public void testGame() {
        Node testNodeOne = runInputs(new String[]{"1"});
        Node testNodeTwo = runInputs(new String[]{"15"});
        Node testNodeThree = runInputs(new String[]{"1", "0", "1"});
        Node testNodeFour = runInputs(new String[]{"1", "0", "3"});
        Node testNodeFive = runInputs(new String[]{"1", "1", "2"});
        Node testNodeSix = runInputs(new String[]{"1", "1", "3"});
        Node testNodeSeven = runInputs(new String[]{"1", "0", "1", "1", "0", "2", "1", "0", "3"});
        Node testNodeEight = runInputs(new String[]{"1", "1", "1", "1", "1", "2", "1", "1", "3"});

        Assertions.assertAll(
                () -> assertEquals("Fight", testNodeOne.getNodeName()),
                () -> assertFalse(testNodeTwo.isSuccess()),
                () -> assertEquals("Initial", testNodeThree.getNodeName()),
                () -> assertEquals("Dead", testNodeFour.getNodeName()),
                () -> assertEquals("Initial", testNodeFive.getNodeName()),
                () -> assertEquals("Win", testNodeSix.getNodeName()),
                () -> assertEquals("Dead", testNodeSeven.getNodeName()),
                () -> assertEquals("Win", testNodeEight.getNodeName())
        );
    }

    public Node runInputs(String[] inputs) {
        Node testNode = FiniteStateMachine.initialiseNodesGame();
        for (String input : inputs) {
            testNode.connect(input);
            testNode = testNode.getCurrentNode();
        }
        return testNode;
    }
}
