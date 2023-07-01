package General;

import Product.Product;
import Vehicles.TugVehicle;
import Vehicles.Vehicle;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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
            AtomicReference<Position> position = new AtomicReference<>(positions.get(i));
            AtomicReference<StackPane> stackPane = new AtomicReference<>(new StackPane()); // StackPane to hold the rectangle and text
            VBox vbox = new VBox();
            Rectangle r;

            // Set label text based on the position's name
            switch (position.get().getName()) {
                case "Wall":
                    if (position.get().getX() == 0 || position.get().getX() == dimensions[0] - 1) {
                        stackPane.get().getChildren().addAll(createVerticalWallRectangle(), createText("║", 12));
                    } else if (position.get().getY() == 0 || position.get().getY() == dimensions[1] - 1) {
                        stackPane.get().getChildren().addAll(createHorizontalWallRectangle(), createText("═", 12));
                    }
                    break;
                case "Shelf":
                    for (int j = 0; j < shelves.size(); j++) {
                        if (shelves.get(j).getPosition().equals(positions.get(i))) {
                            r = createProductsNumberRectangle();
                            vbox.getChildren().add(createText("Shelf", 12));
                            Text vehicleText = createText("[" + shelves.get(j).getProducts().size() + "]", 10);
                            StackPane vehicleStack = new StackPane(r, vehicleText);
                            vbox.getChildren().add(vehicleStack);
                            vbox.setAlignment(Pos.CENTER);
                            vbox.setSpacing(5);
                            StackPane stackPane2 = new StackPane(createShelfRectangle(), vbox);
                            gridPane.add(stackPane2, position.get().getX(), position.get().getY());
                        }
                    }
                    break;
                case "Collect":
                    vbox.getChildren().add(createText("Collect", 12));
                    vbox.setAlignment(Pos.CENTER);
                    vbox.setSpacing(5);
                    StackPane stackPane2 = new StackPane(createCollectRectangle(), vbox);
                    gridPane.add(stackPane2, position.get().getX(), position.get().getY());

                    break;
                case "Delivery":
                    stackPane.get().getChildren().addAll(createDeliveryRectangle(), createText("Delivery", 12));
                    break;
                case "Entry":
                case "Exit":
                    stackPane.get().getChildren().addAll(createEntryExitRectangle(), createText("Door", 12));
                    break;
                default:
                    break;
            }


            if (position.get().getVehicleInPosition() != null) {
                r = createVehicleRectangle();
                Text vehicleText = createText(position.get().getVehicleInPosition().toString() + "  [" + position.get().getVehicleInPosition().getCargoQuantity() + "]", 10);
                StackPane vehicleStack = new StackPane(r, vehicleText);
                vbox.getChildren().add(vehicleStack);
                stackPane.get().setOnMouseClicked(e -> {
                    if (whiteAreaStackPane != null && gridPane.getChildren().contains(whiteAreaStackPane)) {
                        gridPane.getChildren().remove(whiteAreaStackPane);
                    }
                    HBox buttonsHBox = new HBox(1);
                    Button button1 = new Button("↑");
                    Button button2 = new Button("↓");
                    Button button3 = new Button("→");
                    Button button4 = new Button("←");
                    button1.setStyle("-fx-font-size: 14px;");
                    button2.setStyle("-fx-font-size: 14px;");
                    button3.setStyle("-fx-font-size: 14px;");
                    button4.setStyle("-fx-font-size: 14px;");
                    buttonsHBox.setAlignment(Pos.CENTER);
                    buttonsHBox.setPadding(new Insets(10));
                    buttonsHBox.getChildren().addAll(button1, button2, button3, button4);
                    whiteAreaStackPane = new StackPane(buttonsHBox);
                    whiteAreaStackPane.setAlignment(Pos.CENTER);
                    gridPane.add(whiteAreaStackPane, position.get().getX(), position.get().getY());


                    Vehicle v = position.get().getVehicleInPosition();
                    button1.setOnAction(event -> {
                        Position newPosition = position.get().getVehicleInPosition().moveUpwards(positions);

                        if(newPosition.getName().equals("Shelf")){

                            Shelf foundShelf = shelves.stream()
                                    .filter(shelf -> shelf.getPosition().equals(newPosition))
                                    .findFirst()
                                    .orElse(null);

                            if(foundShelf != null){
                                if(v.getCargoQuantity() == 0){
                                    openPopup("Error: this vehicle is empty");
                                }
                                else{
                                    v.loadShelf(foundShelf);
                                    openPopup("Shelf loaded");

                                    VBox vbox1 = new VBox();
                                    Rectangle r2 = createProductsNumberRectangle();
                                    vbox1.getChildren().add(createText("Shelf", 12));
                                    Text vehicleText2 = createText("[" + foundShelf.getProducts().size() + "]", 10);
                                    StackPane vehicleStack2 = new StackPane(r2, vehicleText2);
                                    vbox1.getChildren().add(vehicleStack2);
                                    vbox1.setAlignment(Pos.CENTER);
                                    vbox1.setSpacing(5);
                                    StackPane stackPane2 = new StackPane(createShelfRectangle(), vbox1);

                                    gridPane.add(stackPane2, newPosition.getX(), newPosition.getY());
                                    vehicleText.setText(v.toString() + "  [" + v.getCargoQuantity() + "]");
                                }
                            }

                        }

                        if (newPosition.getName().equals("Floor") || newPosition.getName().equals("Delivery") || newPosition.getName().equals("Collect")) {
                            position.set(newPosition.getVehicleInPosition().getCurrentPosition());
                            gridPane.getChildren().remove(stackPane.get());
                            StackPane stackPane1 = new StackPane();
                            stackPane1.setOnMouseClicked(stackPane.get().getOnMouseClicked());
                            stackPane.set(stackPane1);
                            stackPane.get().getChildren().addAll(vehicleStack, vehicleText);
                            gridPane.add(stackPane.get(), newPosition.getX(), newPosition.getY());

                        }

                        if(newPosition.getName().equals("Exit")|| newPosition.getName().equals("Entry")) {
                            openPopup("Error: You can't move the vehicle to the door");
                        }


                    });

                    button2.setOnAction(event -> {
                        Position newPosition = position.get().getVehicleInPosition().moveDownwards(positions);

                        if(newPosition.getName().equals("Shelf")){

                            Shelf foundShelf = shelves.stream()
                                    .filter(shelf -> shelf.getPosition().equals(newPosition))
                                    .findFirst()
                                    .orElse(null);

                            if(foundShelf != null){
                                if(v.getCargoQuantity() == 0){
                                    openPopup("Error: this vehicle is empty");
                                }
                                else{
                                    v.loadShelf(foundShelf);
                                    openPopup("Shelf loaded");

                                    VBox vbox1 = new VBox();
                                    Rectangle r2 = createProductsNumberRectangle();
                                    vbox1.getChildren().add(createText("Shelf", 12));
                                    Text vehicleText2 = createText("[" + foundShelf.getProducts().size() + "]", 10);
                                    StackPane vehicleStack2 = new StackPane(r2, vehicleText2);
                                    vbox1.getChildren().add(vehicleStack2);
                                    vbox1.setAlignment(Pos.CENTER);
                                    vbox1.setSpacing(5);
                                    StackPane stackPane2 = new StackPane(createShelfRectangle(), vbox1);

                                    gridPane.add(stackPane2, newPosition.getX(), newPosition.getY());
                                    vehicleText.setText(v.toString() + "  [" + v.getCargoQuantity() + "]");
                                }
                            }
                        }

                        if (newPosition.getName().equals("Floor") || newPosition.getName().equals("Delivery") || newPosition.getName().equals("Collect")) {
                            position.set(newPosition.getVehicleInPosition().getCurrentPosition());
                            gridPane.getChildren().remove(stackPane.get());
                            StackPane stackPane1 = new StackPane();
                            stackPane1.setOnMouseClicked(stackPane.get().getOnMouseClicked());
                            stackPane.set(stackPane1);
                            stackPane.get().getChildren().addAll(vehicleStack, vehicleText);
                            gridPane.add(stackPane.get(), newPosition.getX(), newPosition.getY());
                        }

                        if(newPosition.getName().equals("Exit")|| newPosition.getName().equals("Entry")) {
                            openPopup("Error: You can't move the vehicle to the door");
                        }


                    });

                    button3.setOnAction(event -> {
                       Position newPosition = position.get().getVehicleInPosition().moveRight(positions);

                        if(newPosition.getName().equals("Shelf")){

                            Shelf foundShelf = shelves.stream()
                                    .filter(shelf -> shelf.getPosition().equals(newPosition))
                                    .findFirst()
                                    .orElse(null);

                            if(foundShelf != null){
                                if(v.getCargoQuantity() == 0){
                                    openPopup("Error: this vehicle is empty");
                                }
                                else{
                                    v.loadShelf(foundShelf);
                                    openPopup("Shelf loaded");

                                    VBox vbox1 = new VBox();
                                    Rectangle r2 = createProductsNumberRectangle();
                                    vbox1.getChildren().add(createText("Shelf", 12));
                                    Text vehicleText2 = createText("[" + foundShelf.getProducts().size() + "]", 10);
                                    StackPane vehicleStack2 = new StackPane(r2, vehicleText2);
                                    vbox1.getChildren().add(vehicleStack2);
                                    vbox1.setAlignment(Pos.CENTER);
                                    vbox1.setSpacing(5);
                                    StackPane stackPane2 = new StackPane(createShelfRectangle(), vbox1);

                                    gridPane.add(stackPane2, newPosition.getX(), newPosition.getY());
                                    vehicleText.setText(v.toString() + "  [" + v.getCargoQuantity() + "]");
                                }
                            }
                        }

                        if (newPosition.getName().equals("Floor") || newPosition.getName().equals("Delivery") || newPosition.getName().equals("Collect")) {
                            position.set(newPosition.getVehicleInPosition().getCurrentPosition());
                            gridPane.getChildren().remove(stackPane.get());
                            StackPane stackPane1 = new StackPane();
                            stackPane1.setOnMouseClicked(stackPane.get().getOnMouseClicked());
                            stackPane.set(stackPane1);
                            stackPane.get().getChildren().addAll(vehicleStack, vehicleText);
                            gridPane.add(stackPane.get(), newPosition.getX(), newPosition.getY());
                        }

                        if(newPosition.getName().equals("Exit")|| newPosition.getName().equals("Entry")) {
                            openPopup("Error: You can't move the vehicle to the door");
                        }





                    });

                    button4.setOnAction(event -> {
                        Position newPosition = position.get().getVehicleInPosition().moveLeft(positions);

                        if(newPosition.getName().equals("Shelf")){

                            Shelf foundShelf = shelves.stream()
                                    .filter(shelf -> shelf.getPosition().equals(newPosition))
                                    .findFirst()
                                    .orElse(null);

                            if(foundShelf != null){
                                if(v.getCargoQuantity() == 0){
                                    openPopup("Error: this vehicle is empty");
                                }
                                else{
                                    v.loadShelf(foundShelf);
                                    openPopup("Shelf loaded");

                                    VBox vbox1 = new VBox();
                                    Rectangle r2 = createProductsNumberRectangle();
                                    vbox1.getChildren().add(createText("Shelf", 12));
                                    Text vehicleText2 = createText("[" + foundShelf.getProducts().size() + "]", 10);
                                    StackPane vehicleStack2 = new StackPane(r2, vehicleText2);
                                    vbox1.getChildren().add(vehicleStack2);
                                    vbox1.setAlignment(Pos.CENTER);
                                    vbox1.setSpacing(5);
                                    StackPane stackPane2 = new StackPane(createShelfRectangle(), vbox1);

                                    gridPane.add(stackPane2, newPosition.getX(), newPosition.getY());
                                    vehicleText.setText(v.toString() + "  [" + v.getCargoQuantity() + "]");
                                }
                            }
                        }

                        if (newPosition.getName().equals("Floor") || newPosition.getName().equals("Delivery") || newPosition.getName().equals("Collect")) {
                            position.set(newPosition.getVehicleInPosition().getCurrentPosition());
                            gridPane.getChildren().remove(stackPane.get());
                            StackPane stackPane1 = new StackPane();
                            stackPane1.setOnMouseClicked(stackPane.get().getOnMouseClicked());
                            stackPane.set(stackPane1);
                            stackPane.get().getChildren().addAll(vehicleStack, vehicleText);
                            gridPane.add(stackPane.get(), newPosition.getX(), newPosition.getY());
                        }

                        if(newPosition.getName().equals("Exit")|| newPosition.getName().equals("Entry")) {
                            openPopup("Error: You can't move the vehicle to the door");
                        }


                    });
                    lastClickedPosition = position.get();
                });
            }

            gridPane.add(stackPane.get(), position.get().getX(), position.get().getY());

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
        rectangle.setFill(Color.LIGHTGREY);
        return rectangle;
    }

    public static void openPopup(String message) {
        // Create a new stage for the popup
        Stage popupStage = new Stage();

        // Set the modality so that the popup blocks interactions with the main window
        popupStage.initModality(Modality.APPLICATION_MODAL);

        // Create the content of the popup
        Label label = new Label(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> popupStage.close());

        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        content.getChildren().addAll(label, closeButton);

        // Create a scene with the content and set it to the popup stage
        Scene scene = new Scene(content);
        popupStage.setScene(scene);

        // Set the title of the popup window
        popupStage.setTitle("Popup");

        // Show the popup
        popupStage.showAndWait();
    }

}