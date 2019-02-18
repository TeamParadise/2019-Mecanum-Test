package frc.robot.SubSystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Commands.DriveWithJoystick;

public class DriveTrain extends Subsystem
{
 
    // WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.kFrontLeftChannel);
	// WPI_TalonSRX rearLeft = new WPI_TalonSRX(RobotMap.kRearLeftChannel);
	// WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.kFrontRightChannel);
	// WPI_TalonSRX rearRight = new WPI_TalonSRX(RobotMap.kRearRightChannel);
 
	WPI_VictorSPX frontLeft = new WPI_VictorSPX(RobotMap.kFrontLeftChannel);
	WPI_VictorSPX rearLeft = new WPI_VictorSPX(RobotMap.kRearLeftChannel);
	WPI_VictorSPX frontRight = new WPI_VictorSPX(RobotMap.kFrontRightChannel);
	WPI_VictorSPX rearRight = new WPI_VictorSPX(RobotMap.kRearRightChannel);

	public MecanumDrive robotDrive;
	
	private boolean isRunning = false;
	
	private String lastReport = "none";

 	public DriveTrain()
	{
		robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
		
		//Fix Output Not Enabled Error
		robotDrive.setSafetyEnabled(false);
		frontLeft.setSafetyEnabled(false);
		frontRight.setSafetyEnabled(false);
		rearRight.setSafetyEnabled(false);
		rearLeft.setSafetyEnabled(false);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveWithJoystick());
	}

	public void driveCartesian(double x, double y, double twist, double gyroAngle)
	{
    isRunning = Math.abs(x) >= 0.2 || Math.abs(y) >= 0.2 || Math.abs(twist) >= 0.3 ? true : false;
    
	//	DriverStation.reportWarning(x+","+y+","+twist, false);
	robotDrive.driveCartesian(x, -y, twist*.5, gyroAngle);
		// SmartDashboard.putNumber("Heading", Robot.navXSource.getHeading());
	}
	
	public boolean isRunning()
	{
		return isRunning;
	}	
	public void report(boolean debugTrace)
	{
		if (debugTrace) DriverStation.reportWarning("Report DriveTrain", false);

		StringBuilder _sb = new StringBuilder();
 		/* Prepare line to print */
		//  _sb.append("out:");
		 /* Cast Talon's current output percentageto int to remove decimal places */
		 _sb.append("fl: ");	// FrontLeft Motor
		 _sb.append((int) (frontLeft.getMotorOutputPercent() * 100));
		 _sb.append("%");	// Percent

		 _sb.append(" rl: ");	// RearLeft  Motor
		 _sb.append((int) (rearLeft.getMotorOutputPercent() * 100));
		 _sb.append("%");	// Percent

		 _sb.append(" fr: ");	// FrontRight Motor
		 _sb.append((int) (frontRight.getMotorOutputPercent() * 100));
		 _sb.append("%");	// Percent

		 _sb.append(" rr: ");	// RearRight Motor
		 _sb.append((int) (rearRight.getMotorOutputPercent() * 100));
		 _sb.append("%");	// Percent	
		
		 String output = _sb.toString();
		 
     if (!lastReport.equals(output))
    	{	
     	 	SmartDashboard.putString("DriveTrain", output);
      		lastReport = output;
    	}
	}
}