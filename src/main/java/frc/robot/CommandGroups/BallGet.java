/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Commands.BallIntake;
import frc.robot.Commands.BallPickExtend;

public class BallGet extends CommandGroup {
  /**
   * Add your docs here.
   */
  public BallGet() {
   addParallel(new BallIntake());
   addSequential(new BallPickExtend(), 2);
  }
}
