// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.DigitalInput;

public class MoveThyLify extends CommandBase {
  private double speed;
  private Lift lift;
  private DigitalInput limitSwitch;

  public MoveThyLify(double speed, Lift lift, int limitSwitchPort) {
    addRequirements(lift);
    this.lift = lift;
    this.speed = speed;
    this.limitSwitch = new DigitalInput(limitSwitchPort);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    lift.setLift(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    lift.setLift(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return limitSwitch.get(); //return true if all buttons read nothing
  }
}
