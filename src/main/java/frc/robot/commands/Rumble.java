// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

public class Rumble extends CommandBase {
  private Timer timer = new Timer();

  private final XboxController joystick;
  private final double intensity;
  private final double duration;


  /** Creates a new Rumble. */
  public Rumble(XboxController joystick, double duration, double intensity) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.joystick = joystick;
    this.intensity = intensity;
    this.duration = duration;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    joystick.setRumble(RumbleType.kLeftRumble, intensity);
    joystick.setRumble(RumbleType.kRightRumble, intensity);

    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.printf("**Rumble**");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    joystick.setRumble(RumbleType.kLeftRumble, 0);
    joystick.setRumble(RumbleType.kRightRumble, 0);

    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > duration;
  }
}
