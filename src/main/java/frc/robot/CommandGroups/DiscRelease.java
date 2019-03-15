/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.DiscGrabberDownwards;
import frc.robot.commands.DiscGrabberRetract;

public class DiscRelease extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DiscRelease() {
    addSequential(new DiscGrabberDownwards(), 0.8);
    //addSequential(new WaitCommand(0.1));
    addSequential(new DiscGrabberRetract(), 0.1);
  
  }
}
