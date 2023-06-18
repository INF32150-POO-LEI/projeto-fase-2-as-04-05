package General;
import Product.Product;
import Product.PackagingType;
import Product.ProductType;
import Product.Box;
import Product.CardBox;
import Product.Bag;
import Product.Pallet;
import Vehicles.*;
import java.util.*;

/**
 * The DistributionCenter class represents a distribution center in a logistics system.
 * It manages the storage and movement of products within the center.
 */
public class DistributionCenter {
    private int x;  // Tamanho em posições no eixo X // comprimento (width)
    private int y;  // Tamanho em posições no eixo Y // largura (length)
    private List<Position> positionsList;
    private List<Vehicle> vehicles;
    private List<Product> products;
    private List<Shelf> shelves;

    /**
     * Constructs a new DistributionCenter with the specified dimensions.
     *
     * @param x The size of the distribution center in X-axis positions (length).
     * @param y The size of the distribution center in Y-axis positions (width).
     */
    public DistributionCenter(int x, int y) {
        this.x = x; // linhas // comprimento
        this.y = y; // colunas // largura
        this.positionsList = new ArrayList<>();
        this.shelves = new ArrayList<>();
        this.vehicles = new ArrayList<>();

        createDistributionCenter();
    }

    /**
     * Packs the given list of products into appropriate packaging items.
     * Returns a list of packed items, including pallets, bags, boxes, and card boxes.
     *
     * @param products The list of products to pack.
     * @return A list of packed items.
     */
    public List packProducts(List<Product> products) {
        List packedItems = new ArrayList<>();

        for (Product p : products) {
            if (p.getProductType().equals(ProductType.CLOTHING) || p.getProductType().equals(ProductType.ACCESSORY)) {
                PackagingType[] packagingOptions = {PackagingType.BOX, PackagingType.BAG};
                Random random = new Random();
                int randomIndex = random.nextInt(packagingOptions.length);
                PackagingType chosenPackage = packagingOptions[randomIndex];

                if (chosenPackage.equals(PackagingType.BAG)) {
                    Bag bag = new Bag(2);
                    bag.pack(p);
                    packedItems.add(bag);
                    p.setPackaged(true);
                    p.setPackagingType(PackagingType.BAG);
                }
                else if (chosenPackage == PackagingType.BOX) {
                    if (!p.getProductType().equals(ProductType.CLOTHING)) {
                        Box box = new Box(5);
                        box.pack(p);
                        packedItems.add(box);
                        p.setPackaged(true);
                        p.setPackagingType(PackagingType.BOX);
                    } else {
                        Bag bag = new Bag(2);
                        bag.pack(p);
                        packedItems.add(bag);
                        p.setPackaged(true);
                        p.setPackagingType(PackagingType.BAG);
                    }
                }
            }
            if (p.getProductType().equals(ProductType.SMALL_ELECTRONIC_EQUIPMENT) || p.getProductType().equals(ProductType.SMALL_TOY)) {
                PackagingType[] packagingOptions = {PackagingType.CARD_BOX, PackagingType.BAG};
                Random random = new Random();
                int randomIndex = random.nextInt(packagingOptions.length);
                PackagingType chosenPackage = packagingOptions[randomIndex];

                if (chosenPackage.equals(PackagingType.BAG)) {
                    Bag bag = new Bag(2);
                    bag.pack(p);
                    packedItems.add(bag);
                    p.setPackaged(true);
                    p.setPackagingType(PackagingType.BAG);
                }
                else if (chosenPackage.equals(PackagingType.CARD_BOX)) {
                    boolean foundCardBox = false;
                    Iterator iterator = packedItems.iterator();
                    while (iterator.hasNext()) {
                        Object packaging = iterator.next();
                        if (packaging instanceof CardBox) {
                            CardBox cardBox = (CardBox) packaging;
                                if (cardBox.pack(p) == true) {
                                    p.setPackaged(true);
                                    p.setPackagingType(PackagingType.CARD_BOX);
                                    foundCardBox = true;
                                    break;
                                } else if (cardBox.pack(p) == false) {
                                    CardBox cardBox2 = new CardBox(10, 10);
                                    cardBox.pack(p);
                                    packedItems.add(cardBox2);
                                    p.setPackaged(true);
                                    p.setPackagingType(PackagingType.CARD_BOX);
                                    break;
                                }
                        }
                    }
                    if (!foundCardBox) {
                        CardBox cardBox = new CardBox(10, 10);
                        cardBox.pack(p);
                        packedItems.add(cardBox);
                        p.setPackaged(true);
                        p.setPackagingType(PackagingType.CARD_BOX);
                    }
                }
            }
            if (p.getProductType().equals(ProductType.BOOK)) {
                Box box = new Box(5);
                box.pack(p);
                packedItems.add(box);
                p.setPackaged(true);
                p.setPackagingType(PackagingType.BOX);
            }
            if (p.getProductType().equals(ProductType.LARGE_ELECTRONIC_EQUIPMENT) || p.getProductType().equals(ProductType.LARGE_TOY)) {
                CardBox cardBox = new CardBox(10, 1);
                cardBox.pack(p);
                packedItems.add(cardBox);
                p.setPackaged(true);
                p.setPackagingType(PackagingType.CARD_BOX);
            }
        }

        addCardBoxesToPallets(packedItems);
        return packedItems;
    }

