// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class GoinBackWithDaIntake extends CommandBase {
  /** Creates a new ControlConveyor. */
  private Conveyor conveyor;
  private JoystickButton button;

  public GoinBackWithDaIntake(Conveyor conveyor, JoystickButton button) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(conveyor);
    this.conveyor = conveyor;
    this.button = button;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    conveyor.setConveyor(-0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !button.get();
  }
}
