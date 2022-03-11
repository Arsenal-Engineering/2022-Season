/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Camera extends SubsystemBase {
  UsbCamera cam;

  public Camera() {
    cam = CameraServer.startAutomaticCapture(0);
    cam.setResolution(160,120);
    cam.setFPS(10);
  }

  @Override
  public void periodic() {
  }
}
