package com.team1678.swerve;

import edu.wpi.first.wpilibj.XboxController;

public class Controller extends XboxController {

    private double DEAD_BAND = 0.15;

    public void setDeadband(double deadband){
        DEAD_BAND = deadband;
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
            return deadBand(getRawAxis(0), DEAD_BAND);
        } else {
            return deadBand(getRawAxis(4), DEAD_BAND);
        }
    }
    @Override
    public double getY(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return deadBand(getRawAxis(1), DEAD_BAND);
        } else {
            return deadBand(getRawAxis(5), DEAD_BAND);
        }
    }

    public static double deadBand(double val, double deadband){
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }
}
