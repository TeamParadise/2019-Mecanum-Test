/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Sonar extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static Ultrasonic leftSonar = new Ultrasonic(RobotMap.kLeftPingChannel,RobotMap.kLeftEchoChannel,Ultrasonic.Unit.kInches);
  //private static Ultrasonic rightSonar = new Ultrasonic(RobotMap.kRightPingChannel,RobotMap.kRightEchoChannel,Ultrasonic.Unit.kInches);
  private String lastReport = "";
  private double lastDiff = -1;

  public Sonar()
  {
    //rightSonar.setAutomaticMode(true);
    leftSonar.setAutomaticMode(true);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
 
  public double righttDistance()
  {
    return 0; //only 1 installed //rightSonar.getRangeInches();
  }

  public double leftDistance()
  {
    return leftSonar.getRangeInches();
  }
 
  public double sonarDifference()
  {
    double difference = righttDistance() - leftDistance();
    double tempdiff = difference;
    if (lastDiff != -1 && Math.abs(lastDiff - difference) >51)
      difference = 0;
    else if(Math.abs(difference) < .1 )
      difference = 0;
    else lastDiff = difference;
    return difference;
  }

  public void report(boolean debugTrace)
  {
    if (debugTrace) DriverStation.reportWarning("Report Sonar", false);
    //System.out.printf("left: %f, right: %f\n", leftSonar.getRangeInches(), rightSonar.getRangeInches());
  
    StringBuilder _sb = new StringBuilder();
    _sb.setLength(0);
    _sb.append("Lt:");
    _sb.append((int)leftDistance());
    _sb.append(" Rt:");
    _sb.append((int)righttDistance());
    _sb.append(" Diff:");
    //int diff = (int)(sonarDifference()*10);
   // _sb.append(diff/10.0);
   _sb.append(String.format("%.1f", sonarDifference()));
   

    String output = _sb.toString();
    if (!lastReport.equals(output))
    {
      SmartDashboard.putString("Sonar", output);
      lastReport = output;
    }
  
    //SmartDashboard.putNumber("Left Sonar", (int)leftSonar.getRangeInches());
    //SmartDashboard.putNumber("Right Sonar", (int)rightSonar.getRangeInches());

  }
}
