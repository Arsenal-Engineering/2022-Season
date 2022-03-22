/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;


public class Lift extends SubsystemBase {

  private WPI_TalonSRX left;
  private WPI_TalonSRX right;

  public Lift(int left_ID, int right_ID) {
    left = new WPI_TalonSRX(left_ID);
    right = new WPI_TalonSRX(right_ID);
    left.setNeutralMode(NeutralMode.Brake);
    right.setNeutralMode(NeutralMode.Brake);
  }

  public void upLeft() {
    left.set(0.5);
  }

  public void upRight() {
    right.set(0.45);
  }

  public void downLeft() {
    left.set(-0.5);
  }

  public void downRight() {
    right.set(-0.45);
  }

  public void stopLeft() {
    left.set(0);
  }

  public void stopRight() {
    right.set(0);
  }

  @Override
  public void periodic() {
  }
}
