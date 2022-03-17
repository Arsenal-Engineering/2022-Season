/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST and other WPILib contributors.                         */
/* Open Source Software; you can modify and/or share it under the terms of    */
/* the WPILib BSD license file in the root directory of this project.         */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveDrive extends SubsystemBase {
  private double L_over_R;
  private double W_over_R;

  private WheelDrive bR;
  private WheelDrive bL;
  private WheelDrive fR;
  private WheelDrive fL;

  private boolean fieldOrientated;

  public SwerveDrive(double length, double width, int speedBR_ID, int angleBR_ID, int speedBL_ID, int angleBL_ID, int speedFR_ID, int angleFR_ID, int speedFL_ID, int angleFL_ID) {

    double r = Math.sqrt((length * length) + (width + width));
    L_over_R = length / r;
    W_over_R = width / r;

    bR = new WheelDrive("BR", speedBR_ID, angleBR_ID, 10, 0.00, 20, 1023, true);
    bL = new WheelDrive("BL", speedBL_ID, angleBL_ID, 15, 0.00, 20, 1023, false);
    fR = new WheelDrive("FR", speedFR_ID, angleFR_ID, 13, 0.00, 20, 1023, true);
    fL = new WheelDrive("FL", speedFL_ID, angleFL_ID, 20, 0.00, 20, 1023, false);

    fieldOrientated = true;
  }

  public void drive(double x1, double y1, double x2) {
    // If no joystick input, prevent from turning randomly and ensure motors are stopped
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
      // Compared to the documents we used, the math for left and right wheels on our code are
      // swapped because our x-axis is switched
    }
  }

  public void driveForward() {
    drive(0, -1, 0);
  }

  public void driveBackward() {
    drive(0, 1, 0);
  }

  public boolean getFieldOrientated() {
    return fieldOrientated;
  }

  public void setFieldOrientated() {
    fieldOrientated = true;
  }

  public void setFieldOrientatedRegular() {
    fieldOrientated = false;
  }

  public void setBrakeMode(boolean brakeMode) {
    bR.setBreakMode(brakeMode);
    bL.setBreakMode(brakeMode);
    fR.setBreakMode(brakeMode);
    fL.setBreakMode(brakeMode);
  }

  @Override
  public void periodic() {
  }
}
