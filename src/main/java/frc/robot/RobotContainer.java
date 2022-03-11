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
  private final Conveyor conveyor;
  private final Shooter shooter;
  private final LimelightCam ballCam;
  private final Camera camera;
  private final LimelightCam shooterCam;

  ////COMMANDS
  private final DriveAuto driveBack;
  private final DriveAuto driveForward;
  private final DriveJoystick driveJoystick;
  private InstantCommand noMoPewPew;
  private DoDaPewPew doDaPewPew;
  private ChillinWithDaIntake chillinWithDaIntake;
  private InstantCommand stopDaIntake;

  public RobotContainer() {
    joystick = new XboxController(0);
    buttonB = new JoystickButton(joystick, 2);
    buttonY = new JoystickButton(joystick, 4);
    buttonA = new JoystickButton(joystick, 1);
    lBumper = new JoystickButton(joystick, 5);
    back = new JoystickButton(joystick, 7);
    start = new JoystickButton(joystick, 8);

    swerveDrive = new SwerveDrive(27.0, 21.0);
    swerveDrive.setBrakeMode(false);
    conveyor = new Conveyor(Constants.CONVEYOR_TOP, Constants.CONVEYOR_BOT);
    shooter = new Shooter(Constants.SHOOTER);
    camera = new Camera();
    ballCam = new LimelightCam();
    shooterCam = new LimelightCam();

    driveBack = new DriveAuto(0, -1, 0, swerveDrive);
    driveForward = new DriveAuto(0, 1, 0, swerveDrive);
    driveJoystick = new DriveJoystick(joystick, swerveDrive);

    doDaPewPew = new DoDaPewPew(conveyor, shooter);
    noMoPewPew = new InstantCommand(shooter::stopShooter, shooter);
    chillinWithDaIntake = new ChillinWithDaIntake(conveyor);
    stopDaIntake = new InstantCommand(conveyor::stopConveyor, conveyor);

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // Limelight
    buttonB.whenPressed(new LimelightSteering(shooterCam, swerveDrive, buttonB));
    buttonY.whenPressed(new LimelightDistance(shooterCam, swerveDrive, buttonY));
    buttonA.whenPressed(new TheftOfABall(ballCam, swerveDrive, buttonA, driveBack, chillinWithDaIntake, stopDaIntake));
    
    // Conveyor
    lBumper.whenPressed(new GoinBackWithDaIntake(conveyor));
    lBumper.whenReleased(stopDaIntake);
    
    // Drive Mode
    back.whenPressed(new SetDriveMode(true, swerveDrive));
    start.whenPressed(new SetDriveMode(false, swerveDrive));
  }

  public SwerveDrive getSwerveDrive() {
    return swerveDrive;
  }

  public DriveAuto getDriveBack() {
    return driveBack;
  }

  public DriveAuto getDriveForward() {
    return driveForward;
  }

  public DriveJoystick getDriveJoystick() {
    return driveJoystick;
  }

  public InstantCommand getStopDaIntake() {
    return stopDaIntake;
  }

  public DoDaPewPew getDoDaPewPew() {
    return doDaPewPew;
  }

  public InstantCommand getNoMoPewPew() {
    return noMoPewPew;
  }

  public ChillinWithDaIntake getChillinWithDaIntake() {
    return chillinWithDaIntake;
  }

  public XboxController getJoystick() {
    return joystick;
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}