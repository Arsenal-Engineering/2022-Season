// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;

public class DriveJoystick extends CommandBase {
  /** Creates a new DriveJoystick. */

  private SwerveDrive swerveDrive;
  private XboxController joystick;

  public DriveJoystick(SwerveDrive swerveDrive, XboxController joystick) {
    addRequirements(swerveDrive);
    this.swerveDrive = swerveDrive;
    this.joystick = joystick;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    swerveDrive.drive(joystick.getLeftX(), joystick.getLeftY(), joystick.getRightX());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
