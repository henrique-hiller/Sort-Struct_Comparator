import java.util.Scanner;

public class App{
    public static void main (String args[]){
        System.out.println("Please type the file name (with .txt at the end): ");
        Scanner sc = new Scanner (System.in);
        String file = sc.nextLine();
        opcoes();
        int selection = sc.nextInt();
        RDRSort rdr = new RDRSort(file, selection);
        sc.close();
    }

    public static void opcoes(){
        System.out.println("Choose the data structure");
        System.out.println("[1] ArrayList");
        System.out.println("[2] HashSet");
        System.out.println("[3] HashMap");
        System.out.println("[4] TreeSet");
        System.out.println("[5] Caso espacial???");
    }
}