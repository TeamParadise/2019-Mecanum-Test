/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ReportPressureSensor;
import edu.wpi.first.wpilibj.AnalogInput;

public class AnalogPressureSensor extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  /**
 * Wraps an analog input for a Rev Robotics Analog Pressure sensor.
 *
 * http://www.revrobotics.com/wp-content/uploads/2015/11/REV-11-1107-DS-00.pdf
 * http://www.revrobotics.com/rev-11-1107/
 */
  AnalogInput pressureSensor = new AnalogInput(RobotMap.analogPressureSensorChannel);

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new ReportPressureSensor());
  }
	/*
	 * Equation:
	 * 
	 * v0 = pressureSensor.getVoltage()
	 * v_out = v_cc(5) * (0.004 * p(120) + 0.1) = 2.9
   * v_n = v0/(0.004 * p0(120) + 0.1) = v0/0.58
	 * 
	 * p = 250(v_out(2.9)/v_n) - 25
	 * 
   * p = 250(2.9/(pressureSensor.getVoltage()/0.58)) - 25;
   */

  public double getPressure()
	{
    //Not going to bother with normalization, because a ballpark is good enough
    return 120*pressureSensor.getVoltage()/2.8; //2.8 Volts at 120psi, 0 @0 //250 * (2.9/(pressureSensor.getVoltage()/0.58)) - 25;
		//return 191.4 * (pressureSensor.getVoltage() - 0.717773364) + 85;
    //return 50 * mPressureSensor.getVoltage() - 25;
	}
  public void report()
  {
    SmartDashboard.putNumber("Pressure Sensor", this.getPressure());
    //SmartDashboard.putNumber("Voltage", pressureSensor.getVoltage());
  }
}
