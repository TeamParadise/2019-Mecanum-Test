/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class PneumaticDouble extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DoubleSolenoid doubleSolenoid;
  
  public PneumaticDouble(int pcm, int extendChannel, int retractChannel)
  {
    doubleSolenoid = new DoubleSolenoid(pcm, extendChannel, retractChannel);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void extend() {
    doubleSolenoid.set(DoubleSolenoid.Value.kForward);
  }
  public void retract() {
    doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
  public void idle() {
    doubleSolenoid.set(DoubleSolenoid.Value.kOff);
  }
}
