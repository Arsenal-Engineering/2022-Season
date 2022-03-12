/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;

import com.kauailabs.navx.frc.AHRS;

public class DriveJoystick extends CommandBase {
  private SwerveDrive swerveDrive;
  private XboxController joystick;
  private AHRS navX;

  public DriveJoystick(XboxController joystick, SwerveDrive swerveDrive) {
    addRequirements(swerveDrive);
    this.swerveDrive = swerveDrive;
    this.joystick = joystick;
    navX = new AHRS(edu.wpi.first.wpilibj.SPI.Port.kMXP);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    double x1 = -joystick.getLeftX(); // X-axis on robot is flipped, new_y1 and new_x1 are also adjusted as needed
    double y1 = -joystick.getLeftY();

    if (swerveDrive.getFieldOrientated()) {
      double rad = navX.getYaw() * Math.PI / 180;
      double new_y1 = y1 * Math.cos(rad) + -1 * x1 * Math.sin(rad);
      double new_x1 = y1 * Math.sin(rad) + x1 * Math.cos(rad);
      swerveDrive.drive(new_x1, new_y1, -joystick.getRightX());
    } else {
      swerveDrive.drive(-x1, -y1, -joystick.getRightX());
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
