import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Tests {

    @Test
    public void testString() {
        //Testcase single A.
        Map<Boolean, ArrayList<String>> testOne = runInputsString(new String[]{"s0", "s2"}, true);
        //testcase BAAB.
        Map<Boolean, ArrayList<String>> testTwo = runInputsString(new String[]{"s0", "s1", "s1", "s1", "s2"}, true);
        //Testcase double A.
        Map<Boolean, ArrayList<String>> testThree = runInputsString(new String[]{"s0", "s2"}, false);
        //Testcase A, invalid letter.
        @SuppressWarnings("UnnecessaryLocalVariable")
        Map<Boolean, ArrayList<String>> testFour = testThree;
        //Testcase invalid string.
        Map<Boolean, ArrayList<String>> testFive = runInputsString(new String[]{"s0"}, false);
        //Testcase empty string.
        Map<Boolean, ArrayList<String>> testSix = runInputsString(new String[]{"s0"}, true);
        //Testcase large string.
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
        //Testcase initial node, valid input.
        Node testNodeOne = runInputsGame(new String[]{"1"});
        //Testcase initial node, invalid input.
        Node testNodeTwo = runInputsGame(new String[]{"15"});
        //Testcase one loop.
        Node testNodeThree = runInputsGame(new String[]{"1", "0", "1"});
        //Testcase full game, loss (simple).
        Node testNodeFour = runInputsGame(new String[]{"1", "0", "3"});
        //Testcase one loop, different node.
        Node testNodeFive = runInputsGame(new String[]{"1", "1", "2"});
        //Testcase full game, win (simple).
        Node testNodeSix = runInputsGame(new String[]{"1", "1", "3"});
        //Testcase full game, loss (full run).
        Node testNodeSeven = runInputsGame(new String[]{"1", "0", "1", "1", "0", "2", "1", "0", "3"});
        //Testcase full game, win (full run).
        Node testNodeEight = runInputsGame(new String[]{"1", "1", "1", "1", "1", "2", "1", "1", "3"});

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

    public Map<Boolean, ArrayList<String>> runInputsString(String[] inputs, boolean success){
        Map<Boolean, ArrayList<String>> testMap = new HashMap<>();
        ArrayList<String> testList = new ArrayList<>(Arrays.asList(inputs));
        testMap.put(success, testList);
        return testMap;
    }

    public Node runInputsGame(String[] inputs) {
        Node testNode = FiniteStateMachine.initialiseNodesGame();
        for (String input : inputs) {
            testNode.connect(input);
            testNode = testNode.getCurrentNode();
        }
        return testNode;
    }
}
