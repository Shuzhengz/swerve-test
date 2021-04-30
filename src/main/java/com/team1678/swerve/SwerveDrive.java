package com.team1678.swerve;

public class SwerveDrive extends Drive{

    private static final double K_ROBOT_LENGTH = Constants.kRobotLength;
    private static final double K_ROBOT_WIDTH = Constants.kRobotWidth;
    private static final double K_ROBOT_DIAMETER = Math.sqrt ((K_ROBOT_LENGTH * K_ROBOT_LENGTH) + (K_ROBOT_WIDTH * K_ROBOT_WIDTH));

    /**
     * Calculate the angle of the wheel based on joystick input
     * @param x1  X value of first joystick
     * @param y1  Y value of first joystick
     * @param x2  X value of second joystick
     * @return swerveWheelAngles, swerveWheelSpeeds
     */
    public static double[] calcSwerveWheelAngle(double x1, double y1, double x2) {

        double a = x1 - x2 * (K_ROBOT_LENGTH / K_ROBOT_DIAMETER);
        double b = x1 + x2 * (K_ROBOT_LENGTH / K_ROBOT_DIAMETER);
        double c = inverse(y1) - x2 * (K_ROBOT_WIDTH / K_ROBOT_DIAMETER);
        double d = inverse(y1) + x2 * (K_ROBOT_WIDTH / K_ROBOT_DIAMETER);

        return calcAngles(a, b, c, d);
     }

    /**
     * Calculate the speed of the wheel based on joystick input
     * @param x1  X value of first joystick
     * @param y1  Y value of first joystick
     * @param x2  X value of second joystick
     * @return swerveWheelAngles, swerveWheelSpeeds
     */
     public static double[] calcSwerveWheelSpeed (double x1, double y1, double x2) {

         double a = x1 - x2 * (K_ROBOT_LENGTH / K_ROBOT_DIAMETER);
         double b = x1 + x2 * (K_ROBOT_LENGTH / K_ROBOT_DIAMETER);
         double c = inverse(y1) - x2 * (K_ROBOT_WIDTH / K_ROBOT_DIAMETER);
         double d = inverse(y1) + x2 * (K_ROBOT_WIDTH / K_ROBOT_DIAMETER);

         return calcSpeeds(a, b, c, d);
     }

    private static double[] calcSpeeds(double a, double b, double c, double d) {
        double backRightSpeed = Math.sqrt ((a * a) + (d * d));
        double backLeftSpeed = Math.sqrt ((a * a) + (c * c));
        double frontRightSpeed = Math.sqrt ((b * b) + (d * d));
        double frontLeftSpeed = Math.sqrt ((b * b) + (c * c));
        return new double[] {backRightSpeed, backLeftSpeed, frontRightSpeed, frontLeftSpeed};
    }

    private static double[] calcAngles(double a, double b, double c, double d) {
        double backRightAngle = Math.atan2 (a, d) / Math.PI;
        double backLeftAngle = Math.atan2 (a, c) / Math.PI;
        double frontRightAngle = Math.atan2 (b, d) / Math.PI;
        double frontLeftAngle = Math.atan2 (b, c) / Math.PI;
        return new double[]{backRightAngle, backLeftAngle, frontRightAngle, frontLeftAngle};
    }

    /**
     * Simple inverse for doubles
     * @param num any double number
     * @return inverse of input number
     */
    private static double inverse(double num) {
        return num * -1;
    }
}
