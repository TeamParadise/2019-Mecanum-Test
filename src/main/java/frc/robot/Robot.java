/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
//import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.CommandGroups.BallGet;
// import frc.robot.CommandGroups.BallGot;
// import frc.robot.CommandGroups.DiscPickUp;
// import frc.robot.CommandGroups.DiscPickUpExtend;
// import frc.robot.CommandGroups.DiscRelease;
// import frc.robot.CommandGroups.DiscReleaseExtend;
// import frc.robot.CommandGroups.MoveLift;
import frc.robot.CommandGroups.ConfigureRobot;
import frc.robot.SubSystems.BallMotor;
import frc.robot.SubSystems.DriveTrain;
import frc.robot.SubSystems.LiftSystem;
import frc.robot.SubSystems.NAVxSubSystem;
import frc.robot.SubSystems.PneumaticDouble;
import frc.robot.SubSystems.Sonar;
import frc.robot.commands.AutoPilotSonarRobot;
import frc.robot.commands.BallShoot;
import frc.robot.commands.BrakeClose;
import frc.robot.commands.BrakeOpen;
import frc.robot.commands.DiscGrabberDownwards;
import frc.robot.commands.DiscGrabberExtend;
import frc.robot.commands.DiscGrabberRetract;
import frc.robot.commands.DiscGrabberUpwards;
import frc.robot.commands.LiftWithJoyStick;

/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive
 * class.
 */
public class Robot extends TimedRobot {

 //private int chooserCommand;
 //SendableChooser<Integer> chooser;
 
 public static final DriveTrain driveTrain = new DriveTrain();
 public static final LiftSystem lift = new LiftSystem();
 public static final BallMotor ballMotor = new BallMotor();
 public static final NAVxSubSystem NAVx = new NAVxSubSystem();
 public static MecanumDrive m_robotDrive;
 public static Joystick m_stick = new Joystick(RobotMap.kJoystickChannel);
 public static Joystick m_stick1 = new Joystick(RobotMap.kJoystickChannel1);
 
 public static PneumaticDouble discLifter = new PneumaticDouble(RobotMap.kPcm0, RobotMap.kGrabUpwards, RobotMap.kGrabDownwards);
 public static PneumaticDouble discGrabber = new PneumaticDouble(RobotMap.kPcm0, RobotMap.kGrabExtendChannel, RobotMap.kGrabRetractChannel);
 public static PneumaticDouble ballGrabber = new PneumaticDouble(RobotMap.kPcm0, RobotMap.kBallExtend, RobotMap.kBallRetract);
 public static PneumaticDouble brakeGrabber = new PneumaticDouble(RobotMap.kPcm0 , RobotMap.kBrakeOpen, RobotMap.kBrakeClose);
 
 public static Sonar sonar = new Sonar();

 public static int reportLoops = 0; //used to loop thru whats reporting
 public static final boolean debugTrace = false; //if true dumps info to dirverstation
 
 private boolean done = false;
 public boolean grabberIn = true;
 public boolean grabberDown = true;

 @Override
 public void robotInit() {
   Shuffleboard.stopRecording();
  //chooserCommand = -1;
  //CameraServer.getInstance().addAxisCamera("Camera", "10.11.65.3");
  //SmartDashboard.putData(new LiftToHeight(RobotMap.kLiftBottom));
  //SmartDashboard.putData(new LiftToHeight(RobotMap.kLiftDiscLevel1));
  // //SmartDashboard.putData(new LiftToHeight(RobotMap.kLiftBallLevel3));
  // chooser = new SendableChooser<>();
  // chooser.setName("Lift Position");
  // chooser.addDefault("Default", -1);
  // chooser.addObject("Lift Bottom", RobotMap.kLiftBottom);
  // chooser.addObject("Disc Level 1", RobotMap.kLiftDiscLevel1);
  // chooser.addObject("Ball Level 1", RobotMap.kLiftBallLevel1);
  // chooser.addObject("Disc Level 2", RobotMap.kLiftDiscLevel2);
  // chooser.addObject("Ball Level 2", RobotMap.kLiftBallLevel2);
  // chooser.addObject("Disc Level 3", RobotMap.kLiftDiscLevel3);
  // chooser.addObject("Ball Level 3", RobotMap.kLiftBallLevel3);
  // SmartDashboard.putData(chooser);

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
    lift.resetLiftPosition();
  }
  done = true;
 }


 @Override
 public void autonomousPeriodic() {
   super.autonomousPeriodic();
   runRobot();
 }

 @Override
 public void teleopPeriodic()
 {
   super.teleopPeriodic();
   runRobot();
 }

