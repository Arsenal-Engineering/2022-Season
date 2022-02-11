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
import edu.wpi.first.wpilibj.XboxController;

public class DoDaPewPew extends CommandBase {
  Timer timer;

  private Conveyor conveyor;
  private Shooter shooter;
  private XboxController controller;

  /**
   * Creates a new ControlDatShooter.
   */

  public DoDaPewPew(Conveyor conveyor,Shooter shooter, XboxController controller) {
    timer = new Timer();
    addRequirements(shooter);
    addRequirements(conveyor);
    this.conveyor = conveyor;
    this.shooter = shooter;
    this.controller = controller;

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
    if (controller.getRightTriggerAxis() > .5) {
      if (timer.get() > 1) {
        shooter.startShooter();
        conveyor.setConveyor(0.7);
      } else
        shooter.startShooter();
    } else {
      shooter.stopShooter();
      conveyor.setConveyor(0);
      timer.reset();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.stopShooter();
    conveyor.setConveyor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}