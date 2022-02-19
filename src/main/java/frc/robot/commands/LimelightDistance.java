/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
 
package frc.robot.commands;
 
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
 
public class LimelightDistance extends CommandBase {
  /**
   * Creates a new LimelightDistance.
   */
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
    this.cam = cam;
    Kp = -0.1;
    min_command = 0.08;
    ty = cam.getY();
    heading_error = -ty;
    steering_adjust = 0.0;
    this.swerveDrive = swerveDrive;
    this.buttonY = buttonY;
  }
 
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }
 
  // Called every time the scheduler runs while the command is scheduled.
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
 
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
 
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !buttonY.get();
  }
}
