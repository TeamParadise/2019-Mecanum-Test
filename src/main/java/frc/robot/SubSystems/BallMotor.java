/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.SubSystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class BallMotor extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static TalonSRX Ballmotor  = new TalonSRX(RobotMap.kBallChannel);

  public BallMotor()
  {
    Ballmotor.setInverted(true);
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void  set(double power)
  {
    Ballmotor.set(ControlMode.PercentOutput, power);
  }
}
