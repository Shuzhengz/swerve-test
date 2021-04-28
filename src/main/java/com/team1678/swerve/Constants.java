package com.team1678.swerve;


import com.team254.lib.geometry.Pose2d;
import com.team254.lib.geometry.Rotation2d;
import com.team254.lib.geometry.Translation2d;

public class Constants {
    /* ROBOT PHYSICAL CONSTANTS */
    //Physical Robot Dimensions (including bumpers)
    public static final double kRobotWidth = 17.5;
    public static final double kRobotLength = 17.5;
    public static final double kRobotHalfWidth = kRobotWidth / 2.0;
    public static final double kRobotHalfLength = kRobotLength / 2.0;
    public static final double kRobotProbeExtrusion = 4.0;

    public static final double kWheelbaseLength = 17.8;
    public static final double kWheelbaseWidth  = 17.8;
    public static final double kSwerveDiagonal = Math.hypot(kWheelbaseLength, kWheelbaseWidth);

    //Swerve Odometry Constants
    public static final double kSwerveWheelDiameter = 4.0901; //inches (actual diamter is closer to 3.87, but secondary algorithm prefers 4.0901) 3.76
    public static final double kSwerveDriveEncoderResolution = 4096.0;

    // Tuned dynamics
    public static final double kPathFollowingMaxAccel = 80.0; // inches per second ^ 2

    public static final Pose2d kRobotLeftStartingPose = new Pose2d(new Translation2d(48.0 + kRobotHalfLength, 97.0 + kRobotHalfWidth - 162.0), Rotation2d.fromDegrees(0));

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

    //Limelight
    public static final double kMaxTrackerDistance = 15.0;
    public static final double kMaxGoalTrackAge = 30.0;
    public static final double kMaxGoalTrackSmoothingTime = 1.5;
    public static final double kCameraFrameRate = 90.0;

    //Loops
    public static final double kLooperDt = 0.01;
}
