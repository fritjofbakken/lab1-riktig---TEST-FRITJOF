import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class FuncDistribution {
    private ArrayList<UpdateListener> updateListeners;
    private int carBoundX;
    private int carBoundY;
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    protected Timer timer = new Timer(delay, new TimerListener());
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    //methods:
    public FuncDistribution(int BoundX, int BoundY){
        updateListeners = new ArrayList<>();
        carBoundX = BoundX;
        carBoundY = BoundY;
    }
    public void AddUpdateListener(UpdateListener newUpdateListener){
        updateListeners.add(newUpdateListener);
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                car.move(carBoundX, carBoundY);
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                for (UpdateListener listener : updateListeners){
                    listener.updateSent(x, y, car.getModelName());
                }
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    void stopAllCars() {
        for (Vehicle car : cars) {car.stopEngine();}
    }

    void startAllCars() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }
    void turnLeft() {
        for (Vehicle car : cars) {
            car.turnLeft(Math.PI/10);
        }
    }
    void turnRight() {
        for (Vehicle car : cars) {
            car.turnRight(Math.PI/10);
        }
    }

    void saabTurboOn(){
        for (Vehicle car : cars) {
            if (car instanceof Saab95){((Saab95) car).setTurboOn();}
        }
    }
    void saabTurboOff(){
        for (Vehicle car : cars){
            if (car instanceof Saab95){((Saab95) car).setTurboOff();}
        }
    }
    void scaniaLiftBed(){
        for (Vehicle car : cars){
            if (car instanceof Scania){((Scania) car).tilt(Math.toRadians(70));}
        }
    }
    void scaniaLowerBed(){
        for (Vehicle car : cars){
            if (car instanceof Scania){((Scania) car).tilt(0);}
        }
    }
    //void addCar(){
        //cars.add();}





}