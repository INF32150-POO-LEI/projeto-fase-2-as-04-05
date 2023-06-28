package General;

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

        //INICIO DA SIMULAÇÃO
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        DistributionCenter distributionCenter = new DistributionCenter(dimensions[0],dimensions[1]);
        Position[][] positions = distributionCenter.createDistributionCenter();

        for (int i = 0; i < dimensions[1]; i++) {
            for (int j = 0; j < dimensions[0]; j++) {
                Label label = new Label(positions[i][j].getName());
                gridPane.add(label, j, i);
            }
        }

        Scene scene = new Scene(gridPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Distribution Center");
        primaryStage.show();
    }
}
