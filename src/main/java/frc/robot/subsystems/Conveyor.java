// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Conveyor extends SubsystemBase {
  /** Creates a new Conveyer. */
  private final WPI_TalonSRX conveyorTop;
  private final WPI_TalonSRX conveyorBot;

  public Conveyor() {
    conveyorTop = new WPI_TalonSRX(Constants.CONVEYOR_TOP);
    conveyorBot = new WPI_TalonSRX(Constants.CONVEYOR_BOT); // I dont know how to code, Jacob has some sick shirts.
    conveyorTop.setInverted(true);
  }

  // Trigger button to turn on the shooter, then higher conveyer half power half
  // second later
  // Left trigger button to turn on lower conveyer

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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
