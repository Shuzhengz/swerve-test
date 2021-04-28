package com.team1678.swerve;

import com.team254.lib.drivers.TalonFXFactory;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;

import com.team254.lib.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.ArrayList;
import java.util.List;

public class Drive {

    private static Drive mInstance = new Drive();

    private PeriodicIO mPeriodicIO;

    //Modules
    public Modules frontRight, frontLeft, rearLeft, rearRight;
    List<Modules> modules;
    List<Modules> positionModules;

    private Drive() {
        mPeriodicIO = new PeriodicIO();
    }

    public static Drive getInstance() {
        return mInstance;
    }

    public static class PeriodicIO {
        // INPUTS

        // OUTPUTS

    }
}
