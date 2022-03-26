/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.*;
import frc.robot.Robot;

public class ChillinWithDaIntake extends InstantCommand {
  private Conveyor conveyor;

  public ChillinWithDaIntake(Conveyor conveyor) {
    addRequirements(conveyor);
    this.conveyor = conveyor;
  }

  @Override
  public void initialize() {
    conveyor.startBotConveyor();
    Robot.getRobotContainer().getSwerveDrive().setConveyorOn(true);
  }
}
