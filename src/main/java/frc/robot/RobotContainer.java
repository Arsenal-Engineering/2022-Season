// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
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
  private JoystickButton buttonA, buttonB, buttonX, buttonY;

  //Subsystems
  private final SubsystemBase[] subsystemList;
  private final SwerveDrive swerveDrive;
  private final WheelDrive fL;
  private final WheelDrive fR;
  private final WheelDrive bL;
  private final WheelDrive bR;
  private final Lift lift;

  //Commands
  public final DriveJoystick driveJoystick;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer(XboxController controller) {
    // Configure the button bindings
    joystick = controller;

    fL = new WheelDrive("FL", Constants.SPEEDMOTOR_FL, Constants.ANGLEMOTOR_FL, 15, 0.00, 2000, 1023, true);
    fR = new WheelDrive("FR", Constants.SPEEDMOTOR_FR, Constants.ANGLEMOTOR_FR, 15, 0.00, 20, 1023, true);
    bL = new WheelDrive("BL", Constants.SPEEDMOTOR_BL, Constants.ANGLEMOTOR_BL, 15, 0.00, 2000, 1023, true);
    bR = new WheelDrive("BR", Constants.SPEEDMOTOR_BR, Constants.ANGLEMOTOR_BR, 15, 0.00, 20, 1023, true);
    swerveDrive = new SwerveDrive(bR, bL, fR, fL, 27.0, 21.0);

    lift = new Lift(Constants.LIFT);

    subsystemList = new SubsystemBase[7];
      subsystemList[0] = swerveDrive;
      subsystemList[1] = fL;
      subsystemList[2] = fR;
      subsystemList[3] = bL;
      subsystemList[4] = bR;

    driveJoystick = new DriveJoystick(joystick, swerveDrive);
      buttonA = new JoystickButton(joystick, 1);
      buttonB = new JoystickButton(joystick, 2);
      buttonX = new JoystickButton(joystick, 3);
      buttonY = new JoystickButton(joystick, 4);

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    buttonA.whenPressed(new MoveThyLify(-0.5, lift, Constants.LIMIT_SWITCH));
    buttonB.whenPressed(new MoveThyLify(0.5, lift, Constants.LIMIT_SWITCH));
    buttonX.whenPressed(new MoveThyLify(-1, lift, Constants.LIMIT_SWITCH));
    buttonY.whenPressed(new MoveThyLify(1, lift, Constants.LIMIT_SWITCH));

    buttonA.whenReleased(new StopLift(lift, Constants.LIMIT_SWITCH));
    buttonB.whenReleased(new StopLift(lift, Constants.LIMIT_SWITCH));
    buttonX.whenReleased(new StopLift(lift, Constants.LIMIT_SWITCH));
    buttonY.whenReleased(new StopLift(lift, Constants.LIMIT_SWITCH));
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
