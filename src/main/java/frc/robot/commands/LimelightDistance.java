/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
 
package frc.robot.commands;
 
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
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
  JoystickButton button;
 
  public LimelightDistance(LimelightCam cam, SwerveDrive swerveDrive, JoystickButton button) {
    addRequirements(cam);
    addRequirements(swerveDrive);
    this.cam = cam;
    Kp = -0.1;
    min_command = 0.08;
    ty = cam.getY();
    heading_error = -ty;
    steering_adjust = 0.0;
    this.swerveDrive = swerveDrive;
    this.button = button;
  }
 
  @Override
  public void initialize() {
  }
 
  @Override
  public void execute() {
    ty = cam.getY();
    heading_error = -ty;
 
    if (ty > 1.0)
    {
      steering_adjust = Kp*heading_error - min_command;
    }
    else if (ty < 1.0)
    {
      steering_adjust = Kp*heading_error + min_command;
    }

    swerveDrive.drive(0, steering_adjust, 0);
  }
 
  @Override
  public void end(boolean interrupted) {
    Robot.robotContainer.getDriveJoystick().schedule();
  }
 
  @Override
  public boolean isFinished() {
    return !button.get();
  }
}
