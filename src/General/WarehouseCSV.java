package General;
import java.io.*;
import java.util.Scanner;

/**
 * Represents a CSV file for storing warehouse data.
 */
public class WarehouseCSV implements CSV {

    /**
     * Creates a new instance of the `WarehouseCSV` class and automatically creates the CSV file.
     */
    public WarehouseCSV() {
        createCSV();
    }

    /**
     * Creates the CSV file and populates it with warehouse data.
     */
    @Override
    public void createCSV() {
        String filePath = "armazem.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Escreve o cabeçalho do arquivo General.CSV
            writer.write("Dimensions\n");

            // Escreve as dimensões do armazém
            int length = 11; //comprimento
            int width = 10; //largura
            writer.write(length + "," + width + "\n");

            // Escreve os locais de armazenamento
           /* writer.write("Localizacao,Dimensao\n");
            writer.write("Local1,10,10\n");
            writer.write("Local2,20,10\n");
            writer.write("Local3,30,10\n");*/

            // Escreve os veiculos disponiveis
            // writer.write();

            System.out.println("Arquivo CSV do armazém criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao criar o arquivo General.CSV do armazém: " + e.getMessage());
        }
    }

    /**
     * Reads the warehouse data from the CSV file and returns the dimensions of the warehouse.
     *
     * @return an array of integers representing the dimensions of the warehouse [length, width]
     */
    public int[] readFromCSV() {
        String filePath = "armazem.csv";
        int[] size = new int[0];
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Lê a primeira linha do arquivo (cabeçalho) e ignora
            String header = scanner.nextLine();

            // Lê a próxima linha do arquivo (dimensão do armazém)
            String dimensionLine = scanner.nextLine();

            // Divide a linha em valores separados por vírgula
            String[] dimensions = dimensionLine.split(",");

            // Obtém os valores individuais e armazena em variáveis
            int length = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);
            size = new int[]{length, width};

            //System.out.println("Dimensões do armazém:");
            //System.out.println("Length: " + length);
            //System.out.println("Width: " + width);

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo CSV do armazém não foi encontrado.");
        }

        return size;
    }
}