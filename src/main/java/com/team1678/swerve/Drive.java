package com.team1678.swerve;

import com.team254.lib.drivers.TalonFXFactory;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import com.team254.lib.geometry.Pose2d;
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
        SwerveDrive.calcSwerveWheels(x, y, rotate);
        // TODO do something
    }

    public void outputTelemetry() {
        modules.forEach(m -> m.outputTelemetry());
        SmartDashboard.putNumberArray("Robot Pose", new double[]{pose.getTranslation().x(), pose.getTranslation().y(),
                pose.getRotation().getUnboundedDegrees()});
    }
}
