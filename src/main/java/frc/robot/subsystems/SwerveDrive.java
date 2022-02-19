// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SwerveDrive extends SubsystemBase {
  /** Creates a new SwerveDrive. */
  private double L_over_R;
  private double W_over_R;

  private WheelDrive bR;
  private WheelDrive bL;
  private WheelDrive fR;
  private WheelDrive fL;

  public SwerveDrive(double length, double width) {

    double r = Math.sqrt((length * length) + (width + width));
    L_over_R = length / r;
    W_over_R = width / r;

    fL = new WheelDrive("FL", Constants.SPEEDMOTOR_FL, Constants.ANGLEMOTOR_FL, 15, 0.00, 2000, 1023, true);
    fR = new WheelDrive("FR", Constants.SPEEDMOTOR_FR, Constants.ANGLEMOTOR_FR, 15, 0.00, 20, 1023, true);
    bL = new WheelDrive("BL", Constants.SPEEDMOTOR_BL, Constants.ANGLEMOTOR_BL, 15, 0.00, 2000, 1023, true);
    bR = new WheelDrive("BR", Constants.SPEEDMOTOR_BR, Constants.ANGLEMOTOR_BR, 15, 0.00, 20, 1023, true);

  }

  public void drive(double x1, double y1, double x2) {
    // If no joystick input, prevent from turning randomly and ensure motors are
    // stopped
    if (Math.abs(x1) < 0.05 && Math.abs(y1) < 0.05 && Math.abs(x2) < 0.05) {
      bR.stop();
      bL.stop();
      fR.stop();
      fL.stop();
    } else {
      double a = x1 - x2 * L_over_R;
      double b = x1 + x2 * L_over_R;
      double c = y1 - x2 * W_over_R;
      double d = y1 + x2 * W_over_R;

      // Arg1 = Speed = Range of 0 to 1
      // Arg2 = Angle = Range of -1 to 1 (or multiply by 180 for angle)
      bR.drive(Math.sqrt((a * a) + (d * d)), Math.atan2(a, d) / Math.PI);
      bL.drive(Math.sqrt((a * a) + (c * c)), Math.atan2(a, c) / Math.PI);
      fR.drive(Math.sqrt((b * b) + (d * d)), Math.atan2(b, d) / Math.PI);
      fL.drive(Math.sqrt((b * b) + (c * c)), Math.atan2(b, c) / Math.PI);
    }
  }

  @Override
  public void periodic() {
  }
}
