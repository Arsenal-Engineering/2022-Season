// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import static frc.robot.Constants.*;

/**
 * The VM is configured to automatically run this c.lass, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private XboxController joystick;

  private Timer timer;

  private Conveyor conveyor;
  private Shooter shooter;
  private SwerveDrive swerveDrive;

  private NoMoPewPew noMoPewPew;
  private DoDaPewPew doDaPewPew;
  private ChillinWithDaIntake chillinWithDaIntake;
  private StopDaIntake stopDaIntake;
  private DriveJoystick driveJoystick;
  private DriveAuto driveBack;
  private DriveAuto driveForward;

  private RobotContainer m_robotContainer;

  private Rumble rumble;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    System.out.println(1);
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    joystick = new XboxController(0);

    timer = new Timer();

    // conveyor = new Conveyor(CONVEYOR_TOP, CONVEYOR_BOT);
    // shooter = new Shooter(SHOOTER);
    swerveDrive = new SwerveDrive(27.0, 21.0);

    // noMoPewPew = new NoMoPewPew(conveyor, shooter);
    // doDaPewPew = new DoDaPewPew(conveyor, shooter);
    // chillinWithDaIntake = new ChillinWithDaIntake(conveyor);
    // stopDaIntake = new StopDaIntake(conveyor);
    driveJoystick = new DriveJoystick(joystick, swerveDrive);
    driveBack = new DriveAuto(0, -1, 0, swerveDrive);
    driveForward = new DriveAuto(0, 1, 0, swerveDrive);

    m_robotContainer = new RobotContainer(joystick, swerveDrive/*, conveyor, shooter*/, driveBack, chillinWithDaIntake, stopDaIntake);

    rumble = new Rumble(joystick, 0.5, 1.0);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    timer.reset();
    timer.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    if (timer.get() < 2) {
      doDaPewPew.schedule();
    } else if (timer.get() < 0/* Insert Value Here */) {
      noMoPewPew.schedule();
    } else if (timer.get() < 0/* Insert Value Here */) {
      driveBack.schedule();
      chillinWithDaIntake.schedule();     
    } else if (timer.get() < 0/* Insert Value Here */) {
      driveForward.schedule();
      stopDaIntake.schedule();
    } else if (timer.get() < 0/* Insert Value Here */) {
      doDaPewPew.schedule();
    } else if (timer.get() < 0/* Insert Value Here */) {
      noMoPewPew.schedule();
    } else {
      timer.stop();
    }
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    driveJoystick.schedule();
    //chillinWithDaIntake.schedule();
    timer.reset();
    timer.start();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    driveJoystick.schedule();
    if (joystick.getRightTriggerAxis() > .5) {
      // doDaPewPew.schedule();
    } else if (joystick.getLeftTriggerAxis() > .5) {
      // chillinWithDaIntake.schedule();
    } else {
      // noMoPewPew.schedule();
      // stopDaIntake.schedule();
    }

    if (timer.get() < 1.0) {
      rumble.schedule();
    } else if (timer.get() < 2.0) {
      rumble.schedule();
    } else {
      timer.stop();
    }

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
    driveJoystick.schedule();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}