/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DiscGrabberExtend;
import frc.robot.commands.DiscGrabberOpen;

public class DiscReleaseExtend extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DiscReleaseExtend() {
    addSequential(new DiscGrabberOpen(), 0.8);
    //addSequential(new WaitCommand(0.3));
    addSequential(new DiscGrabberExtend(), 0.1);
  }
}
