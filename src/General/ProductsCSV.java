package General;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import General.CSV;
import Product.Product;
import Product.ProductType;

/**
 * The ProductsCSV class implements the CSV interface and is responsible for creating a CSV file for products.
 */
public class ProductsCSV implements CSV {

    /**
     * Constructs a new instance of the ProductsCSV class.
     * The constructor calls the createCSV() method to create the CSV file.
     */
    public ProductsCSV(){
        createCSV();
    }

    /**
     * Creates a CSV file for products.
     * This method is implemented from the CSV interface.
     */
    @Override
    public void createCSV() {
        String filePath = "produtos.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Escreve o cabeçalho do arquivo General.CSV
            writer.write("Product,Type\n");
            ProductType[] productType = {ProductType.CLOTHING, ProductType.ACCESSORY, ProductType.SMALL_TOY, ProductType.LARGE_TOY, ProductType.SMALL_ELECTRONIC_EQUIPMENT, ProductType.LARGE_ELECTRONIC_EQUIPMENT, ProductType.BOOK};
            String[][] items = new String[200][2];

            for (int i = 0; i < items.length; i++){
                    Random random = new Random();
                    int randomIndex = random.nextInt(productType.length);
                    String randomString = productType[randomIndex].toString();
                    items[i][0] = "Item" + (i+1) + ",";
                    items[i][1] = randomString + "\n";
            }

            for (int i = 0; i < items.length; i++) {
                for (int j = 0; j < items[i].length; j++) {
                    writer.write(items[i][j]);
                }
            }
            System.out.println("Arquivo CSV dos produtos criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao criar o arquivo General.CSV dos produtos: " + e.getMessage());
        }
    }

    /**
     * Reads product data from a CSV file and returns a list of products.
     * The CSV file should have the following format: "productName,productType".
     */
    public List<Product> readFromCSV() {
        String filePath = "produtos.csv";

        // Contar o número de linhas e colunas
        int numRows = 0;
        int numCols = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                numRows++;
                numCols = Math.max(numCols, row.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Criar a matriz com as dimensões adequadas
        String[][] data = new String[numRows][numCols];
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowIdx = 0;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                for (int colIdx = 0; colIdx < row.length; colIdx++) {
                    data[rowIdx][colIdx] = row[colIdx];
                }
                rowIdx++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProductType[] productTypes = ProductType.values();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < productTypes.length; j++) {
                if (productTypes[j].toString().equals(data[i][1])) { // Use equals() to compare strings
                    Product product = new Product(data[i][0], productTypes[j]);
                    products.add(product);
                }
            }
        }

        System.out.println("\n- Foram carregados " + (data.length-1) + " produtos não embalados.");
        return products;
    }
}