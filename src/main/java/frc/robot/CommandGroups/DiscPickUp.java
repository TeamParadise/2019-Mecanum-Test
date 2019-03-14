/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.DiscGrabberExtend;
import frc.robot.commands.DiscGrabberRetract;
import frc.robot.commands.DiscGrabberUpwards;

public class DiscPickUp extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DiscPickUp() {
    addSequential(new DiscGrabberExtend(), 2);
    addSequential(new WaitCommand(1));
    addSequential(new DiscGrabberUpwards(), 2);
    addSequential(new WaitCommand(1));
    addSequential(new DiscGrabberRetract(), 2);
  }
}