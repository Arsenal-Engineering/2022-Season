// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightCam;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FloopDaColor extends InstantCommand {
  private LimelightCam ballCam;
  private boolean blue;

  public FloopDaColor(LimelightCam ballCam) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(ballCam);
    this.ballCam = ballCam;
    blue = true;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    int pipe;
    if (blue) {
      pipe = 0;
      System.out.println("Limelight on blue team");
    } else {
      pipe = 1;
      System.out.println("Limelight on red team");
    }
    ballCam.setPipe(pipe);
    blue = !blue;
  }
}
