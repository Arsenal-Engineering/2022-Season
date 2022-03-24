/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class RobotContainer {
  private XboxController joystick;
  private JoystickButton buttonA, buttonB, buttonX, buttonY, lBumper, rightStickPush, leftStickPush, back, start;
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
  private final InstantCommand driveForward;
  private final InstantCommand driveBackward;
  private final DriveJoystick driveJoystick;
  private final DoDaPewPew doDaPewPewHigh;
  private final DoDaPewPew doDaPewPewLow;
  private final InstantCommand noMoPewPew;
  private final InstantCommand chillinWithDaIntake;
  private final InstantCommand stopDaIntake;
  private final FloopDaColor floopDaColor;
  private final Rumble rumble;
  private final UpLift upLift;
  private final DownLift downLift;
  private final LimelightSteering limelightSteeringBall;
  private final LimelightSteering limelightSteeringShooter;
  private final TheftOfABall theftOfABall;
  private final LimelightDistance limelightDistance;

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
      leftStickPush = new JoystickButton(joystick, 9);
      dPadUp = new POVButton(joystick, 0);
      dPadDown = new POVButton(joystick, 180);

    //// SUBSYSTEMS
    swerveDrive = new SwerveDrive(27.0, 21.0, Constants.SPEEDMOTOR_BR, Constants.ANGLEMOTOR_BR, Constants.SPEEDMOTOR_BL, Constants.ANGLEMOTOR_BL, Constants.SPEEDMOTOR_FR, Constants.ANGLEMOTOR_FR, Constants.SPEEDMOTOR_FL, Constants.ANGLEMOTOR_FL);
    swerveDrive.setBrakeMode(false);
    conveyor = new Conveyor(Constants.CONVEYOR_TOP, Constants.CONVEYOR_BOT);
    shooter = new Shooter(Constants.SHOOTER);
    camera = new Camera();
    shooterCam = new LimelightCam("limelight-shooter");
    ballCam = new LimelightCam("limelight-ball");
    lift = new Lift(Constants.LIFT_LEFT, Constants.LIFT_RIGHT);

    //// COMMANDS
    driveForward = new InstantCommand(swerveDrive::driveForward, swerveDrive);
    driveBackward = new InstantCommand(swerveDrive::driveBackward, swerveDrive);
    driveJoystick = new DriveJoystick(joystick, swerveDrive);
    doDaPewPewHigh = new DoDaPewPew(conveyor, shooter, 1.0);
    doDaPewPewLow = new DoDaPewPew(conveyor, shooter, 0.3);
    noMoPewPew = new InstantCommand(shooter::stopShooter, shooter);
    chillinWithDaIntake = new InstantCommand(conveyor::startBotConveyor, conveyor);
    stopDaIntake = new InstantCommand(conveyor::stopConveyor, conveyor);
    rumble = new Rumble(joystick, 0.5, 1.0);
    upLift = new UpLift(lift, Constants.LIMIT_SWITCH_LEFT_TOP, Constants.LIMIT_SWITCH_RIGHT_TOP);
    downLift = new DownLift(lift, Constants.LIMIT_SWITCH_LEFT_BOT, Constants.LIMIT_SWITCH_RIGHT_BOT);
    floopDaColor = new FloopDaColor(ballCam, Constants.COLOR_SWITCH);
    limelightSteeringBall = new LimelightSteering(ballCam, swerveDrive, buttonA, false);
    limelightSteeringShooter = new LimelightSteering(shooterCam, swerveDrive, buttonX, true);
    theftOfABall = new TheftOfABall(ballCam, swerveDrive, conveyor, buttonB);
    limelightDistance = new LimelightDistance(shooterCam, swerveDrive, buttonY, true);

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // Limelight
    buttonA.whenPressed(limelightSteeringBall);
    buttonB.whenPressed(theftOfABall);
    buttonX.whenPressed(limelightSteeringShooter);
    buttonY.whenPressed(limelightDistance);

    // Conveyor
    lBumper.whenPressed(new InstantCommand(conveyor::reverseConveyor, conveyor));
    rightStickPush.whenPressed(new LimelightTestV(ballCam));
    leftStickPush.whenPressed(new LimelightTestV(shooterCam));

    // Lift
    dPadUp.whenPressed(upLift);
    dPadDown.whenPressed(downLift);
    //dPadUp.whenReleased(new InstantCommand(lift::stopLift, lift)); //Haven't been consistent with their performance
    // m_robotContainer.whyIsMyPeeRed().realMenGetHemorrhages;
    //dPadDown.whenReleased(new InstantCommand(lift::stopLift, lift));

    // Drive Mode
    back.whenPressed(
        new ParallelCommandGroup(new Rumble(joystick, 1.0, 1.0), new InstantCommand(swerveDrive::setFieldOrientated)));
    start.whenPressed(new ParallelCommandGroup(new Rumble(joystick, 1.0, 1.0),
        new InstantCommand(swerveDrive::setFieldOrientatedRegular)));
  }

  public SwerveDrive getSwerveDrive() {
    return swerveDrive;
  }

  public InstantCommand getDriveForward() {
    return driveForward;
  }

  public InstantCommand getDriveBack() {
    return driveBackward;
  }

  public DriveJoystick getDriveJoystick() {
    return driveJoystick;
  }

  public InstantCommand getStopDaIntake() {
    return stopDaIntake;
  }

  public DoDaPewPew getDoDaPewPewHigh() {
    return doDaPewPewHigh;
  }

  public DoDaPewPew getDoDaPewPewLow() {
    return doDaPewPewLow;
  }

  public InstantCommand getNoMoPewPew() {
    return noMoPewPew;
  }

  public InstantCommand getChillinWithDaIntake() {
    return chillinWithDaIntake;
  }

  public XboxController getJoystick() {
    return joystick;
  }

  public Rumble getRumble() {
    return rumble;
  }

  public FloopDaColor getFloopDaColor() {
    return floopDaColor;
  }

  public LimelightSteering getLimelightSteeringBall() {
    return limelightSteeringBall;
  }

  public LimelightSteering getLimelightSteeringShooter() {
    return limelightSteeringShooter;
  }

  public TheftOfABall getTheftOfABall() {
    return theftOfABall;
  }

  public LimelightDistance getLimelightDistance() {
    return limelightDistance;
  }

  public DriveAuto createDriveAuto(double x1, double y1, double x2) {
    return new DriveAuto(x1, y1, x2, swerveDrive);
  }
}
