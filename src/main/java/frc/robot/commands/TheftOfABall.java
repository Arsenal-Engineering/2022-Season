// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Timer;

public class TheftOfABall extends CommandBase {
  /** Creates a new TheftOfABall. */
  Timer timer;
  double Kp;
  double min_command;
  double tx, ta;
  double heading_error;
  double steering_adjust;
  LimelightSteering limelightSteering;
  DriveAuto driveBack;
  LimelightCam cam;
  ChillinWithDaIntake chillinWithDaIntake;
  StopDaIntake stopDaIntake;
  SwerveDrive swerveDrive;
  JoystickButton buttonA;

  public TheftOfABall(LimelightCam cam, SwerveDrive swerveDrive, JoystickButton buttonA, DriveAuto driveBack,
      ChillinWithDaIntake chillinWithDaIntake, StopDaIntake stopDaIntake) {
    // Use addRequirements() here to declare subsystem dependencies.
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
    this.driveBack = driveBack;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    limelightSteering.schedule();
    if (ta > 50.0) {
      chillinWithDaIntake.schedule();
    } else {
      stopDaIntake.schedule();
    }
    if (timer.get() > 0.3333333333333333333) {
      driveBack.schedule();
    }
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