    public void addCardBoxesToPallets(List<Object> packedItems) {
        if (countCardBoxes(packedItems) > 0) {
            Pallet pallet = new Pallet(100, 10);
            for (Object o : new ArrayList<>(packedItems)) {
                if (o instanceof CardBox) {
                    CardBox c = (CardBox) o;
                    if (!c.getPutIntoPallet()) {
                        if (pallet.pack(c)) {
                            c.putIntoPallet(true);
                            packedItems.remove(c);
                        } else {
                            packedItems.add(pallet);
                            addCardBoxesToPallets(packedItems);
                            break;
                        }
                    }
                }
            }
        }
    }



    public int countCardBoxes(List packedItems){
         int total = 0;
         for(Object o : packedItems){
             if (o instanceof CardBox){
                 total++;
             }
         }
         return total;
     }
    /**
     * Creates the distribution center by initializing the positions and shelves within it.
     * This method sets up the layout of the distribution center with walls, shelves, floors, and designated areas.
     * It also populates the shelves list with the created shelves.
     * Lastly, it converts the position matrix to a list and prints the storage matrix.
     */
    public void createDistributionCenter(){
        Position[][] positions;
        positions = new Position[y][x]; // linhas | colunas

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (i == 0 || i == y-1) {
                    positions[i][j] = new Position("Wall", j, i);
                    if (i == 0 && j == x / 2) {
                        positions[i][j] = new Position("Exit", j, i);
                    }
                    if (i == y - 1 && j == x / 2) {
                        positions[i][j] = new Position("Entry", j, i);
                    }
                } else if (i == 1 || i == y-2) {
                    if (j == x / 2) {
                        positions[i][j] = new Position("Floor", j, i);
                    } else if ((j != 1 || j != x-1) && (i != 0 || i != y-1)) {
                        positions[i][j] = new Position("Shelf", j, i);
                        Shelf shelf = new Shelf(positions[i][j]);
                        shelves.add(shelf);
                        if (j == 0 || j == x-1) {
                            positions[i][j] = new Position("Wall", j, i);
                        }
                    }
                } else if (j == 0) {
                    positions[i][j] = new Position("Collect", j, i);
                } else if (j == x-1) {
                    positions[i][j] = new Position("Delivery", j, i);
                }else {
                    positions[i][j] = new Position("Floor", j, i);
                }
            }
        }
        positionsList = positionMatrixToList(positions);
        printStorageMatrix(positions);
    }

    /**
     * Converts a position matrix to a list of positions.
     *
     * @param position The position matrix to be converted.
     * @return A list of positions obtained from the position matrix.
     */
    public List<Position> positionMatrixToList(Position position[][]){
        List<Position> positionsList = new ArrayList<>();
        for (int i = 0; i < position.length; i++){
            for (int j = 0; j < position[i].length; j++){
                positionsList.add(position[i][j]);
            }
        }
        return positionsList;
    }
    /**
     * Prints the storage matrix.
     *
     * @param matrix The storage matrix to be printed.
     */
    public static void printStorageMatrix(Position[][] matrix) {
        int width = 10;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("[%d][%d]" + "%-" + width + "s ",  matrix[i][j].getX(), matrix[i][j].getY(), matrix[i][j].getName());
            }
            System.out.println(); // Move to the next line after printing a row
        }
    }

    /**
     * Sets the starting positions for the vehicles.
     *
     * @param vehiclesList   The list of vehicles.
     * @param positionsList  The list of positions.
     * @return The updated list of vehicles with their starting positions set.
     */
    public void setVehiclesStartingPositions(List<Vehicle> vehiclesList, List<Position> positionsList) {
        for (Vehicle v : vehiclesList) {
            for (Position p : positionsList) {
                if (p.getName().equals("Collect") && p.addToPosition(v)) {
                    v.setCurrentPosition(p);
                    v.setAvailableStatus(true);
                    break;
                }
            }
        }
    }

    /**
     * Prints the number of available vehicles and their types.
     *
     * @param vehicles The list of vehicles.
     * @return A string representation of the number of available vehicles and their types.
     */
    public String printHowManyAvailableVehicles(List<Vehicle>  vehicles){
        int available = 0;
        int ulc = 0, deliveryCart = 0, tugVehicle = 0, agc = 0;
        for (Vehicle v : vehicles){
            if(v.isAvailable()){
                available++;

                if(v instanceof ULC){
                    ulc++;
                }
                if(v instanceof DeliveryCart){
                    deliveryCart++;
                }
                if(v instanceof TugVehicle){
                    tugVehicle++;
                }
                if(v instanceof AGC){
                    agc++;
                }
            }
        }
        return "- Estão disponiveis para uso: " + available + " veiculos" +
                "\nSendo estes: \n" + ulc + " do tipo ULC" +
                "\n" + deliveryCart + " do tipo delivery cart" +
                "\n" + tugVehicle + " do tipo tug vehicle" +
                "\n" + agc + " do tipo AGC";
    }

    public Vehicle findVehicle(List<Vehicle> vehicles, Class<?> vehicleType) {
        Optional<Vehicle> optionalVehicle = vehicles.stream()
                .filter(vehicle -> vehicleType.isInstance(vehicle) && vehicle.isAvailable())
                .findFirst();

        // Check if the vehicle was found
        if (optionalVehicle.isPresent()) {
            return optionalVehicle.get(); // Return the found vehicle
        } else {
            return null; // Vehicle not found
        }
    }

    /**
     * Loads the packed items onto the available vehicles based on their types.
     *
     * @param packedItems  The list of packed items to be loaded onto vehicles.
     * @param vehicles     The list of vehicles to load the items onto.
     */

    public void loadVehicles(List packedItems, List<Vehicle> vehicles) {
        int currentItemsPacked = 0;
        Vehicle foundVehicle = null;

                for (int i = 0; i < packedItems.size(); i++) {
                    if (currentItemsPacked >= packedItems.size()) {
                        break;
                    }

                    Object item = packedItems.get(i);
                    if (item instanceof Pallet) {
                            foundVehicle = findVehicle(vehicles, ULC.class);
                            if(foundVehicle != null) {
                                if (((ULC) foundVehicle).addPallet((Pallet) item)) {
                                    currentItemsPacked++;
                                }
                            }
                            continue;
                        }
                    if (item instanceof Bag) {
                        String[] choices = {"DeliveryCart", "AGC"};
                        Random random = new Random();
                        String randomChoice = choices[random.nextInt(choices.length)];

                        if (randomChoice.equals("DeliveryCart")) {
                            foundVehicle = findVehicle(vehicles, DeliveryCart.class);
                            if(foundVehicle != null) {
                                    if (((DeliveryCart) foundVehicle).addBag((Bag) item)) {
                                        currentItemsPacked++;
                                    }
                              }
                            continue;
                            }
                        else if (randomChoice.equals("AGC")) {
                            foundVehicle = findVehicle(vehicles, AGC.class);
                            if(foundVehicle != null) {
                                    if (((AGC) foundVehicle).addBag((Bag) item)) {
                                        currentItemsPacked++;
                                    }
                              }
                            continue;
                            }
                        }
                    if (item instanceof Box) {
                        String[] choices = {"DeliveryCart", "AGC"};
                        Random random = new Random();
                        String randomChoice = choices[random.nextInt(choices.length)];

                        if (randomChoice.equals("DeliveryCart")) {
                            foundVehicle = findVehicle(vehicles, DeliveryCart.class);
                            if(foundVehicle != null) {
                                    ((DeliveryCart) foundVehicle).addBox((Box) item);
                                    currentItemsPacked++;
                                }
                            continue;
                            }
                        else if (randomChoice.equals("AGC")) {
                            foundVehicle = findVehicle(vehicles, AGC.class);
                            if (foundVehicle != null) {
                                    ((AGC) foundVehicle).addBox((Box) item);
                                    currentItemsPacked++;
                             }
                            continue;
                           }
                        }
                    }


        for(Vehicle v2 : vehicles){
            if(v2 instanceof TugVehicle){
                if(v2.isAvailable()){
                    ((TugVehicle) v2).putDCIntoTugVehicle(vehicles);
                }
            }
        }
        System.out.println("- Os veiculos disponiveis foram carregados com as respetivas embalagens/critérios."
                +"\n- Sobraram: " + (packedItems.size()-currentItemsPacked) + " embalagens.");
    }

    /**
     * Counts the number of packed items of each type and returns the results as a formatted string.
     *
     * @param packedItems  The list of packed items to count.
     * @return A formatted string displaying the count of each packed item type.
     */
    public String countPackedItemsResults(List packedItems){
        int pallets = 0;
        int boxes = 0;
        int bags = 0;

        for (Object item : packedItems){
            if(item instanceof Pallet){
                pallets++;
            }
            if(item instanceof Box){
                boxes++;
            }
            if(item instanceof Bag){
                bags++;
            }
        }
        return "\nTotal de embalagens: " + (pallets+boxes+bags) + "\n" + pallets + " pallets (Cada pallet tem 100kg de peso máximo e um máximo de 10 caixas de madeira tendo cada caixa de madeira presente um peso máximo de 10kg)" + "\n" + bags + " bags (Cada bag tem um peso máximo de 2kg)" + "\n" + boxes + " boxes (Cada caixa tem um máximo de 5kg)" + "\n";
    }

    /**
     * Returns the x (width) of the distribution center.
     *
     * @return The x (width) of the position.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x (width) of the distribution center.
     *
     * @param x (width) of the distribution center to set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y-coordinate of the position.
     *
     * @return The y-coordinate of the position.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the position.
     *
     * @param y The new y-coordinate value.
     */

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the list of shelves in the distribution center.
     *
     * @return The list of shelves.
     */
    public List<Shelf> getShelves() {
        return shelves;
    }

    /**
     * Returns the list of positions in the distribution center.
     *
     * @return The list of positions.
     */
    public List<Position> getPositionsList() {
        return positionsList;
    }

    /**
     * Returns the list of vehicles.
     *
     * @return The list of vehicles.
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * Gets the list of products.
     *
     * @return the list of products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Sets the list of positions.
     *
     * @param positionsList the list of positions to set
     */
    public void setPositionsList(List<Position> positionsList) {
        this.positionsList = positionsList;
    }

    /**
     * Sets the list of vehicles.
     *
     * @param vehicles the list of vehicles to set
     */
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * Sets the list of products.
     *
     * @param products the list of products to set
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Sets the list of shelves.
     *
     * @param shelves the list of shelves to set
     */
    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }
}