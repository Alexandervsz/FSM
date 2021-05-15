import java.util.Scanner;

public class Main {
    public static void main(String[] kwargs) {
        Node s0 = initialiseNodes();
        System.out.println("Voer hier je input in: ");
        Scanner scanner2 = new Scanner(System.in);
        String input = scanner2.nextLine().toUpperCase();
        String endState = s0.connect(input);
        String outputString = switch (endState) {
            case "0" -> "Ongeldige letter tegen gekomen.\nAfgelegd pad: ";
            case "1" -> "Afgelegd pad: ";
            default -> "Onbekende fout.\n Afgelegd pad: ";
        };
        System.out.println(outputString+s0.getOutput());
        }

    public static Node initialiseNodes() {
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


}
