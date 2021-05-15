import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    @Test
    public void testNode() {
        Map<String, ArrayList<String>> testOne = new HashMap<>();
        ArrayList<String> testListOne = new ArrayList<>();
        testListOne.add("s0");
        testListOne.add("s2");
        testOne.put("1", testListOne);


        Map<String, ArrayList<String>> testTwo = new HashMap<>();
        ArrayList<String> testListTwo = new ArrayList<>();
        testListTwo.add("s0");
        testListTwo.add("s1");
        testListTwo.add("s1");
        testListTwo.add("s1");
        testListTwo.add("s2");
        testTwo.put("1", testListTwo);


        Map<String, ArrayList<String>> testThree = new HashMap<>();
        ArrayList<String> testListThree = new ArrayList<>();
        testListThree.add("s0");
        testListThree.add("s2");
        testThree.put("0", testListThree);


        Map<String, ArrayList<String>> testFour = new HashMap<>();
        ArrayList<String> testListFour = new ArrayList<>();
        testListFour.add("s0");
        testListFour.add("s2");
        testFour.put("0", testListFour);


        Map<String, ArrayList<String>> testFive = new HashMap<>();
        ArrayList<String> testListFive = new ArrayList<>();
        testListFive.add("s0");
        testFive.put("0", testListFive);


        Map<String, ArrayList<String>> testSix = new HashMap<>();
        ArrayList<String> testListSix = new ArrayList<>();
        testListSix.add("s0");
        testSix.put("1", testListSix);


        Assertions.assertAll(
                () -> assertEquals(FiniteStateMachine.runNodes("A"), testOne),
                () -> assertEquals(FiniteStateMachine.runNodes("BAAB"), testTwo),
                () -> assertEquals(FiniteStateMachine.runNodes("AA"), testThree),
                () -> assertEquals(FiniteStateMachine.runNodes("AX"), testFour),
                () -> assertEquals(FiniteStateMachine.runNodes("1453"), testFive),
                () -> assertEquals(FiniteStateMachine.runNodes(""), testSix)

        );
    }

}
