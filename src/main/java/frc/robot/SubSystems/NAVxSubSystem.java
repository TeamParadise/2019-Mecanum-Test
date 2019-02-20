/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.SubSystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NAVxSubSystem extends Subsystem {

  private static AHRS NAVx;
    
  private String lastReport = "none.";

  public NAVxSubSystem()
  {
    try {
      NAVx = new AHRS(Port.kMXP);
      NAVx.reset();
   } catch (Exception e) {
     DriverStation.reportError("NAVx not instantiated.",false);
   }
   if (!NAVx.isConnected()) DriverStation.reportError("NAVx not connected",false);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void report(boolean debugTrace)
	{
 		if (debugTrace) DriverStation.reportWarning("Report NAVx", false);

		StringBuilder _sb = new StringBuilder();
 		/* Prepare line to print */
		//  _sb.append("out:");
		 /* Cast Talon's current output percentageto int to remove decimal places */
		 _sb.append(String.format("cmp: %.1f",NAVx.getCompassHeading()));
	   _sb.append(String.format("ang: %.1f ",NAVx.getAngle()));
     _sb.append(NAVx.getAltitude()+" ft");

     String output = _sb.toString();
			
     if (!lastReport.equals(output))
     {
        SmartDashboard.putString("NAVx", output);
       lastReport = output;
     }
 
  }
}
