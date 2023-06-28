package General;

import Product.Product;
import Vehicles.Vehicle;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //INICIO DA SIMULAÇÃO
        WarehouseCSV dimensionsFile = new WarehouseCSV();
        ProductsCSV productsFile = new ProductsCSV();
        VehiclesCSV vehiclesFile = new VehiclesCSV();
        int dimensions[] = dimensionsFile.readFromCSV();
        System.out.println("\nArmazem criado com x: " + dimensions[0] + " e y: " + dimensions[1] + " visivel abaixo:\n");

        DistributionCenter distributionCenter = new DistributionCenter(dimensions[0],dimensions[1]) ;
        List<Product> productList = productsFile.readFromCSV();

        for(Product p : productList){
            p.setWeights();// gera pesos para cada item
        }
        System.out.println("- Foram gerados diferentes pesos para cada produto.");

        List<Shelf> shelves = distributionCenter.getShelves();
        List<Vehicle> vehicles = vehiclesFile.readFromCSV();
        List<Position> positions = distributionCenter.getPositionsList();
        distributionCenter.setVehiclesStartingPositions(vehicles, positions);
        System.out.println(distributionCenter.printHowManyAvailableVehicles(vehicles));
        List packedItems = distributionCenter.packProducts(productList);
        System.out.println("\n- Os produtos foram embalados, dando origem a: \n" + distributionCenter.countPackedItemsResults(packedItems));

        distributionCenter.loadVehicles(packedItems, vehicles);
       // Scanner scanner = new Scanner(System.in);

       /* System.out.println("Digite o tipo de veículo que deseja mexer (ulc, dc, tugv, agc): ");
        String vehicleType = scanner.nextLine();

        System.out.println("Digite até que posição deseja mexer o veículo (x,y): ");
        String positionInput = scanner.nextLine();
        String[] positionValues = positionInput.split(",");
        int x = Integer.parseInt(positionValues[0]);
        int y = Integer.parseInt(positionValues[1]);*/

    }
}