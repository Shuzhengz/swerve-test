package com.team1678.swerve;


public class Constants {
    /* ROBOT PHYSICAL CONSTANTS */
    // Wheels
    public static final double kDriveWheelTrackWidthInches = 31.170;
    public static final double kDriveWheelDiameterInches = 5.67;
    public static final double kDriveWheelRadiusInches = kDriveWheelDiameterInches / 2.0;
    public static final double kTrackScrubFactor = 1.0; // TODO Tune me!
    // Tuned dynamics
    public static final double kRobotLinearInertia = 60.0; // kg TODO tune
    public static final double kRobotAngularInertia = 25.0; // kg m^2 TODO tune
    public static final double kRobotAngularDrag = 30.0; // N*m / (rad/sec) TODO tune
    public static final double kDriveVIntercept = 0.8; // V
    public static final double kDriveKv = 0.19; // V per rad/s
    public static final double kDriveKa = 0.012; // V per rad/s^2
    public static final double kPathKX = 4.0; // units/s per unit of error
    public static final double kPathLookaheadTime = 0.4; // seconds to look ahead along the path for steering
    public static final double kPathMinLookaheadDistance = 24.0; // inches
    public static final double kPathFollowingMaxAccel = 80.0; // inches per second ^ 2
    // PID gains for drive velocity loop
    // Units: setpoint, error, and output are in ticks per second.
    public static final double kDriveVelocityKp = 0.1;
    public static final double kDriveVelocityKi = 0.0;
    public static final double kDriveVelocityKd = 1.0;
    public static final double kDriveVelocityKf = 0.0;
    public static final int kDriveVelocityIZone = 0;
    public static final double kDrivePositionKp = 0.021;
    public static final double kDrivePositionKi = 0.0;
    public static final double kDrivePositionKd = 0.0;
    public static final double kDrivePositionKf = 0.05;
    public static final int kDrivePositionIZone = 0;
    public static final double kDriveVoltageRampRate = 0.0;

    /* I/O */
    // (Note that if multiple talons are dedicated to a mechanism, any sensors
    // are attached to the master)
    public static final int kCANTimeoutMs = 10; // use for on the fly updates
    public static final int kLongCANTimeoutMs = 100; // use for constructors

    // Drive
    public static final int kRightDriveMasterId = 3;
    public static final int kRightDriveSlaveId = 4;

    public static final int kLeftDriveMasterId = 1;
    public static final int kLeftDriveSlaveId = 2;

    public static final int kIndexerId = 5;
}
