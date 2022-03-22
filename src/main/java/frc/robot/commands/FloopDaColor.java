/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightCam;

public class FloopDaColor extends InstantCommand {
  private LimelightCam ballCam;
  private DigitalInput colorSwitch;

  public FloopDaColor(LimelightCam ballCam, int SwitchColor_ID) {
    addRequirements(ballCam);
    this.ballCam = ballCam;
    colorSwitch = new DigitalInput(SwitchColor_ID);
  }

  @Override
  public void initialize() {
    ballCam.setPipe(colorSwitch.get() ? 0 : 1);
  }
}
