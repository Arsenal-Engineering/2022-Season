/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyor extends SubsystemBase {
  private final WPI_TalonSRX conveyorTop;
  private final WPI_TalonSRX conveyorBot;

  public Conveyor(int topID, int botID) {
    conveyorTop = new WPI_TalonSRX(topID);
    conveyorBot = new WPI_TalonSRX(botID);
    conveyorTop.setInverted(true);
  }

  public void setConveyor(double speed) {
    conveyorTop.set(speed);
    conveyorBot.set(speed);
  }

  public void setConveyorBot(double speed) {
    conveyorBot.set(speed);
  }

  public void setConveyorTop(double speed) {
    conveyorTop.set(speed);
  }

  public void stopConveyor() {
    conveyorTop.set(0);
    conveyorBot.set(0);
  }

  @Override
  public void periodic() {
  }

}