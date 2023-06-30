package General;

import Product.Product;
import Vehicles.Vehicle;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.util.List;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AppStart extends Application {

    private StackPane whiteAreaStackPane; // Declare the white area StackPane as a field
    private Position lastClickedPosition;

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
        List packedItems = distributionCenter.packProducts(productList);
        System.out.println("\n- Numero de embalagens:" + packedItems.size());
        distributionCenter.setVehiclesStartingPositions(vehicles, positions);
        distributionCenter.loadVehicles(packedItems, vehicles);

        //INICIO DA SIMULAÇÃO
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        for (int i = 0; i < positions.size(); i++) {
            Position position = positions.get(i);
            StackPane stackPane = new StackPane(); // StackPane to hold the rectangle and text
            VBox vbox = new VBox();
            Rectangle r = createVehicleRectangle();

            // Set label text based on the position's name
            switch (position.getName()) {
                case "Wall":
                    if (position.getX() == 0 || position.getX() == dimensions[0] - 1) {
                        stackPane.getChildren().addAll(createVerticalWallRectangle(), createText("║", 12));
                    } else if (position.getY() == 0 || position.getY() == dimensions[1] - 1) {
                        stackPane.getChildren().addAll(createHorizontalWallRectangle(), createText("═", 12));
                    }
                    break;
                case "Shelf":
                    for(int j = 0; j < shelves.size(); j++){
                        if(shelves.get(j).getPosition().equals(positions.get(i))){
                            r = createProductsNumberRectangle();
                            vbox.getChildren().add(createText("Shelf", 12));
                            Text vehicleText = createText("[" + shelves.get(j).getProducts().size() + "]", 10);
                            StackPane vehicleStack = new StackPane(r, vehicleText);
                            vbox.getChildren().add(vehicleStack);
                            vbox.setAlignment(Pos.CENTER);
                            vbox.setSpacing(5);
                            StackPane stackPane2 = new StackPane(createShelfRectangle(), vbox);
                            gridPane.add(stackPane2, position.getX(), position.getY());
                        }
                    }
                    break;
                case "Collect":
                    vbox.getChildren().add(createText("Collect", 12));
                    if (position.getVehicleInPosition() != null) {
                        Text vehicleText = createText(position.getVehicleInPosition().toString() + "  [" + position.getVehicleInPosition().getCargoQuantity() + "]", 10);
                        StackPane vehicleStack = new StackPane(r, vehicleText);
                        vbox.getChildren().add(vehicleStack);
                        stackPane.setOnMouseClicked(e -> {
                            // Check if a white area is already open and close it
                            if (whiteAreaStackPane != null && gridPane.getChildren().contains(whiteAreaStackPane)) {
                                gridPane.getChildren().remove(whiteAreaStackPane);
                            }

                           // Rectangle whiteRectangle = new Rectangle(50, 50, Color.WHITE);

                            // Create buttons
                            Button button1 = new Button("↑");
                            Button button2 = new Button("↓");
                            Button button3 = new Button("→");
                            Button button4 = new Button("←");
                            button1.setStyle("-fx-font-size: 14px;");
                            button2.setStyle("-fx-font-size: 14px;");
                            button3.setStyle("-fx-font-size: 14px;");
                            button4.setStyle("-fx-font-size: 14px;");

                            // Create an HBox for the buttons and set spacing and padding
                            HBox buttonsHBox = new HBox(10);
                            buttonsHBox.setAlignment(Pos.CENTER);
                            buttonsHBox.setPadding(new Insets(10)); // Add padding to create space
                            buttonsHBox.getChildren().addAll(button1, button2, button3, button4);

                            // Create a StackPane for the white area and buttons
                           // whiteAreaStackPane = new StackPane(whiteRectangle, buttonsHBox); // Assign the StackPane to the field
                            whiteAreaStackPane = new StackPane( buttonsHBox);
                            whiteAreaStackPane.setAlignment(Pos.CENTER);

                            // Show the white area with buttons
                            gridPane.add(whiteAreaStackPane, position.getX(), position.getY()); // Add 1 to the Y position

                            // Update the last clicked position
                            lastClickedPosition = position;
                        });
                    }
                    vbox.setAlignment(Pos.CENTER);
                    vbox.setSpacing(5);

                    StackPane stackPane2 = new StackPane(createCollectRectangle(), vbox);
                    gridPane.add(stackPane2, position.getX(), position.getY());

                    // Add an empty StackPane below stackPane2 to maintain the layout
                    StackPane emptyStackPane = new StackPane();
                    gridPane.add(emptyStackPane, position.getX(), position.getY()+1); // Add 1 to the Y position
                    break;


                case "Delivery":
                    stackPane.getChildren().addAll(createDeliveryRectangle(), createText("Delivery", 12));
                    if (position.getVehicleInPosition() != null) {
                        stackPane.getChildren().add(createText(position.getVehicleInPosition().toString() + "  [" + position.getVehicleInPosition().getCargoQuantity() + "]", 10));
                    }

                    break;
                case "Entry":
                case "Exit":
                    stackPane.getChildren().addAll(createEntryExitRectangle(), createText("Door", 12));
                    if (position.getVehicleInPosition() != null) {
                        stackPane.getChildren().add(createText(position.getVehicleInPosition().toString() + "  [" + position.getVehicleInPosition().getCargoQuantity() + "]", 10));
                    }
                    break;
                default:
                    stackPane.getChildren().addAll(createFloorRectangle());
                    if (position.getVehicleInPosition() != null) {
                        stackPane.getChildren().add(createText(position.getVehicleInPosition().toString() + "  [" + position.getVehicleInPosition().getCargoQuantity() + "]", 10));
                    }
                    break;
            }

            gridPane.add(stackPane, position.getX(), position.getY());
        }

        Scene scene = new Scene(gridPane, 800, 800);
        scene.getRoot().addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            // Close the white area with buttons if clicked outside of it
            if (!(event.getTarget() instanceof Button || event.getTarget() instanceof StackPane || event.getTarget() == whiteAreaStackPane)) {
                gridPane.getChildren().remove(whiteAreaStackPane);
                lastClickedPosition = null; // Reset the last clicked position
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Distribution center");
        primaryStage.show();
    }

    private Rectangle createVerticalWallRectangle() {
        Rectangle rectangle = new Rectangle(40, 20);
        rectangle.setFill(Color.DARKGRAY);
        return rectangle;
    }

    private Rectangle createVehicleRectangle() {
        Rectangle rectangle = new Rectangle(60, 10);
        rectangle.setFill(Color.LIGHTGREEN);
        return rectangle;
    }

    private Rectangle createProductsNumberRectangle() {
        Rectangle rectangle = new Rectangle(30, 10);
        rectangle.setFill(Color.LIGHTBLUE);
        return rectangle;
    }

    private Rectangle createHorizontalWallRectangle() {
        Rectangle rectangle = new Rectangle(40, 20);
        rectangle.setFill(Color.DARKGRAY);
        return rectangle;
    }

    private Rectangle createShelfRectangle() {
        Rectangle rectangle = new Rectangle(40, 40);
        rectangle.setFill(Color.BLUE);
        return rectangle;
    }

    private Rectangle createCollectRectangle() {
        Rectangle rectangle = new Rectangle(60, 40);
        rectangle.setFill(Color.GREEN);
        return rectangle;
    }

    private Text createText(String text1, int size) {
        Text text = new Text(text1);
        text.setFont(Font.font(size));
        return text;
    }

    private Rectangle createDeliveryRectangle() {
        Rectangle rectangle = new Rectangle(60, 40);
        rectangle.setFill(Color.RED);
        return rectangle;
    }

    private Rectangle createEntryExitRectangle() {
        Rectangle rectangle = new Rectangle(40, 40);
        rectangle.setFill(Color.YELLOW);
        return rectangle;
    }

    private Rectangle createFloorRectangle() {
        Rectangle rectangle = new Rectangle(40, 40);
        rectangle.setFill(Color.WHITE);
        return rectangle;
    }



}
