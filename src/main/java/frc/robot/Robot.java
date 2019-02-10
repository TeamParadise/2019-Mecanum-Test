/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.List;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
//import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.SubSystems.DriveTrain;
import frc.robot.SubSystems.LiftSystem;
import frc.robot.SubSystems.Pneumatic;

/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive
 * class.
 */
public class Robot extends TimedRobot {

 
 public static final DriveTrain driveTrain = new DriveTrain();
 public static MecanumDrive m_robotDrive;
 public static Joystick m_stick = new Joystick(RobotMap.kJoystickChannel);;

 public static Ultrasonic rightSonar = new Ultrasonic(RobotMap.kRightPingChannel,RobotMap.kRightEchoChannel,Ultrasonic.Unit.kInches);
 public static Ultrasonic leftSonar = new Ultrasonic(RobotMap.kLeftPingChannel,RobotMap.kLeftEchoChannel,Ultrasonic.Unit.kInches);
 

 public static Pneumatic discGrabber = new Pneumatic(0,1);
 
 
 @Override
 public void robotInit() {
   rightSonar.setAutomaticMode(true);
   leftSonar.setAutomaticMode(true);
   CameraServer.getInstance().addAxisCamera("Camera", "10.11.65.3");
   
/*   try {
    Field field1 = rightSonar.getClass().getDeclaredField("m_sensors");

    System.out.println(field1);
    field1.setAccessible(true);

    List<Ultrasonic> fieldValue = (List<Ultrasonic>) field1.get(rightSonar);

    System.out.println("success");
    System.out.println(fieldValue);

    for(Ultrasonic ultra: fieldValue) {
      System.out.println(ultra.getName());
      System.out.println(ultra.isEnabled());
    }
    
  }catch (NoSuchFieldException error) {
    System.out.println("no such field exception");
  } catch(IllegalAccessException error) {
    System.out.println("illegal access exception");  
  }
*/

   // Invert the left side motors.
   // You may need to change or remove this to match your robot.
   //frontRight.setInverted(true);
   //rearRight.setInverted(true);
 }

 @Override
 public void teleopPeriodic() {

  //System.out.printf("left: %f, right: %f\n", leftSonar.getRangeInches(), rightSonar.getRangeInches());

  SmartDashboard.putNumber("Left Sonar", (int)leftSonar.getRangeInches());
    SmartDashboard.putNumber("Right Sonar", (int)rightSonar.getRangeInches());

    if (m_stick.getRawButtonPressed(RobotMap.kGrabExtend)) discGrabber.extend();
    if (m_stick.getRawButtonPressed(RobotMap.kGrabRetract)) discGrabber.retract();
    if (m_stick.getRawButtonPressed(RobotMap.kGrabIdle)) discGrabber.idle();

    double power = 0;
    power = m_stick.getThrottle();
    if (Math.abs(power) < 0.2 ) power = 0;
    //System.out.println(power);
    LiftSystem.Liftmotor.set(power);
   // System.out.println(LiftSystem.Liftmotor.getEncPosition());
    Scheduler.getInstance().run(); //causes all default commands to run
  }
}


