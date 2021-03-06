/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class BallMotorShoot extends Command {
  public BallMotorShoot() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.ballMotor);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.ballMotor.set(0.75);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !(Robot.m_stick.getRawButton(RobotMap.kBallShoot) || Robot.m_stick1.getRawButton(RobotMap.kBallShoot));
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.ballMotor.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
