package General;

import Vehicles.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a CSV file for storing vehicle data.
 */
public class VehiclesCSV implements CSV {

    /**
     * Creates a new instance of the `VehiclesCSV` class and automatically creates the CSV file.
     */
    public VehiclesCSV() {
        createCSV();
    }

    /**
     * Creates the CSV file and populates it with randomly generated vehicle data.
     */
    @Override
    public void createCSV() {
        String filePath = "veiculos.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Escreve o cabeçalho do arquivo General.CSV
            writer.write("ID,Type\n");
            String[] vehicleType = {"AGC", "TugVehicle", "ULC", "DeliveryCart"};
            String[][] items = new String[100][2];

            for (int i = 0; i < items.length; i++){
                Random random = new Random();
                int randomIndex = random.nextInt(vehicleType.length);
                String randomString = vehicleType[randomIndex];
                items[i][0] = "Vehicle" + (i+1) + ",";
                items[i][1] = randomString + "\n";
            }

            for (int i = 0; i < items.length; i++) {
                for (int j = 0; j < items[i].length; j++) {
                    writer.write(items[i][j]);
                }
            }
            System.out.println("Arquivo CSV dos veiculos criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao criar o arquivo CSV dos produtos: " + e.getMessage());
        }
    }

    /**
     * Reads the vehicle data from the CSV file and returns a list of `Vehicle` objects.
     *
     * @return a list of `Vehicle` objects read from the CSV file
     */
    public List<Vehicle> readFromCSV() {
        String filePath = "veiculos.csv";

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
        List<Vehicle> vehicles = new ArrayList<>();

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


        for (int i = 0; i < data.length; i++) {
                if(data[i][1].equals("AGC")){
                    AGC agc = new AGC();
                    vehicles.add(agc);
                }
                else if(data[i][1].equals("DeliveryCart")){
                    DeliveryCart deliveryCart = new DeliveryCart();
                    vehicles.add(deliveryCart);
                }
                else if(data[i][1].equals("TugVehicle")){
                    TugVehicle tugVehicle = new TugVehicle();
                    vehicles.add(tugVehicle);
                }
                else if(data[i][1].equals("ULC")){
                    ULC ulc = new ULC();
                    vehicles.add(ulc);
                }
            }

        System.out.println("\n- Foram carregados " + (data.length-1) + " veiculos para o armazém.");
        return vehicles;
        }
}
