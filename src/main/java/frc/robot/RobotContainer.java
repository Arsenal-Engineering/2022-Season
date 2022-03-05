// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.DigitalInput;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private XboxController joystick;
  private JoystickButton buttonA, buttonB, buttonX, buttonY, lBumper, leftStickPush, rightStickPush;
  private DigitalInput colorSwitch;

  private final SwerveDrive swerveDrive;
  //private final Conveyor conveyor;
  //private final Shooter shooter;
  private final Camera camera;
  //private final LimelightCam shooterCam;
  private final LimelightCam ballCam;

  private final DriveAuto driveBack;
  private final ChillinWithDaIntake chillinWithDaIntake;
  private final StopDaIntake stopDaIntake;


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer(XboxController controller, SwerveDrive swerveDrive/*, Conveyor conveyor, Shooter shooter*/, DriveAuto driveBack, ChillinWithDaIntake chillinWithDaIntake, StopDaIntake stopDaIntake) {
    // Configure the button bindings
    joystick = controller;
    buttonA = new JoystickButton(joystick, 1);
    buttonB = new JoystickButton(joystick, 2);
    buttonX = new JoystickButton(joystick, 3);
    buttonY = new JoystickButton(joystick, 4);
    lBumper = new JoystickButton(joystick, 5);
    leftStickPush = new JoystickButton(joystick, 9);
    rightStickPush = new JoystickButton(joystick, 10);

    colorSwitch = new DigitalInput(Constants.COLOR_SWITCH);

    this.swerveDrive = swerveDrive;
    //this.conveyor = conveyor;
    //this.shooter = shooter;

    camera = new Camera();
    //shooterCam = new LimelightCam("limelight-shooter");
    ballCam = new LimelightCam("limelight-ball");


    this.driveBack = driveBack;
    this.chillinWithDaIntake = chillinWithDaIntake;
    this.stopDaIntake = stopDaIntake;

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@linkedu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Limelight
    buttonA.whenPressed(new LimelightSteering(ballCam, swerveDrive, buttonA));
    buttonB.whenPressed(new TheftOfABall(ballCam, swerveDrive, buttonB, driveBack, chillinWithDaIntake, stopDaIntake));
    //buttonX.whenPressed(new LimelightSteering(shooterCam, swerveDrive, buttonX));
    //buttonY.whenPressed(new LimelightDistance(shooterCam, swerveDrive, buttonY));
    leftStickPush.whenPressed(new FloopDaColor(ballCam, colorSwitch));
    rightStickPush.whenPressed(new LimelightTestV(ballCam));

    // Conveyor
    //lBumper.whenPressed(new GoinBackWithDaIntake(conveyor));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
