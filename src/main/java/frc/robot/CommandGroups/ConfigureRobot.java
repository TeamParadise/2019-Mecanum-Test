/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.BrakeOpen;
import frc.robot.commands.LiftToBottom;

public class ConfigureRobot extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ConfigureRobot()
  {
    Scheduler.getInstance().removeAll(); //remove all running commands
    addSequential(new BrakeOpen(),2);
    addSequential(new WaitCommand(1));
    addSequential(new LiftToBottom(),3);
    }
}
