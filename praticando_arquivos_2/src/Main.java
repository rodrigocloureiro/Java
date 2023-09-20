import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Path pathDataFile = Paths.get(System.getProperty("user.dir"), "data", "brasileirao-2004-2022.csv");

            List<String> lines = Files.readAllLines(pathDataFile);
            List<String[]> data = new ArrayList<>();

            for (int i = 1; i < lines.size(); i++) {
                String[] values = lines.get(i).split(", ");
                data.add(values);
            }

            System.out.print("Qual time deseja pesquisar? ");
            String time = sc.nextLine().trim();

            List<String[]> dataFiltered = data.stream()
                    .filter(info -> Integer.parseInt(info[0]) > 2005 && Integer.parseInt(info[1]) >= 17 && info[2].equalsIgnoreCase(time))
                    .toList();
            if (dataFiltered.isEmpty()) System.out.println(time + " nunca foi rebaixado nos pontos corridos.");
            else {
                System.out.println("Campanha no(s) rebaixamento(s):");
                dataFiltered.forEach(infos -> {
                    for (String info : infos) {
                        System.out.print(info + " | ");
                    }
                    System.out.println();
                });
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
