/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

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

  public Rumble(XboxController joystick, double duration, double intensity) {
    this.joystick = joystick;
    this.intensity = intensity;
    this.duration = duration;
  }

  @Override
  public void initialize() {
    joystick.setRumble(RumbleType.kLeftRumble, intensity);
    joystick.setRumble(RumbleType.kRightRumble, intensity);

    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    System.out.print("**Rumble**");
  }

  @Override
  public void end(boolean interrupted) {
    joystick.setRumble(RumbleType.kLeftRumble, 0);
    joystick.setRumble(RumbleType.kRightRumble, 0);

    timer.stop();
  }

  @Override
  public boolean isFinished() {
    return timer.get() > duration;
  }
}
