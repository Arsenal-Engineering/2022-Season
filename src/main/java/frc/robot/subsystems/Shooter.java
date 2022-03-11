/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Shooter extends SubsystemBase {
  private WPI_TalonFX shooter;

  public Shooter(int shooterID) {
    shooter = new WPI_TalonFX(shooterID);
  }

  // Sets the PWM value, should only input a value between -1 and 1, inclusive
  public void setShooter(double speed) {
    shooter.set(speed);
  }

  public void stopShooter() {
    shooter.set(0);
  }

  @Override
  public void periodic() {
  }
}