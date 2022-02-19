// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;

import com.kauailabs.navx.frc.AHRS;

public class DriveJoystick extends CommandBase {
  /** Creates a new DriveJoystick. */

  private SwerveDrive swerveDrive;
  private XboxController joystick;
  private AHRS navX;

  public DriveJoystick(XboxController joystick, SwerveDrive swerveDrive) {

    addRequirements(swerveDrive);
    this.swerveDrive = swerveDrive;
    this.joystick = joystick;
    navX = new AHRS(edu.wpi.first.wpilibj.SPI.Port.kMXP);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*
    //assumes y1 is backwards
    double x1 = joystick.getLeftX();
    double y1 = joystick.getLeftY();
    double rad = navX.getYaw() * Math.PI / 180;
    double new_y1 = y1 * -1 * Math.cos(rad) + x1 * Math.sin(rad);
    double new_x1 = y1 * Math.sin(rad) + x1 * Math.cos(rad);
    System.out.println(navX.getYaw());
    */
    //adjusts for field oriented control
    swerveDrive.drive(joystick.getLeftX(), -joystick.getLeftY(), -joystick.getRightX());
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
