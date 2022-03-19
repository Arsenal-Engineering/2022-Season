/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.*;

public class TheftOfABall extends CommandBase {
  Timer timer;
  
  private double tx;
  private boolean hasSeenBall;
  private JoystickButton button;

  private LimelightCam cam;
  private ChillinWithDaIntake chillinWithDaIntake;
  private StopDaIntake stopDaIntake;
  private SwerveDrive swerveDrive;

  public TheftOfABall(LimelightCam cam, SwerveDrive swerveDrive, ChillinWithDaIntake chillinWithDaIntake, StopDaIntake stopDaIntake, JoystickButton button) {
    addRequirements(cam);
    addRequirements(swerveDrive);
    timer = new Timer();
    this.cam = cam;
    tx = cam.getX();
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

    swerveDrive.drive(0, -0.5, 0);
  }

  @Override
  public void end(boolean interrupted) {
    Robot.robotContainer.getDriveJoystick().schedule();
    // stopDaIntake.schedule(); hmmm maybe, maybe not- remember to review if changes are needed during drive practice
  }

  @Override
  public boolean isFinished() {
    return !button.get();
  }
}
