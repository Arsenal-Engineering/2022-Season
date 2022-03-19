/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.*;

public class DoDaPewPew extends CommandBase {
  Timer timer;

  private Conveyor conveyor;
  private Shooter shooter;

  public DoDaPewPew(Conveyor conveyor, Shooter shooter) {
    timer = new Timer();
    addRequirements(shooter);
    addRequirements(conveyor);
    this.conveyor = conveyor;
    this.shooter = shooter;
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    System.out.println("Hello cruel world!!");
    if (timer.get() > 2)
      conveyor.setConveyor(1);
    if (timer.get() < 1)
      shooter.setShooter(0.3);
    else if (timer.get() < 1.5)
      shooter.setShooter(0.5);
    else
      shooter.setShooter(0.7);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}