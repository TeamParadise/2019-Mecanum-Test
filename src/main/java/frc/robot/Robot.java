/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
//import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.SubSystems.ConfigureRobot;
import frc.robot.SubSystems.DriveTrain;
import frc.robot.SubSystems.LiftSystem;
import frc.robot.SubSystems.Pneumatic;
import frc.robot.SubSystems.Sonar;
import frc.robot.commands.LiftToHeight;
import frc.robot.commands.LiftWithJoyStick;

/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive
 * class.
 */
public class Robot extends TimedRobot {

 private int chooserCommand;
 public static final DriveTrain driveTrain = new DriveTrain();
 public static final LiftSystem lift = new LiftSystem();
 public static MecanumDrive m_robotDrive;
 public static Joystick m_stick = new Joystick(RobotMap.kJoystickChannel);;
 SendableChooser<Integer> chooser;
 
 public static Pneumatic discGrabber = new Pneumatic(0,1);

 public static Sonar sonar = new Sonar();

 public static int reportLoops = 0; //used to loop thru whats reporting
 public static final boolean debugTrace = false; //if true dumps info to dirverstation
 
 private boolean done = false;

 @Override
 public void robotInit() {
   Shuffleboard.stopRecording();
  chooserCommand = -1;
  CameraServer.getInstance().addAxisCamera("Camera", "10.11.65.3");
  //SmartDashboard.putData(new LiftToHeight(RobotMap.kLiftBottom));
  //SmartDashboard.putData(new LiftToHeight(RobotMap.kLiftDiscLevel1));
  // //SmartDashboard.putData(new LiftToHeight(RobotMap.kLiftBallLevel3));
  chooser = new SendableChooser<>();
  chooser.setName("Lift Position");
  chooser.addDefault("Default", -1);
  chooser.addObject("Lift Bottom", RobotMap.kLiftBottom);
  chooser.addObject("Disc Level 1", RobotMap.kLiftDiscLevel1);
  chooser.addObject("Ball Level 1", RobotMap.kLiftBallLevel1);
  SmartDashboard.putData(chooser);

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

 private void configureRobot()
 {
  if(!done)
  {
    new ConfigureRobot().start();
  }
  done = true;
 }


 @Override
 public void teleopPeriodic() {

    configureRobot();
    //if (m_stick.getRawButtonPressed(11)) LiftToHeight(1000);
    if(chooserCommand != chooser.getSelected())
    {
      chooserCommand = chooser.getSelected();
      //SmartDashboard.putNumber("Command Running is ", chooserCommand);
      if(chooserCommand != -1)
        new LiftToHeight(chooserCommand).start();
      else
        new LiftWithJoyStick().start();
    }
    //we don't need to report everything every time and io is slow let's slow this down
    if (++reportLoops == 1) sonar.report(debugTrace);
    else if (reportLoops ==2) lift.report(debugTrace);
    else if (reportLoops == 10) reportLoops = 0; //start the reporting process over

    if (m_stick.getRawButton(RobotMap.kResetLiftPosition)) lift.resetLiftPosition(); 


    if (m_stick.getRawButtonPressed(RobotMap.kGrabExtend)) discGrabber.extend();
    if (m_stick.getRawButtonPressed(RobotMap.kGrabRetract)) discGrabber.retract();
    if (m_stick.getRawButtonPressed(RobotMap.kGrabIdle)) discGrabber.idle();

    
    Scheduler.getInstance().run(); //causes all default commands to run
  }
}
