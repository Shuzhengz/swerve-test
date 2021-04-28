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
    public static final double kWheelDiameter = 4.0901; //inches (actual diamter is closer to 3.87, but secondary algorithm prefers 4.0901) 3.76
    public static final double kEncoderResolution = 4096.0; //TODO Find actual resolution for Falcon's built in encoders
    public static final double kRotationMaxSpeed = 1250.0 * 0.8; //The 0.8 is to request a speed that is always achievable
    public static final double kEncoderToWheelRatio = 6.0;
    public static final double kSwerveEncUnitsPerWheelRev = kEncoderResolution * kEncoderToWheelRatio;
    public static final double kSwerveEncUnitsPerInch = kSwerveEncUnitsPerWheelRev / (Math.PI * kWheelDiameter);


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

    //Falcons Ports
    public static final int FRONT_RIGHT_ROTATION= 11;
    public static final int FRONT_RIGHT_DRIVE   = 10;
    public static final int FRONT_LEFT_ROTATION = 2;
    public static final int FRONT_LEFT_DRIVE    = 1;
    public static final int REAR_LEFT_ROTATION  = 4;
    public static final int REAR_LEFT_DRIVE     = 5;
    public static final int REAR_RIGHT_ROTATION = 7;
    public static final int REAR_RIGHT_DRIVE    = 6;

    //Stolen from O2019 code
    public static final int kFrontRightEncoderStartingPos = -262;
    public static final int kFrontLeftEncoderStartingPos = -2213;
    public static final int kRearLeftEncoderStartingPos = -3906;
    public static final int kRearRightEncoderStartingPos = -2398;

    public static final Translation2d kVehicleToModuleZero = new Translation2d(kWheelbaseLength/2, kWheelbaseWidth/2);
    public static final Translation2d kVehicleToModuleOne = new Translation2d(kWheelbaseLength/2, -kWheelbaseWidth/2);
    public static final Translation2d kVehicleToModuleTwo = new Translation2d(-kWheelbaseLength/2, -kWheelbaseWidth/2);
    public static final Translation2d kVehicleToModuleThree = new Translation2d(-kWheelbaseLength/2, kWheelbaseWidth/2);
}
