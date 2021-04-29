// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team1678.swerve;

import com.team1678.swerve.loops.Looper;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private Controller controller;

  private Looper enabledLooper = new Looper();
  private Looper disabledLooper = new Looper();

  private Drive drive = Drive.getInstance();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);



    controller = new Controller(0);
    controller.setDeadBand(0.0);

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    enabledLooper.outputToSmartDashboard();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */

  // No Auto
  //
  // @Override
  //  public void autonomousInit() {
  //    m_autoSelected = m_chooser.getSelected();
  //    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
  //    System.out.println("Auto selected: " + m_autoSelected);
  //  }
  //
  //  /** This function is called periodically during autonomous. */
  //  @Override
  //  public void autonomousPeriodic() {
  //    switch (m_autoSelected) {
  //      case kCustomAuto:
  //        // Put custom auto code here
  //        break;
  //      case kDefaultAuto:
  //      default:
  //        // Put default auto code here
  //        break;
  //    }
  //  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    try {
      disabledLooper.stop();
      enabledLooper.start();
      drive.setDriveOutput(0.0);
      SmartDashboard.putBoolean("Auto", false);
    } catch (Exception t) {
      System.out.println(t);
      throw t;
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    try {
      normalMode();

      drive.outputTelemetry();
      enabledLooper.outputToSmartDashboard();
    } catch (Throwable t) {
      System.out.println(t);
      throw t;
    }
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    try {
      enabledLooper.stop();
      disabledLooper.start();
    } catch (Throwable t) {
      System.out.println(t);
      throw t;
    }
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
    try {
      enabledLooper.outputToSmartDashboard();
    } catch (Throwable t) {
      System.out.println(t);
      throw t;
    }
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  private void normalMode() {
    double swerveYInput = controller.getX(GenericHID.Hand.kLeft);
    double swerveXInput = -controller.getY(GenericHID.Hand.kLeft);
    double swerveRotationInput = (controller.getX(GenericHID.Hand.kRight));

    drive.readInput(swerveXInput, swerveYInput, swerveRotationInput);
  }
}
