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
import frc.robot.commands.DiscGrabberExtend;

public class DiscPickUpExtend extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DiscPickUpExtend() {
    addSequential(new DiscGrabberDownwards(), 0.8);
    //addSequential(new WaitCommand(0.3));
    addSequential(new DiscGrabberExtend(), 0.1);
  }
}
