/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot {
  private RobotContainer m_robotContainer;
  private Timer timer;

  private Command m_autonomousCommand;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    timer = new Timer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    m_robotContainer.getSwerveDrive().setBrakeMode(false);
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

    m_robotContainer.getSwerveDrive().setBrakeMode(true);
    timer.reset();
    timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    if (timer.get() < 2) {
      m_robotContainer.getDoDaPewPew().schedule();
    } else if (timer.get() < 0/* Insert Value Here */) {
      m_robotContainer.getNoMoPewPew().schedule();
      m_robotContainer.getStopDaIntake().schedule();
    } else if (timer.get() < 0/* Insert Value Here */) {
      m_robotContainer.getDriveBack().schedule();
      m_robotContainer.getChillinWithDaIntake().schedule();     
    } else if (timer.get() < 0/* Insert Value Here */) {
      m_robotContainer.getDriveForward().schedule();
      m_robotContainer.getStopDaIntake().schedule();
    } else if (timer.get() < 0/* Insert Value Here */) {
      m_robotContainer.getDoDaPewPew().schedule();
    } else if (timer.get() < 0/* Insert Value Here */) {
      m_robotContainer.getNoMoPewPew().schedule();
      m_robotContainer.getStopDaIntake().schedule();
    } else {
      timer.stop();
    }
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    m_robotContainer.getSwerveDrive().setBrakeMode(true);
    m_robotContainer.getDriveJoystick().schedule();
    //chillinWithDaIntake.schedule();
    timer.reset();
    timer.start();
  }

  @Override
  public void teleopPeriodic() {
    m_robotContainer.getDriveJoystick().schedule();
    if (m_robotContainer.getJoystick().getRightTriggerAxis() > .5) {
      // doDaPewPew.schedule();
    } else if (m_robotContainer.getJoystick().getLeftTriggerAxis() > .5) {
      // chillinWithDaIntake.schedule();
    } else {
      // noMoPewPew.schedule();
      // stopDaIntake.schedule();
    }

    if (timer.get() < 1.0) {
      m_robotContainer.getRumble().schedule();
    } else if (timer.get() < 2.0) {
      m_robotContainer.getRumble().schedule();
    } else {
      timer.stop();
    }

  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
    m_robotContainer.getSwerveDrive().setBrakeMode(true);
    m_robotContainer.getDriveJoystick().schedule();
  }

  @Override
  public void testPeriodic() {
  }
}