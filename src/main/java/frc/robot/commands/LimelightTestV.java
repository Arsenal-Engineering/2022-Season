/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightCam;

public class LimelightTestV extends InstantCommand {
  private LimelightCam cam;
  
  public LimelightTestV(LimelightCam cam) {
    addRequirements(cam);
    this.cam = cam;
  }

  @Override
  public void initialize() {
    System.out.println(cam.getName() + " valid target: " + cam.getV());
  }
}
