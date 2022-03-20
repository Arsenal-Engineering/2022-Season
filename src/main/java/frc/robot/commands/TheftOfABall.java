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
import frc.robot.*;

public class TheftOfABall extends CommandBase {
  Timer timer;
  
  private double ty;
  private boolean hasSeenBall;
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
    ty = cam.getY();
    this.chillinWithDaIntake = chillinWithDaIntake;
    this.swerveDrive = swerveDrive;
    hasSeenBall = false;
    this.button = button;
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    hasSeenBall = false;
  }

  @Override
  public void execute() {
    if (ty < 50.0) {
      chillinWithDaIntake.schedule();
      hasSeenBall = true;
      timer.reset();
    }
    else if (timer.get() > 0.125){
      stopDaIntake.schedule();
    }
    else if (hasSeenBall) {
      chillinWithDaIntake.schedule();
    }

    swerveDrive.drive(0, -0.5, 0);
  }

  @Override
  public void end(boolean interrupted) {
    // Robot.getRobotContainer().getDriveJoystick().schedule();
    // stopDaIntake.schedule(); hmmm maybe, maybe not- remember to review if changes are needed during drive practice
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
