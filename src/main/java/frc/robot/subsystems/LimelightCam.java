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
  
 
  public LimelightCam() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
  }
 
  @Override
  public void periodic() {
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

  public void setPipe(boolean blue) {
    if (blue) {
      table.getEntry("pipeline").setNumber(0);
    }
    else {
      table.getEntry("pipeline").setNumber(1);
    }
  }
}