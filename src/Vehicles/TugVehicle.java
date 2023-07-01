package Vehicles;
import General.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tug vehicle.
 */
public class TugVehicle extends Vehicle{
    private DeliveryCart towedAGC;

    /**
     * Constructs a new TugVehicle object with the specified current position.
     *
     */
    public TugVehicle() {
        this.towedAGC = null;
    }

    /**
     * Puts the delivery carts from the given list of vehicles into the tug vehicle.
     *
     * @param vehicles the list of vehicles
     */
    public void putDCIntoTugVehicle(List<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
            if (v instanceof DeliveryCart && v.isAvailable()) {
                  if(((DeliveryCart) v).getCurrentCargo().size() >= 0 && ((DeliveryCart) v).isTugged() == false && getTowedAGC() == null) {
                    setTowedAGC((DeliveryCart) v);
                    ((DeliveryCart) v).setTugged(true);
                  }
            }
        }
    }

    @Override
    public String toString() {
        return "TUG";
    }

    @Override
    public int getCargoQuantity(){
        if(getTowedAGC() != null){
            return 1;
        }
        else{
            return 0;
        }
    }

    public DeliveryCart getTowedAGC() {
        return towedAGC;
    }

    public void setTowedAGC(DeliveryCart towedAGC) {
        this.towedAGC = towedAGC;
    }
}