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

public class LiftToHeight extends Command {

  private static int heightInTicks;
 
  public LiftToHeight(int tickHeight)
  {
    requires(Robot.lift);
    heightInTicks = tickHeight;
   }

  // public LiftToHeight() {
  //   // Use requires() here to declare subsystem dependencies
  //   // eg. requires(chassis);
  //   //heightInTicks = 0;
  //   requires(Robot.lift);
  // }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.lift.moveLiftToPosition(heightInTicks);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
   return Robot.lift.liftMotorGetSensorPosition() == heightInTicks && Robot.lift.liftMotorGetVelocity() == 0;
   //return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.lift.liftMotorSet(ControlMode.PercentOutput,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
