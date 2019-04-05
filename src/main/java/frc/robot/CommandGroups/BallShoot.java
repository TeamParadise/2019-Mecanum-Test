/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.BallMotorShoot;
import frc.robot.commands.BallMotorStop;
import frc.robot.commands.BallPickRetract;

public class BallShoot extends CommandGroup {
  /**
   * Add your docs here.
   */
  public BallShoot() {
   addSequential(new BallMotorShoot());
   addSequential(new WaitCommand(0.6));
   addSequential(new BallPickRetract());
   addSequential(new BallMotorStop());
   //addSequential(new MoveLift(RobotMap.kLiftBallLevel1), 2);
  }
}
