/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoPilotSonarRobot extends Command {
  public AutoPilotSonarRobot() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveTrain.setAutoDrive(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   Robot.driveTrain.driveWithSonar();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.sonar.leftDistance() <= 5; 
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.setAutoDrive(false);
    Robot.driveTrain.driveCartesian(0, 0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
