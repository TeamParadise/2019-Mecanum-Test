/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.BallIntake;
import frc.robot.commands.BallPickExtend;

public class BallGet extends CommandGroup {
  /**
   * Add your docs here.
   */
  public BallGet() {
   //addSequential(new MoveLift(RobotMap.kLiftBottom), 2);
   addParallel(new BallPickExtend(), 2);
   addSequential(new BallIntake());
  }
}
