package crawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main
{
    private static final String allDataFile = "allInf.csv";
    private static final String mainInf = "mainInf.csv";

    public static void main(String[] args)
    {
        LinkStorage linkStorage = new LinkStorage();
        try(Scanner scanner = new Scanner(System.in);
            PrintWriter allDataPrintWriter = new PrintWriter(new File(allDataFile));
            PrintWriter mainInfWriter = new PrintWriter(new File(mainInf));
        ) {
            System.out.println("Input start link ");
            String link = scanner.nextLine();
            System.out.println("How many terms do you want to enter? ");
            int count = scanner.nextInt();
            System.out.println("Input terms ");
            List<String> terms = new ArrayList<>();
            for (int i = 0; i < count; i++){
                terms.add(scanner.next());
            }
            List<LinkData> mainInf = linkStorage.findResultInformation(link, terms);
            Set<LinkData> allInf = linkStorage.getPagesVisited();
            allInf.forEach(item->allDataPrintWriter.println(item));
            mainInf.forEach(item-> {
                System.out.println(item);
                mainInfWriter.println(item);

            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (InputMismatchException e){
            System.out.println("Incorrect data ");
        }

    }
}
