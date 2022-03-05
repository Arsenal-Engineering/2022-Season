/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class RobotContainer {
  private XboxController joystick;
  private JoystickButton buttonA, buttonB, buttonY, lBumper, back, start;

  ////SUBSYSTEMS
  private final SwerveDrive swerveDrive;
  //private final Conveyor conveyor;
  //private final Shooter shooter;
  private final LimelightCam ballCam;
  private final Camera camera;
  private final LimelightCam shooterCam;

  ////COMMANDS
  private final DriveAuto driveBack;
  private final ChillinWithDaIntake chillinWithDaIntake;
  private final InstantCommand stopDaIntake;

  public RobotContainer(XboxController controller, SwerveDrive swerveDrive/*, Conveyor conveyor, Shooter shooter*/, DriveAuto driveBack, ChillinWithDaIntake chillinWithDaIntake, InstantCommand stopDaIntake) {
    joystick = controller;
    buttonB = new JoystickButton(joystick, 2);
    buttonY = new JoystickButton(joystick, 4);
    buttonA = new JoystickButton(joystick, 1);
    lBumper = new JoystickButton(joystick, 5);
    back = new JoystickButton(joystick, 7);
    start = new JoystickButton(joystick, 8);

    this.swerveDrive = swerveDrive;
    //this.conveyor = conveyor;
    //this.shooter = shooter;
    camera = new Camera();
    ballCam = new LimelightCam();
    shooterCam = new LimelightCam();

    this.driveBack = driveBack;
    this.chillinWithDaIntake = chillinWithDaIntake;
    this.stopDaIntake = stopDaIntake;

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // Limelight
    buttonB.whenPressed(new LimelightSteering(shooterCam, swerveDrive, buttonB));
    buttonY.whenPressed(new LimelightDistance(shooterCam, swerveDrive, buttonY));
    buttonA.whenPressed(new TheftOfABall(ballCam, swerveDrive, buttonA, driveBack, chillinWithDaIntake, stopDaIntake));
    
    // Conveyor
    // lBumper.whenPressed(new GoinBackWithDaIntake(conveyor));
    // lBumper.whenReleased(new StopDaIntake(conveyor));
    
    // Drive Mode
    back.whenPressed(new SetDriveMode(true, swerveDrive));
    start.whenPressed(new SetDriveMode(false, swerveDrive));
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
