/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class WheelDrive extends SubsystemBase {
  private WPI_TalonSRX angleMotor;
  private WPI_TalonSRX speedMotor;
  private int encoderTicks;
  private boolean debug;
  private String name;

  public WheelDrive(String name, int speedMotorID, int angleMotorID, double kP, double kI, double kD, int encoderTicks, boolean debug) {
    this.name = name;
    this.encoderTicks = encoderTicks;
    this.debug = debug;

    angleMotor = new WPI_TalonSRX(angleMotorID);
    speedMotor = new WPI_TalonSRX(speedMotorID);

    speedMotor.setNeutralMode(NeutralMode.Brake);

    angleMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
    angleMotor.config_kP(0, kP);
    angleMotor.config_kI(0, kI);
    angleMotor.config_kD(0, kD);
    angleMotor.selectProfileSlot(0, 0);
  }

  public void drive(double speed, double angleRange) {
    int actualPos = angleMotor.getSensorCollection().getAnalogInRaw();
    int desirePos = (int) ((angleRange + 1) / 2 * encoderTicks); // Angle input is -1 to 1, so covert it to 0 to 1,
                                                                 // desired output is 0 to 1023

    // You should never have to move your swerve > 90 degrees. Just move the other
    // way an flip the motor speed.
    // Courtesy of 2481
    if (Math.abs(desirePos - actualPos) > (encoderTicks / 4) &&
        Math.abs(desirePos - actualPos) < (encoderTicks / 4 * 3)) {
      desirePos = (desirePos + (encoderTicks / 2)) % encoderTicks;
      speed = -speed;
    }

    speedMotor.set(ControlMode.PercentOutput, speed / 2);
    angleMotor.set(ControlMode.Position, desirePos);

    // Debugging code (using a parameter so you can turn on for one motor)
    if (debug) {
      System.out.printf("%s: Speed: %.1f, Angle (-1..1): %.2f, Sensor (actual/desired): %d, %d\n", name, speed,
          angleRange, actualPos, desirePos);
    }
  }

  public void stop() {
    speedMotor.set(ControlMode.PercentOutput, 0);
    angleMotor.set(ControlMode.PercentOutput, 0);
  }

  public void setBreakMode(boolean breakMode) {
    if (breakMode) {
      speedMotor.setNeutralMode(NeutralMode.Brake);
      angleMotor.setNeutralMode(NeutralMode.Brake);
    }
    else {
      speedMotor.setNeutralMode(NeutralMode.Coast);
      angleMotor.setNeutralMode(NeutralMode.Coast);
    }
  }

  @Override
  public void periodic() {
  }
}
