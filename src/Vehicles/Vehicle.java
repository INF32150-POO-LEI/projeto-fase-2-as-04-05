package Vehicles;

import Product.Bag;
import Product.Box;
import Product.Pallet;
import Vehicles.AGC;
import Vehicles.DeliveryCart;
import Vehicles.TugVehicle;
import Vehicles.ULC;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Represents a vehicle with a unique ID, vehicle type, and associated equipment.
 */

public class Vehicle {
    private String id;
    private String vehicleType;
    private AGC agc;
    private ULC ulc;
    private TugVehicle tugVehicle;
    private DeliveryCart dc;
    private boolean available;

    /**
     * Constructs a vehicle with the specified ID, AGC equipment, and vehicle type.
     *
     * @param id          the unique ID of the vehicle
     * @param agc         the AGC equipment associated with the vehicle
     * @param vehicleType the type of the vehicle
     */
    public Vehicle(String id, AGC agc, String vehicleType){
        this.available = false;
        this.id = id;
        this.vehicleType = vehicleType;
        this.agc = agc;
    }

    /**
     * Constructs a vehicle with the specified ID, tug vehicle equipment, and vehicle type.
     *
     * @param id          the unique ID of the vehicle
     * @param tugVehicle  the tug vehicle equipment associated with the vehicle
     * @param vehicleType the type of the vehicle
     */
    public Vehicle(String id, TugVehicle tugVehicle, String vehicleType){
        this.available = false;
        this.id = id;
        this.vehicleType = vehicleType;
        this.tugVehicle = tugVehicle;
    }

    /**
     * Constructs a vehicle with the specified ID, ULC equipment, and vehicle type.
     *
     * @param id          the unique ID of the vehicle
     * @param ulc         the ULC equipment associated with the vehicle
     * @param vehicleType the type of the vehicle
     */
    public Vehicle(String id, ULC ulc, String vehicleType){
        this.available = false;
        this.id = id;
        this.vehicleType = vehicleType;
        this.ulc = ulc;
    }

    /**
     * Constructs a vehicle with the specified ID, delivery cart equipment, and vehicle type.
     *
     * @param id          the unique ID of the vehicle
     * @param dc          the delivery cart equipment associated with the vehicle
     * @param vehicleType the type of the vehicle
     */
    public Vehicle(String id, DeliveryCart dc, String vehicleType){
        this.available = false;
        this.id = id;
        this.vehicleType = vehicleType;
        this.dc = dc;
    }

    /**
     * Returns the AGC equipment associated with the vehicle.
     *
     * @return the AGC equipment associated with the vehicle
     */
    public AGC getAgc() {
        return agc;
    }

    /**
     * Sets the availability status of the vehicle.
     *
     * @param available true if the vehicle is available, false otherwise
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Returns the ULC equipment associated with the vehicle.
     *
     * @return the ULC equipment associated with the vehicle
     */
    public ULC getUlc() {
        return ulc;
    }

    /**
     * Returns the tug vehicle equipment associated with the vehicle.
     *
     * @return the tug vehicle equipment associated with the vehicle
     */
    public TugVehicle getTugVehicle() {
        return tugVehicle;
    }

    /**
     * Returns the delivery cart equipment associated with the vehicle.
     *
     * @return the delivery cart equipment associated with the vehicle
     */
    public DeliveryCart getDc() {
        return dc;
    }

    /**
     * Returns the type of the vehicle.
     *
     * @return the type of the vehicle
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Returns the ID of the vehicle.
     *
     * @return the ID of the vehicle
     */
    public String getId () {
            return id;
        }

    /**
     * Checks if the vehicle is available.
     *
     * @return true if the vehicle is available, false otherwise
     */
        public boolean isAvailable () {
            return available;
        }

    /**
     * Sets the ID of the vehicle.
     *
     * @param id the ID to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the vehicle type.
     *
     * @param vehicleType the vehicle type to set
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Sets the AGC (Automated Guided Cart) for the vehicle.
     *
     * @param agc the AGC to set
     */
    public void setAgc(AGC agc) {
        this.agc = agc;
    }

    /**
     * Sets the ULC (Unit Load Carrier) for the vehicle.
     *
     * @param ulc the ULC to set
     */
    public void setUlc(ULC ulc) {
        this.ulc = ulc;
    }

    /**
     * Sets the TugVehicle for the vehicle.
     *
     * @param tugVehicle the TugVehicle to set
     */
    public void setTugVehicle(TugVehicle tugVehicle) {
        this.tugVehicle = tugVehicle;
    }

    /**
     * Sets the DeliveryCart for the vehicle.
     *
     * @param dc the DeliveryCart to set
     */
    public void setDc(DeliveryCart dc) {
        this.dc = dc;
    }


}