public void runRobot()
{
    configureRobot();
    //if (m_stick.getRawButtonPressed(11)) LiftToHeight(1000);
    // if(chooserCommand != chooser.getSelected())
    // {
    //   chooserCommand = chooser.getSelected();
    //   //SmartDashboard.putNumber("Command Running is ", chooserCommand);
    //   if(chooserCommand != -1)
    //     new MoveLift(chooserCommand).start();
    //   // else new LiftWithJoyStick().start();
    // }
    // // else if (m_stick.getRawButton(RobotMap.kBallLevel1) || m_stick1.getRawButton(RobotMap.kBallLevel1)) new MoveLift(RobotMap.kLiftBallLevel1).start();
    // else if (m_stick.getRawButton(RobotMap.kBallLevel2) || m_stick1.getRawButton(RobotMap.kBallLevel2)) new MoveLift(RobotMap.kLiftBallLevel2).start();
    // else if (m_stick.getRawButton(RobotMap.kBallLevel3) || m_stick1.getRawButton(RobotMap.kBallLevel3)) new MoveLift(RobotMap.kLiftBallLevel3).start();
    // else if (m_stick.getRawButton(RobotMap.kDiscLevel1) || m_stick1.getRawButton(RobotMap.kDiscLevel1)) new MoveLift(RobotMap.kLiftDiscLevel1).start();
    // else if (m_stick.getRawButton(RobotMap.kDiscLevel2) || m_stick1.getRawButton(RobotMap.kDiscLevel2)) new MoveLift(RobotMap.kLiftDiscLevel2).start();
    // else if (m_stick.getRawButton(RobotMap.kDiscLevel3) || m_stick1.getRawButton(RobotMap.kDiscLevel3)) new MoveLift(RobotMap.kLiftDiscLevel3).start();
 

    //we don't need to report everything every time and io is slow let's slow this down
    if (++reportLoops == 1) sonar.report(debugTrace);
    else if (reportLoops == 2) lift.report(debugTrace);
    else if (reportLoops == 3) driveTrain.report(debugTrace);
    else if (reportLoops == 4) NAVx.report(debugTrace);
    else if (reportLoops == 10) reportLoops = 0; //start the reporting process over

    // if(m_stick.getRawButtonPressed(RobotMap.kBallShoot) || m_stick1.getRawButtonPressed(RobotMap.kBallShoot)) new BallShoot().start();
    // else if (m_stick.getRawButtonReleased(RobotMap.kBallpickup) || m_stick1.getRawButtonReleased(RobotMap.kBallpickup)) new BallGot().start();
    // else if (m_stick.getRawButtonPressed(RobotMap.kBallpickup) || m_stick1.getRawButtonPressed(RobotMap.kBallpickup) ) new BallGet().start();
    // else if (m_stick.getRawButtonReleased(RobotMap.kDiscPlace) || m_stick1.getRawButtonReleased(RobotMap.kDiscPlace)) new DiscRelease().start();
    // else if (m_stick.getRawButtonReleased(RobotMap.kDiscGet) || m_stick1.getRawButtonReleased(RobotMap.kDiscGet)) new DiscPickUp().start();
    // else if (m_stick.getRawButtonPressed(RobotMap.kDiscGet) || m_stick1.getRawButtonPressed(RobotMap.kDiscGet)) new DiscPickUpExtend().start();
    // else if (m_stick.getRawButtonPressed(RobotMap.kDiscPlace) || m_stick1.getRawButtonPressed(RobotMap.kDiscPlace)) new DiscReleaseExtend().start();
    if (m_stick.getRawButtonPressed(RobotMap.kJGrabberInOut) || m_stick1.getRawButtonPressed(RobotMap.kJGrabberInOut))
    {
      if (grabberIn)
      {
        new DiscGrabberExtend().start();
        grabberIn = false;
      }
      else
      {
        new DiscGrabberRetract().start();
        grabberIn = true;
      }
    }
    if (m_stick.getRawButtonPressed(RobotMap.kJGrabberUpDown) || m_stick1.getRawButtonPressed(RobotMap.kJGrabberUpDown))
    {
      if (grabberDown)
      {
        new DiscGrabberUpwards().start();
        grabberDown = false;
      }
      else
      {
        new DiscGrabberDownwards().start();
        grabberDown = true;
      }
    }
    
    if (m_stick.getRawButtonPressed(9)) new AutoPilotSonarRobot().start();;
    //if (m_stick.getRawButtonPressed(7)) new BrakeOpen().start();
    //if (m_stick.getRawButtonReleased(7)) new BrakeClose().start();

     //if (m_stick.getRawButton(RobotMap.kResetLiftPosition)) lift.resetLiftPosition(); 
    //if (m_stick.getRawButtonPressed(RobotMap.kGrabExtend)) discGrabber.extend();
    //if (m_stick.getRawButtonPressed(RobotMap.kGrabRetract)) discGrabber.retract();
    //if (m_stick.getRawButtonPressed(RobotMap.kGrabIdle)) discGrabber.idle();

    
    Scheduler.getInstance().run(); //causes all default commands to run
  }
}
