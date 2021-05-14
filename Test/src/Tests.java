import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    @Test
    public void testNode(){
        Node s0 = Main.initialiseNodes();
        Map<String, ArrayList<String>> nodeMap = s0.connect("A");

        ArrayList<String> output = new ArrayList<>();
        output.add("s0");
        output.add("s2");
        Map<String, ArrayList<String>> testMap = new HashMap<>();
        testMap.put("Letters op.\nAfgelopen route: ", output);
        assertEquals(nodeMap, testMap);
    }

}
