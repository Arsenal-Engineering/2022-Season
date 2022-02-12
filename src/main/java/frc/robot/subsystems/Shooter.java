/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

//Talon Motor Imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Shooter extends SubsystemBase {
  private WPI_TalonFX shooter;

  public Shooter() {
    
    shooter = new WPI_TalonFX(Constants.SHOOTER);
  }

  // Sets the PWM value, should only input a value between -1 and 1, inclusive
  public void setShooter(double speed) {
    shooter.set(speed);
  }

  @Override
  public void periodic() {}
}