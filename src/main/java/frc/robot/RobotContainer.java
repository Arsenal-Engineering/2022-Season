// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
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
  private JoystickButton buttonA, buttonB, buttonY, lBumper, back, start;

  private final SwerveDrive swerveDrive;
  //private final Conveyor conveyor;
  //private final Shooter shooter;
  private final LimelightCam ballCam;
  private final Camera camera;
  private final LimelightCam shooterCam;

  private final DriveAuto driveBack;
  private final ChillinWithDaIntake chillinWithDaIntake;
  private final StopDaIntake stopDaIntake;


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer(XboxController controller, SwerveDrive swerveDrive/*, Conveyor conveyor, Shooter shooter*/, DriveAuto driveBack, ChillinWithDaIntake chillinWithDaIntake, StopDaIntake stopDaIntake) {
      System.out.println(3);
    // Configure the button bindings
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

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@linkedu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
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
