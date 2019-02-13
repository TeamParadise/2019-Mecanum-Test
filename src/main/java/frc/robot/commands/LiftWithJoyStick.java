/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.SubSystems.LiftSystem;


public class LiftWithJoyStick extends Command {
  public LiftWithJoyStick() {
    requires(Robot.lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double power = 0;
    power = Robot.m_stick.getThrottle();
    if (Math.abs(power) < 0.2  || !Robot.m_stick.getRawButton(RobotMap.kConfirmManualLift)) power = 0;
    //System.out.println(power);
    //set the motor to percent output -1.0 to 1 and feed the throttle position to get power
    LiftSystem.Liftmotor.set(ControlMode.PercentOutput,power);
   // System.out.println(LiftSystem.Liftmotor.getEncPosition());

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
