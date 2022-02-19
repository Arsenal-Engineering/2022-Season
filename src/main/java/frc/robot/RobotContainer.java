// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
 
package frc.robot;
 
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
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
 
  private final SwerveDrive swerveDrive;
 
  public final LimelightCam cam;
 
  public final DriveJoystick driveJoystick;
 
  private JoystickButton buttonB, buttonY, lBumper;

  private final Conveyor conveyor;
  private final Shooter shooter;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer(XboxController controller, SwerveDrive swerveDrive, Conveyor conveyor, Shooter shooter) {
    // Configure the button bindings
    configureButtonBindings();
    joystick = controller;
 
    driveJoystick = new DriveJoystick(swerveDrive, joystick);
 
    buttonB = new JoystickButton(joystick, 1);
    buttonY = new JoystickButton(joystick, 3);

    this.swerveDrive = swerveDrive;
    this.conveyor = conveyor;
    this.shooter = shooter;
    
    cam = new LimelightCam();

    lBumper = new JoystickButton(joystick, 4);
  }
 
  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@linkedu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Limelight
    buttonB.whenPressed(new LimelightSteering(cam, swerveDrive, buttonB));
    buttonY.whenPressed(new LimelightDistance(cam, swerveDrive, buttonY));
    //Conveyor
    lBumper.whenPressed(new GoinBackWithDaIntake(conveyor));
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
