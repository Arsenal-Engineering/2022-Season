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
  private static RobotContainer robotContainer;
  private Timer timer;

  private Command m_autonomousCommand;

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    timer = new Timer();
    robotContainer.getFloopDaColor().schedule();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    robotContainer.getSwerveDrive().setBrakeMode(false);
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    // m_autonomousCommand = robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.schedule();
    // }

    robotContainer.getSwerveDrive().setBrakeMode(true);
    timer.reset();
    timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    if (timer.get() < 2) {
      robotContainer.getDoDaPewPewHigh().schedule();
    } else if (timer.get() < 0/* Insert Value Here */) {
      robotContainer.getNoMoPewPew().schedule();
      robotContainer.getStopDaIntake().schedule();
    } else if (timer.get() < 0/* Insert Value Here */) {
      robotContainer.getDriveBack().schedule();
      robotContainer.getChillinWithDaIntake().schedule();     
    } else if (timer.get() < 0/* Insert Value Here */) {
      robotContainer.getDriveForward().schedule();
      robotContainer.getStopDaIntake().schedule();
    } else if (timer.get() < 0/* Insert Value Here */) {
      robotContainer.getDoDaPewPewHigh().schedule();
    } else if (timer.get() < 0/* Insert Value Here */) {
      robotContainer.getNoMoPewPew().schedule();
      robotContainer.getStopDaIntake().schedule();
    } else {
      timer.stop();
    }
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    robotContainer.getSwerveDrive().setBrakeMode(true);
    robotContainer.getDriveJoystick().schedule();
    timer.reset();
    timer.start();
  }

  @Override
  public void teleopPeriodic() {
    if (timer.get() < 1.0) {
      robotContainer.getRumble().schedule();
    } else if (timer.get() < 2.0) {
      robotContainer.getRumble().schedule();
    } else {
      timer.stop();
    }

    if (!(robotContainer.getJoystick().getAButton() || robotContainer.getJoystick().getBButton() || robotContainer.getJoystick().getXButton() || robotContainer.getJoystick().getYButton())) {
      robotContainer.getDriveJoystick().schedule();
    }

    if (robotContainer.getJoystick().getRightTriggerAxis() > .5) {
      robotContainer.getDoDaPewPewHigh().schedule();
    } else if (robotContainer.getJoystick().getLeftTriggerAxis() > .5) {
      robotContainer.getChillinWithDaIntake().schedule();
    } else if (robotContainer.getJoystick().getRightBumper()) {
      robotContainer.getDoDaPewPewLow().schedule();
    } else {
      robotContainer.getNoMoPewPew().schedule();
      if (!robotContainer.getJoystick().getLeftBumperPressed())
        robotContainer.getStopDaIntake().schedule();
    }
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
    robotContainer.getSwerveDrive().setBrakeMode(true);
    robotContainer.getDriveJoystick().schedule();
  }

  @Override
  public void testPeriodic() {
  }

  public static RobotContainer getRobotContainer() {
    return robotContainer;
  }
}
