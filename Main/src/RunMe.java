import java.util.Scanner;

public class RunMe {
    public static void main(String[] kwargs) {
        Node s0 = new Node("s0");
        Node s1 = new Node("s1");
        Node s2 = new Node("s2");
        Node s3 = new Node("s3");
        s0.setNodeA(s2);
        s0.setNodeB(s1);
        s1.setNodeA(s1);
        s1.setNodeB(s2);
        s2.setNodeB(s3);
        s3.setNodeA(s3);
        s3.setNodeB(s0);
        System.out.println("Voer hier je input in: ");
        Scanner scanner2 = new Scanner(System.in);
        String input = scanner2.nextLine();
        System.out.println(s0.makeConnection(input,new StringBuilder()));
    }


}
