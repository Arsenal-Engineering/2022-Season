/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.*;

public class DoDaPewPew extends CommandBase {
  Timer timer;

  private Conveyor conveyor;
  private Shooter shooter;

  /**
   * Creates a new DoDaPewPew.
   */

  public DoDaPewPew(Conveyor conveyor, Shooter shooter) {
    timer = new Timer();
    addRequirements(shooter);
    addRequirements(conveyor);
    this.conveyor = conveyor;
    this.shooter = shooter;
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
    if (timer.get() > 1) {
      shooter.setShooter(1);
      conveyor.setConveyor(0.7);
    } else
      shooter.setShooter(1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}