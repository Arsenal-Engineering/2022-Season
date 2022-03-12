// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Timer;

public class TheftOfABall extends CommandBase {
  /** Creates a new TheftOfABall. */
  Timer timer;
  
  private double tx;
  private boolean hasSeenBall;

  private LimelightCam cam;
  private ChillinWithDaIntake chillinWithDaIntake;
  private StopDaIntake stopDaIntake;
  private SwerveDrive swerveDrive;

  public TheftOfABall(LimelightCam cam, SwerveDrive swerveDrive, ChillinWithDaIntake chillinWithDaIntake, StopDaIntake stopDaIntake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(cam);
    addRequirements(swerveDrive);
    timer = new Timer();
    this.cam = cam;
    tx = cam.getX();
    this.chillinWithDaIntake = chillinWithDaIntake;
    this.swerveDrive = swerveDrive;
    hasSeenBall = false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    hasSeenBall = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (tx < 50.0) {
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



    swerveDrive.drive(0, -1, 0);
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
