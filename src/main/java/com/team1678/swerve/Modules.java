package com.team1678.swerve;

import com.team1678.swerve.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;

import com.team254.lib.drivers.LazyTalonFX;

public class Modules {

    PeriodicIO periodicIO = new PeriodicIO();

    public static class PeriodicIO{
        //Inputs
        public int velocity = 0;

        //Outputs
        public ControlMode rotationControlMode = ControlMode.PercentOutput;
        public ControlMode driveControlMode = ControlMode.PercentOutput;
        public double rotationDemand;
        public double driveDemand;
    }
}
