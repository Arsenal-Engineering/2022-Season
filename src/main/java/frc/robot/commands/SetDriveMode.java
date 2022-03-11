// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SetDriveMode extends InstantCommand {
  private SwerveDrive swerveDrive;
  private boolean fieldOrientated;
  private XboxController joystick;

  private Rumble rumble;

  public SetDriveMode(boolean fieldOrientated, SwerveDrive swerveDrive, XboxController joystick) {
    // do not put addRequirements for swerveDrive!!!
    this.swerveDrive = swerveDrive;
    this.fieldOrientated = fieldOrientated;
    this.joystick = joystick;
    rumble = new Rumble(joystick, 1.0, 1.0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    swerveDrive.setFieldOrientated(fieldOrientated);
    rumble.schedule();
  }
}
