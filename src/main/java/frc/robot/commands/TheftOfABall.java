/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class TheftOfABall extends CommandBase {
  Timer timer;

  private JoystickButton button;

  private LimelightCam cam;
  private Conveyor conveyor;
  private SwerveDrive swerveDrive;

  public TheftOfABall(LimelightCam cam, SwerveDrive swerveDrive, Conveyor conveyor, JoystickButton button) {
    addRequirements(cam);
    addRequirements(swerveDrive);
    addRequirements(conveyor);
    timer = new Timer();
    this.cam = cam;
    this.conveyor = conveyor;
    this.button = button;
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    System.out.println("See ball: " + cam.getV());
    if (cam.getV() == 1.0) {
      conveyor.startBotConveyor();
      timer.reset();
    }

    swerveDrive.drive(0, -0.5, 0);
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("stopping theft ball");
    conveyor.stopConveyor();
    swerveDrive.drive(0, 0, 0);
  }

  @Override
  public boolean isFinished() {
    return !button.get() || timer.get() > 0.2;
  }
}
