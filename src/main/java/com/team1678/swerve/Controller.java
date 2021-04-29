package com.team1678.swerve;

import edu.wpi.first.wpilibj.XboxController;

public class Controller extends XboxController {

    private double deadBand = 0.15;

    public void setDeadBand(double deadBand){
        this.deadBand = deadBand;
    }

    /**
     * Construct an instance of a joystick. The joystick index is the USB port on the drivers station.
     *
     * @param usb The port on the Driver Station that the joystick is plugged into.
     */
    public Controller(int usb) {
        super(usb);
    }

    @Override
    public double getX(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return deadBand(getRawAxis(0), deadBand);
        } else {
            return deadBand(getRawAxis(4), deadBand);
        }
    }
    @Override
    public double getY(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return deadBand(getRawAxis(1), deadBand);
        } else {
            return deadBand(getRawAxis(5), deadBand);
        }
    }

    public static double deadBand(double val, double deadBand){
        return (Math.abs(val) > Math.abs(deadBand)) ? val : 0.0;
    }
}
