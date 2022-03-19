/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.DigitalInput;

public class DownLift extends CommandBase {
  private Lift lift;
  private DigitalInput switchLeft;
  private DigitalInput switchRight;

  public DownLift(Lift lift, int switchLB_ID, int switchRB_ID) {
    addRequirements(lift);
    this.lift = lift;
    switchLeft = new DigitalInput(switchLB_ID);
    switchRight = new DigitalInput(switchRB_ID);
  }

  @Override
  public void initialize() {
    lift.downLift();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    lift.stopLift();
  }

  @Override
  public boolean isFinished() {
    return switchLeft.get() || switchRight.get();
  }
}
