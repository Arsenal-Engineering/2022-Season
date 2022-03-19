/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import frc.robot.Robot;
import frc.robot.RobotContainer;
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
  }

  @Override
  public void execute() {
    if (switchLeft.get())
      lift.stopLeft();
    else
      lift.downLeft();

    if (switchRight.get())
      lift.stopRight();
    else
      lift.downRight();
  }

  @Override
  public void end(boolean interrupted) {
    lift.stopLeft();
    lift.stopRight();

    if (switchLeft.get() && switchRight.get())
      Robot.getRobotContainer().getSwerveDrive().setLiftExtended(false);
  }

  @Override
  public boolean isFinished() {
    return (switchLeft.get() && switchRight.get()) || Robot.getRobotContainer().getJoystick().getPOV() != 180;
  }
}
