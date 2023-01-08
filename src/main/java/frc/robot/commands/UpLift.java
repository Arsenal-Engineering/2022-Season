/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.DigitalInput;


public class UpLift extends CommandBase {
  private Lift lift;
  private DigitalInput switchLeft;
  private DigitalInput switchRight;

  public UpLift(Lift lift, int switchLT_ID, int switchRT_ID) {
    addRequirements(lift);
    this.lift = lift;
    switchRight = new DigitalInput(switchRT_ID);
    switchLeft = new DigitalInput(switchLT_ID);
  }

  @Override
  public void initialize() {
    Robot.getRobotContainer().getSwerveDrive().setLiftExtended(true);
  }

  @Override
  public void execute() {
    if (switchLeft.get())
      lift.stopLeft();
    else
      lift.upLeft();

    if (switchRight.get())
      lift.stopRight();
    else
      lift.upRight();
  }

  @Override
  public void end(boolean interrupted) {
    lift.stopLeft();
    lift.stopRight();
  }

  @Override
  public boolean isFinished() {
    return (switchLeft.get() && switchRight.get()) || Robot.getRobotContainer().getJoystick().getPOV() != 0;
  }
}
