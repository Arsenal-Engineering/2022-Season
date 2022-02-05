/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
 
package frc.robot.commands;
 
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
 
public class LimelightSteering extends CommandBase {
  /**
   * Creates a new LimelightSteering.
   */
    double Kp;
    double min_command;  
    double tx;
    double steering_adjust;
    LimelightCam cam;
    SwerveDrive swerveDrive;
    JoystickButton buttonB;
 
  public LimelightSteering(LimelightCam cam, SwerveDrive swerveDrive, JoystickButton buttonB) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(cam);
    this.cam = cam;
    Kp = -0.03;
    min_command = 0.05;
    tx = cam.getX();
    steering_adjust = 0.0;
    this.swerveDrive = swerveDrive;
    this.buttonB = buttonB;
  }
 
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }
 
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    tx = cam.getX();
 
    if (tx > 1.0)
    {
      steering_adjust = Kp*tx - min_command;
    }
    else if (tx < 1.0)
    {
      steering_adjust = Kp*tx + min_command;
    }

    swerveDrive.drive(0, 0, steering_adjust);
  }
   
 
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
 
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !buttonB.get();
  }
}
