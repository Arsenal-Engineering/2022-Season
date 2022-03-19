/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Lift extends SubsystemBase {

  private VictorSPX left;
  private VictorSPX right;

  public Lift(int left_ID, int right_ID) {
    left = new VictorSPX(left_ID);
    right = new VictorSPX(right_ID);
  }

  public void upLift() {
    left.set(VictorSPXControlMode.PercentOutput, 0.5);
    right.set(VictorSPXControlMode.PercentOutput, 0.5);
  }

  public void downLift() {
    left.set(VictorSPXControlMode.PercentOutput, -0.5);
    right.set(VictorSPXControlMode.PercentOutput, -0.5);
  }

  public void stopLift() {
    left.set(VictorSPXControlMode.PercentOutput, 0);
    right.set(VictorSPXControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
  }
}
