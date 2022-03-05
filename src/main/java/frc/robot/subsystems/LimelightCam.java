/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
 
package frc.robot.subsystems;
 
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
 
public class LimelightCam extends SubsystemBase {
  NetworkTable table;
 
  public LimelightCam(String camName) {
    table = NetworkTableInstance.getDefault().getTable(camName);
  }
 
  @Override
  public void periodic() {
  }

  public double getV() {
    return table.getEntry("tv").getDouble(0.0);
  }
  
  public double getX() {
    return table.getEntry("tx").getDouble(0.0);
  }
 
  public double getY() {
    return table.getEntry("ty").getDouble(0.0);
  }
 
  public double getArea() {
    return table.getEntry("ta").getDouble(0.0);
  }

  public void setPipe(int pipe) {
    table.getEntry("pipeline").setNumber(pipe);
  }
}