/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
//import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.SubSystems.DriveTrain;
import frc.robot.SubSystems.Sonar;

/**
* This is a demo program showing how to use Mecanum control with the RobotDrive
* class.
*/
public class Robot extends TimedRobot {

 private static final int kJoystickChannel = 0;

 public static final DriveTrain driveTrain = new DriveTrain();
 public static MecanumDrive m_robotDrive;
 public static Joystick m_stick = new Joystick(kJoystickChannel);;

 public static Sonar rightSonar = new Sonar(0,1);
 public static Sonar leftSonar = new Sonar(4,3);
 
 @Override
 public void robotInit() {
   rightSonar.SetupSonar();
   leftSonar.SetupSonar();
   CameraServer.getInstance().addAxisCamera("Camera", "10.11.65.3");
   
   // Invert the left side motors.
   // You may need to change or remove this to match your robot.
   //frontRight.setInverted(true);
   //rearRight.setInverted(true);
 }

 @Override
 public void teleopPeriodic() {
    SmartDashboard.putNumber("Left Sonar", (int)leftSonar.getInches());
    SmartDashboard.putNumber("Right Sonar", (int)rightSonar.getInches());

    Scheduler.getInstance().run(); //causes all default commands to run
  }
}


