/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot {
  private static RobotContainer robotContainer;
  private Timer timer;

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
    robotContainer.getStopDaIntake().schedule();
    robotContainer.getNoMoPewPew().schedule();
    robotContainer.createDriveAuto(0.0, 0.0, 0.0).schedule();
    robotContainer.getDownLift().schedule(); //Should immediately end and set lift motors to 0 or lower them then set them to 0...
    timer.reset();
    timer.start();

    //Question? can we keep running th emototrs after time
  }

  @Override
  public void disabledPeriodic() {
    if (timer.get() > 1)
      CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void autonomousInit() {
    robotContainer.getSwerveDrive().setBrakeMode(true);
    timer.reset();
    timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    /*if (timer.get() < 0.5) { //Steer to hub for 0.5s
      robotContainer.getLimelightSteeringShooter().schedule();
    } else if (timer.get() < 1.0) { //Distance to hub for 0.5s
      robotContainer.getLimelightDistance().schedule();
    } else*/ if (timer.get() < 3) { //Shoot for 3.0s  ---> hehe need to fix if using limelight
      robotContainer.getDoDaPewPewHigh().schedule();
    } else if (timer.get() < 5) { //Stop shooting/conveyor for 0.125s
      robotContainer.getNoMoPewPew().schedule();
      robotContainer.getStopDaIntake().schedule();
    // } else if (timer.get() < 4.125) { //Turn around for 1.0s
    //   robotContainer.createDriveAuto(0.0, 0.0, 0.5).schedule();  
    } else if (timer.get() < 13/*5.625*/) { //Drive forward for 3.0s (1.5s)
      robotContainer.createDriveAuto(0.0, 0.3, 0.0).schedule();
    /*} else if (timer.get() < 6.0) { //Steer to ball for 0.375s
      robotContainer.getLimelightSteeringBall().schedule();
    } else if (timer.get() < 8.0) { //Pick up ball for 2s
      robotContainer.getTheftOfABall().schedule();*/
    // } else if (timer.get() < 8.125/*9.0*/) { //Turn around for 1.0s
    //   robotContainer.createDriveAuto(0.0, 0.0, 0.5).schedule();
    // } else if (timer.get() < 11.125/*11.0*/) { //Drive forward for 3.0s
    //   robotContainer.createDriveAuto(0.0, -0.5, 0.0).schedule(); 
    // /*} else if (timer.get() < 11.5) { //Steer to hub for 0.5s
    //   robotContainer.getLimelightSteeringShooter().schedule();
    // } else if (timer.get() < 12) { //Distance to hub for 0.5s
    //   robotContainer.getLimelightDistance().schedule();*/
    // } else if (timer.get() < 14.125/*15*/) { //Shoot for 3.0s
    //   robotContainer.getDoDaPewPewHigh().schedule();
    } else if (timer.get() <12) { //Stop shooting/conveyor until near end
      robotContainer.getNoMoPewPew().schedule();
      robotContainer.createDriveAuto(0.0, 0.0, 0.0).schedule();
      // robotContainer.getStopDaIntake().schedule();
    } else {
      timer.stop();
    }
  }

  @Override
  public void teleopInit() {
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

    // if (!(robotContainer.getJoystick().getAButton() || robotContainer.getJoystick().getBButton() || robotContainer.getJoystick().getXButton() || robotContainer.getJoystick().getYButton())) {
      robotContainer.getDriveJoystick().schedule();
    // }

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
