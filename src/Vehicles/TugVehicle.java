package Vehicles;
import General.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tug vehicle.
 */
public class TugVehicle{
    private List<DeliveryCart> towedAGCList;
    private Position currentPosition;

    /**
     * Constructs a new TugVehicle object with the specified current position.
     *
     * @param currentPosition the current position of the tug vehicle
     */
    public TugVehicle(Position currentPosition) {
        this.towedAGCList = new ArrayList<>();
        this.currentPosition = currentPosition;
    }

    /**
     * Puts the delivery carts from the given list of vehicles into the tug vehicle.
     *
     * @param vehicles the list of vehicles
     */
    public void putDCIntoTugVehicle(List<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleType().equals("DeliveryCart") && v.isAvailable()) {
                  if(v.getDc().getCurrentCargo().size() > 0 && towedAGCList.size() == 0){
                    towedAGCList.add(v.getDc());
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
     * Gets the current position of the tug vehicle.
     *
     * @return the current position
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Sets the list of towed AGCs (Automated Guided Carts).
     *
     * @param towedAGCList the list of towed AGCs to be set
     */
    public void setTowedAGCList(List<DeliveryCart> towedAGCList) {
        this.towedAGCList = towedAGCList;
    }

    /**
     * Sets the current position of the AGC.
     *
     * @param currentPosition the current position to be set
     */
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }


}