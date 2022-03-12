/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class LimelightDistance extends CommandBase {
  double Kp;
  double min_command;
  double ty;
  double heading_error;
  double steering_adjust;
  LimelightCam cam;
  SwerveDrive swerveDrive;
  JoystickButton buttonY;

  public LimelightDistance(LimelightCam cam, SwerveDrive swerveDrive, JoystickButton buttonY) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(cam);
    addRequirements(swerveDrive);
    this.cam = cam;
    Kp = -0.1;
    min_command = 0.08;
    ty = cam.getY();
    heading_error = -ty;
    steering_adjust = 0.0;
    this.swerveDrive = swerveDrive;
    this.buttonY = buttonY;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    ty = cam.getY();
    heading_error = -ty;

    if (ty > 1.0) {
      steering_adjust = Kp * heading_error - min_command;
    } else if (ty < 1.0) {
      steering_adjust = Kp * heading_error + min_command;
    }

    swerveDrive.drive(0, steering_adjust, 0);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return !buttonY.get();
  }
}
