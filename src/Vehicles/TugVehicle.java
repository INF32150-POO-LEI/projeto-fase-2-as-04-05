package Vehicles;
import General.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tug vehicle.
 */
public class TugVehicle extends Vehicle{
    private List<DeliveryCart> towedAGCList;

    /**
     * Constructs a new TugVehicle object with the specified current position.
     *
     */
    public TugVehicle() {
        this.towedAGCList = new ArrayList<>();

    }

    /**
     * Puts the delivery carts from the given list of vehicles into the tug vehicle.
     *
     * @param vehicles the list of vehicles
     */
    public void putDCIntoTugVehicle(List<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
            if (v instanceof DeliveryCart && v.isAvailable()) {
                  if(((DeliveryCart) v).getCurrentCargo().size() > 0 && getTowedAGCList().size() == 0){
                    getTowedAGCList().add((DeliveryCart) v);
                  }
            }
        }
    }

    /**
     * Gets the list of delivery carts towed by the tug vehicle.
     *
     * @return the list of delivery carts
     */
    public List<DeliveryCart> getTowedAGCList() {
        return towedAGCList;
    }

    /**
     * Sets the list of towed AGCs (Automated Guided Carts).
     *
     * @param towedAGCList the list of towed AGCs to be set
     */
    public void setTowedAGCList(List<DeliveryCart> towedAGCList) {
        this.towedAGCList = towedAGCList;
    }


}