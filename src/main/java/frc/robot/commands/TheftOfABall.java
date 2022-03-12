/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.*;

public class TheftOfABall extends CommandBase {
  Timer timer;
  double Kp;
  double min_command;
  double tx, ta;
  double heading_error;
  double steering_adjust;
  LimelightSteering limelightSteering;
  LimelightCam cam;
  ChillinWithDaIntake chillinWithDaIntake;
  InstantCommand stopDaIntake;
  SwerveDrive swerveDrive;
  JoystickButton buttonA;

  public TheftOfABall(LimelightCam cam, SwerveDrive swerveDrive, JoystickButton buttonA,
      ChillinWithDaIntake chillinWithDaIntake, InstantCommand stopDaIntake) {
    addRequirements(cam);
    addRequirements(swerveDrive);
    timer = new Timer();
    this.cam = cam;
    Kp = -0.1;
    min_command = 0.08;
    tx = cam.getY();
    ta = cam.getArea();
    heading_error = -tx;
    steering_adjust = 0.0;
    this.chillinWithDaIntake = chillinWithDaIntake;
    this.swerveDrive = swerveDrive;
    this.buttonA = buttonA;
    limelightSteering = new LimelightSteering(cam, swerveDrive, buttonA);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    limelightSteering.schedule();
    if (ta > 50.0) {
      chillinWithDaIntake.schedule();
    } else {
      stopDaIntake.schedule();
    }
    if (timer.get() > 0.3333333333333333333) {
      swerveDrive.drive(0, -1, 0);
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
