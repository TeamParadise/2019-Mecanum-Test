/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Commands.BallMotorStop;
import frc.robot.Commands.BallPickRetract;

public class BallGot extends CommandGroup {
  /**
   * Add your docs here.
   */
  public BallGot() {
   addParallel(new BallMotorStop());
   addSequential(new BallPickRetract());
  }
}
