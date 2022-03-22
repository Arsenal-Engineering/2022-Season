/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class TheftOfABall extends CommandBase {
  Timer timer;

  private JoystickButton button;

  private LimelightCam cam;
  private InstantCommand chillinWithDaIntake;
  private InstantCommand stopDaIntake;
  private SwerveDrive swerveDrive;

  public TheftOfABall(LimelightCam cam, SwerveDrive swerveDrive, InstantCommand chillinWithDaIntake, InstantCommand stopDaIntake, JoystickButton button) {
    addRequirements(cam);
    addRequirements(swerveDrive);
    timer = new Timer();
    this.cam = cam;
    this.chillinWithDaIntake = chillinWithDaIntake;
    this.swerveDrive = swerveDrive;
    this.button = button;
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    if (cam.getV() == 1.0) {
      chillinWithDaIntake.schedule();
      timer.reset();
    }

    swerveDrive.drive(0, -0.5, 0);
  }

  @Override
  public void end(boolean interrupted) {
    stopDaIntake.schedule();
    swerveDrive.drive(0, 0, 0);
  }

  @Override
  public boolean isFinished() {
    return !button.get() || timer.get() > 0.2;
  }
}
