package General;

import Product.Product;
import Vehicles.Vehicle;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AppStart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        WarehouseCSV dimensionsFile = new WarehouseCSV();
        ProductsCSV productsFile = new ProductsCSV();
        VehiclesCSV vehiclesFile = new VehiclesCSV();
        int dimensions[] = dimensionsFile.readFromCSV();
        List<Product> productList = productsFile.readFromCSV();

        for(Product p : productList){
            p.setWeights();// gera pesos para cada item
        }

        DistributionCenter distributionCenter = new DistributionCenter(dimensions[0],dimensions[1]);
        List<Shelf> shelves = distributionCenter.getShelves();
        List<Vehicle> vehicles = vehiclesFile.readFromCSV();
        List<Position> positions = distributionCenter.getPositionsList();
        distributionCenter.setVehiclesStartingPositions(vehicles, positions);
        List packedItems = distributionCenter.packProducts(productList);
        distributionCenter.loadVehicles(packedItems, vehicles);

        //INICIO DA SIMULAÇÃO
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for(int i = 0; i < positions.size(); i++){
            Label label = new Label(positions.get(i).getName());
           // if(positions.get(i).getName() == )
            gridPane.add(label, positions.get(i).getX(), positions.get(i).getY());
        }

        Scene scene = new Scene(gridPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Distribution Center");
        primaryStage.show();
    }
}
