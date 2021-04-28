package com.team1678.swerve;

import com.ctre.phoenix.motorcontrol.*;

import com.team254.lib.drivers.LazyTalonFX;
import com.team254.lib.geometry.Rotation2d;
import com.team254.lib.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Modules {
    String kModuleName = "Module ";

    private static LazyTalonFX rotationMotor;
    private static LazyTalonFX driveMotor;

    private int moduleID;
    private int encoderOffset;

    //Robot Positions
    private Translation2d startingPosition;
    private Translation2d position;

    PeriodicIO periodicIO = new PeriodicIO();

    public Modules(int rotationSlot, int driveSlot, int moduleID,
                             int encoderOffset, Translation2d startingPose){
        kModuleName += (moduleID + " ");
        rotationMotor = new LazyTalonFX(rotationSlot);
        driveMotor = new LazyTalonFX(driveSlot);

        motorConfig();

        this.moduleID = moduleID;
        this.encoderOffset = encoderOffset;
        position = startingPose;
        this.startingPosition = startingPose;
        getAngleRAW();
    }

    private void motorConfig(){
        //Config Drive Motors
        driveMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 10);
        driveMotor.setInverted(true);
        driveMotor.setSensorPhase(true);
        driveMotor.setSelectedSensorPosition(0, 0, 10);
        driveMotor.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 10, 10);
        driveMotor.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms, 10);
        driveMotor.configVelocityMeasurementWindow(32, 10);
        driveMotor.configNominalOutputForward(1.5/12.0, 10);
        driveMotor.configNominalOutputReverse(-1.5/12.0, 10);
        driveMotor.configVoltageCompSaturation(12.0, 10);
        driveMotor.enableVoltageCompensation(true);
        driveMotor.configOpenloopRamp(0.25, 10);
        driveMotor.configClosedloopRamp(0.0);
        driveMotor.configAllowableClosedloopError(0, 0, 10);
        driveMotor.setNeutralMode(NeutralMode.Brake);

        //Config Rotational Motors
        rotationMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 10);
        rotationMotor.setSensorPhase(true);
        rotationMotor.setInverted(false);
        rotationMotor.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 10, 10);
        rotationMotor.setNeutralMode(NeutralMode.Brake);
        rotationMotor.configVoltageCompSaturation(7.0, 10);
        rotationMotor.enableVoltageCompensation(true);
        rotationMotor.configAllowableClosedloopError(0, 0, 10);
        rotationMotor.selectProfileSlot(0, 0);

        rotationMotor.set(ControlMode.MotionMagic, rotationMotor.getSelectedSensorPosition(0));
        rotationMotor.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
        rotationMotor.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);

        rotationMotor.config_kP(0, 6.0, 10);
        rotationMotor.config_kI(0, 0.0, 10);
        rotationMotor.config_kD(0, 160.0, 10);
        rotationMotor.config_kF(0, 1023.0/Constants.kRotationMaxSpeed, 10);
    }

    private double getAngleRAW(){
        return periodicIO.rotationPosition/Constants.kEncoderResolution*360.0;
    }

    public Rotation2d getModuleAngle(){
        return Rotation2d.fromDegrees(getAngleRAW() - encoderOffset/Constants.kEncoderResolution*360.0);
    }

    public double encoderVelocityToInchesPerSecond(double encUnitsPer100ms){
        return encoderUnitsToInches(encUnitsPer100ms) * 10;
    }

    public double encoderUnitsToInches(double encUnits){
        return encUnits/Constants.kSwerveEncUnitsPerInch;
    }

    public synchronized void setNominalDriveOutput(double voltage){
        driveMotor.configNominalOutputForward(voltage / 12.0, 10);
        driveMotor.configNominalOutputReverse(-voltage / 12.0, 10);
    }

    public void outputTelemetry() {
        SmartDashboard.putNumber(kModuleName + "Angle", getModuleAngle().getDegrees());
        SmartDashboard.putNumber(kModuleName + "Velocity", encoderVelocityToInchesPerSecond(periodicIO.velocity));
    }


    public static class PeriodicIO{
        //Inputs
        public int velocity = 0;
        public int rotationPosition = 0;

        //Outputs
        public ControlMode rotationControlMode = ControlMode.PercentOutput;
        public ControlMode driveControlMode = ControlMode.PercentOutput;
        public double rotationDemand;
        public double driveDemand;
    }
}
