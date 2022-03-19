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
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class RobotContainer {
  private XboxController joystick;
  private JoystickButton buttonA, buttonB, buttonX, buttonY, lBumper, rightStickPush, back, start;
  private POVButton dPadUp, dPadDown;

  //// SUBSYSTEMS
  private final SwerveDrive swerveDrive;
  private final Conveyor conveyor;
  private final Shooter shooter;
  private final Camera camera;
  private final LimelightCam ballCam;
  private final LimelightCam shooterCam;
  private final Lift lift;

  //// COMMANDS
  private final DriveAuto driveBack;
  private final DriveAuto driveForward;
  private final DriveJoystick driveJoystick;
  private DoDaPewPew doDaPewPew;
  private InstantCommand noMoPewPew;
  private ChillinWithDaIntake chillinWithDaIntake;
  private InstantCommand stopDaIntake;
  private final FloopDaColor floopDaColor;
  private final Rumble rumble;

  public RobotContainer() {
    joystick = new XboxController(0);
      buttonA = new JoystickButton(joystick, 1);  
      buttonB = new JoystickButton(joystick, 2);
      buttonX = new JoystickButton(joystick, 3);
      buttonY = new JoystickButton(joystick, 4);
      lBumper = new JoystickButton(joystick, 5);
      back = new JoystickButton(joystick, 7);
      start = new JoystickButton(joystick, 8);
      rightStickPush = new JoystickButton(joystick, 10);
      dPadUp = new POVButton(joystick, 0);
      dPadDown = new POVButton(joystick, 180);

    //// SUBSYSTEMS
    swerveDrive = new SwerveDrive(27.0, 21.0);
    swerveDrive.setBrakeMode(false);
    conveyor = new Conveyor(Constants.CONVEYOR_TOP, Constants.CONVEYOR_BOT);
    shooter = new Shooter(Constants.SHOOTER);
    camera = new Camera();
    shooterCam = new LimelightCam("limelight-shooter");
    ballCam = new LimelightCam("limelight-ball");
    lift = new Lift(Constants.LIFT_LEFT, Constants.LIFT_RIGHT);

    //// COMMANDS
    driveBack = new DriveAuto(0, -1, 0, swerveDrive);
    driveForward = new DriveAuto(0, 1, 0, swerveDrive);
    driveJoystick = new DriveJoystick(joystick, swerveDrive);
    doDaPewPew = new DoDaPewPew(conveyor, shooter);
    noMoPewPew = new InstantCommand(shooter::stopShooter, shooter);
    chillinWithDaIntake = new ChillinWithDaIntake(conveyor);
    stopDaIntake = new InstantCommand(conveyor::stopConveyor, conveyor);
    rumble = new Rumble(joystick, 0.5, 1.0);

    floopDaColor = new FloopDaColor(ballCam, Constants.COLOR_SWITCH);

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // Limelight
    buttonA.whenPressed(new LimelightSteering(ballCam, swerveDrive, buttonA));
    buttonB.whenPressed(new TheftOfABall(ballCam, swerveDrive, chillinWithDaIntake, stopDaIntake, buttonB));
    buttonX.whenPressed(new LimelightSteering(shooterCam, swerveDrive, buttonX));
    buttonY.whenPressed(new LimelightDistance(shooterCam, swerveDrive, buttonY));
    rightStickPush.whenPressed(new LimelightTestV(ballCam));

    // Conveyor
    lBumper.whenPressed(new GoinBackWithDaIntake(conveyor, lBumper));

    // Lift
    dPadUp.whenPressed(new UpLift(lift, Constants.LIMIT_SWITCH_LEFT_TOP, Constants.LIMIT_SWITCH_RIGHT_TOP));
    dPadDown.whenPressed(new DownLift(lift, Constants.LIMIT_SWITCH_LEFT_BOT, Constants.LIMIT_SWITCH_RIGHT_BOT));
    dPadUp.whenReleased(new InstantCommand(lift::stopLift, lift));
    dPadDown.whenReleased(new InstantCommand(lift::stopLift, lift));

    // Drive Mode
    back.whenPressed(
        new ParallelCommandGroup(new Rumble(joystick, 1.0, 1.0), new InstantCommand(swerveDrive::setFieldOrientated)));
    start.whenPressed(new ParallelCommandGroup(new Rumble(joystick, 1.0, 1.0),
        new InstantCommand(swerveDrive::setFieldOrientatedRegular)));
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

  public Rumble getRumble() {
    return rumble;
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

  public FloopDaColor getFloopDaColor() {
    return floopDaColor;
  }
}
