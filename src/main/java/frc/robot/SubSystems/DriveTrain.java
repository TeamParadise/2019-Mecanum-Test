package frc.robot.SubSystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveWithJoystick;

public class DriveTrain extends Subsystem
{
 
    WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.kFrontLeftChannel);
	WPI_TalonSRX rearLeft = new WPI_TalonSRX(RobotMap.kRearLeftChannel);
	WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.kFrontRightChannel);
	WPI_TalonSRX rearRight = new WPI_TalonSRX(RobotMap.kRearRightChannel);

	private int rearLeftVal = 0;
	private int rearRightVal = 1;

	private static final double Kp = 0.3;
	private static final double Ki = 0.0;
	private static final double Kd = 0.0;
	
	private static final int kMaxNumberOfMotors = 2;
	
	private static final double maxOutput = 0.85;

	public MecanumDrive robotDrive;
	
	private boolean isRunning = false;
	
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
	public void report()
	{
  }
}