/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import static frc.robot.Constants.*;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private XboxController joystick;

  private Timer timer;

  ////SUBSYSTEMS
  private Conveyor conveyor;
  private Shooter shooter;
  private SwerveDrive swerveDrive;

  ////COMMANDS
  private InstantCommand noMoPewPew;
  private DoDaPewPew doDaPewPew;
  private ChillinWithDaIntake chillinWithDaIntake;
  private InstantCommand stopDaIntake;
  private DriveJoystick driveJoystick;
  private DriveAuto driveBack;
  private DriveAuto driveForward;

  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    joystick = new XboxController(0);

    timer = new Timer();

    // conveyor = new Conveyor(CONVEYOR_TOP, CONVEYOR_BOT);
    // shooter = new Shooter(SHOOTER);
    swerveDrive = new SwerveDrive(27.0, 21.0);
    swerveDrive.setBrakeMode(false);

    // doDaPewPew = new DoDaPewPew(conveyor, shooter);
    noMoPewPew = new InstantCommand(shooter::stopShooter, shooter);
    // chillinWithDaIntake = new ChillinWithDaIntake(conveyor);
    stopDaIntake = new InstantCommand(conveyor::stopConveyor, conveyor);

    driveJoystick = new DriveJoystick(joystick, swerveDrive);
    driveBack = new DriveAuto(0, -1, 0, swerveDrive);
    driveForward = new DriveAuto(0, 1, 0, swerveDrive);

    m_robotContainer = new RobotContainer(joystick, swerveDrive/*, conveyor, shooter*/, driveBack, chillinWithDaIntake, stopDaIntake);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    swerveDrive.setBrakeMode(false);
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    swerveDrive.setBrakeMode(true);
    timer.reset();
    timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    if (timer.get() < 2) {
      doDaPewPew.schedule();
    } else if (timer.get() < 0/* Insert Value Here */) {
      noMoPewPew.schedule();
      stopDaIntake.schedule();
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
      stopDaIntake.schedule();
    } else {
      timer.stop();
    }
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    swerveDrive.setBrakeMode(true);
    driveJoystick.schedule();
    //chillinWithDaIntake.schedule();
    timer.reset();
    timer.start();
  }

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

    if (timer.get() < .2) {
      joystick.setRumble(RumbleType.kLeftRumble, 0.9);
      joystick.setRumble(RumbleType.kRightRumble, 0.9);
    } else if (timer.get() < .3) {
      joystick.setRumble(RumbleType.kLeftRumble, 0.0);
      joystick.setRumble(RumbleType.kRightRumble, 0.0);
    } else if (timer.get() < .4) {
      joystick.setRumble(RumbleType.kLeftRumble, 0.9);
      joystick.setRumble(RumbleType.kRightRumble, 0.9);
    } else if (timer.get() < .6) {
      joystick.setRumble(RumbleType.kLeftRumble, 0.0);
      joystick.setRumble(RumbleType.kRightRumble, 0.0);
    } else {
      timer.stop();
    }

  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
    swerveDrive.setBrakeMode(true);
    driveJoystick.schedule();
  }

  @Override
  public void testPeriodic() {
  }
}