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

  private final SubsystemBase[] subsystemList;
 
  private final SwerveDrive swerveDrive;
  private final Conveyor conveyor;
  private final Shooter shooter;

  public final NoMoPewPew noMoPewPew;
  public final DoDaPewPew doDaPewPew;
  public final ChillinWithDaIntake chillinWithDaIntake;
  public final DriveJoystick driveJoystick;

  private JoystickButton lBumper;
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer(XboxController controller) {
    // Configure the button bindings
    configureButtonBindings();
    joystick = controller;

    swerveDrive = new SwerveDrive(27.0, 21.0);
    conveyor = new Conveyor();
    shooter = new Shooter();

    subsystemList = new SubsystemBase[3];
    subsystemList[0] = swerveDrive;
    subsystemList[1] = conveyor;
    subsystemList[2] = shooter;

    driveJoystick = new DriveJoystick(joystick, swerveDrive);
    chillinWithDaIntake = new ChillinWithDaIntake(conveyor);
    doDaPewPew = new DoDaPewPew(conveyor, shooter);
    noMoPewPew = new NoMoPewPew(conveyor, shooter);

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
