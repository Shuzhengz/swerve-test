package com.team1678.swerve;

import com.team254.lib.drivers.TalonFXFactory;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import com.team254.lib.geometry.Pose2d;
import com.team254.lib.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;
import java.util.List;

public class Drive {

    private static Drive mInstance = new Drive();

    //Modules
    public Modules frontRight, frontLeft, rearLeft, rearRight;
    List<Modules> modules;

    private Pose2d pose;
    private double distanceTraveled;
    private double currentVelocity = 0;
    private double lastUpdateTimestamp = 0;

    private double[] swerveDriveAngles;
    private double[] swerveDriveSpeeds;

    public Pose2d getPose(){
        return pose;
    }

    Drive() {
        frontRight = new Modules(Constants.FRONT_RIGHT_ROTATION, Constants.FRONT_RIGHT_DRIVE,
                0, Constants.kFrontRightEncoderStartingPos, Constants.kVehicleToModuleZero);
        frontLeft = new Modules(Constants.FRONT_LEFT_ROTATION, Constants.FRONT_LEFT_DRIVE,
                1, Constants.kFrontLeftEncoderStartingPos, Constants.kVehicleToModuleOne);
        rearLeft = new Modules(Constants.REAR_LEFT_ROTATION, Constants.REAR_LEFT_DRIVE,
                2, Constants.kRearLeftEncoderStartingPos, Constants.kVehicleToModuleTwo);
        rearRight = new Modules(Constants.REAR_RIGHT_ROTATION, Constants.REAR_RIGHT_DRIVE,
                3, Constants.kRearRightEncoderStartingPos, Constants.kVehicleToModuleThree);
    }

    public static Drive getInstance() {
        return mInstance;
    }

    public void setDriveOutput(double voltage) {
        modules.forEach(m -> m.setNominalDriveOutput(voltage));
    }

    public void readInput(double x, double y, double rotate) {
        swerveDriveAngles = SwerveDrive.calcSwerveWheelAngle(x, y, rotate);
        swerveDriveSpeeds = SwerveDrive.calcSwerveWheelSpeed(x, y, rotate);

        driveRobot(rearRight, swerveDriveAngles[0], swerveDriveSpeeds[0]);
        driveRobot(rearLeft, swerveDriveAngles[1], swerveDriveSpeeds[1]);
        driveRobot(frontRight, swerveDriveAngles[2], swerveDriveSpeeds[2]);
        driveRobot(frontLeft, swerveDriveAngles[3], swerveDriveSpeeds[3]);
    }

    /**
     * Drives the robot
     * @param module Module number
     * @param power Rotation of angle motors in power
     * @param speed Speed of the wheel motors in speed
     */
    private void driveRobot(Modules module, double power, double speed) {
        module.setRotation(swerveModuleAngleToPower(power));
        module.setDrive(speed);
    }

    private double swerveModuleAngleToPower(double angle) {
        return angle*Constants.kSwerveModulePowerToAngle;
    }

    public synchronized void readPeriodicInputs() {
        modules.forEach(Modules::readPeriodicInputs);
    }

    public synchronized void writePeriodicOutputs() {
        modules.forEach(Modules::writePeriodicOutputs);
    }

    public void outputTelemetry() {
        modules.forEach(Modules::outputTelemetry);
        SmartDashboard.putNumberArray("Robot Pose", new double[]{pose.getTranslation().x(), pose.getTranslation().y(),
                pose.getRotation().getUnboundedDegrees()});
    }
}
