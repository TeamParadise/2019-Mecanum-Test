/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.SubSystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.LiftWithJoyStick;

/**
 * Add your docs here.
 */
public class LiftSystem extends Subsystem {
  
  // Which PID slot to pull gains from. Choose from 0,1,2 or 3.
	public static final int kSlotIdx = 0;

	//Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For now we just want the primary one.
	public static final int kPIDLoopIdx = 0;

	// Set to zero to skip waiting for confirmation, set to nonzero to wait and report to DS if action fails.
	public static final int kTimeoutMs = 30;
	
	/* Choose so that Talon does not report sensor out of phase */
	public static boolean kSensorPhase = true;

	public static boolean kMotorInvert = false;

  public final double kP = 0.15;
	public final double kI = 0.0;
	public final double kD = 1.0;
	public final double kF = 0.0;
	public final int kIzone = 0;
  public final double kPeakOutput = 1.0;
  
  /** Used to create string thoughout loop */
  boolean _printresults = true;
	StringBuilder _sb = new StringBuilder();
	int _loops = 0;


	public static WPI_TalonSRX Liftmotor  = new WPI_TalonSRX(RobotMap.kLiftChannel);

  public LiftSystem() {
    /* Config the sensor used for Primary PID and sensor direction */
		Liftmotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, kPIDLoopIdx, kTimeoutMs);
		// Liftmotor.reverseSensor(true);
		// Liftmotor.reverseOutput(true);

    /*causes encoder to zero when reverse limit switch is hit*/
    Liftmotor.configClearPositionOnLimitR(true, kTimeoutMs);

    /*set the current positioon of the encoder on initialization to 0, will reset to 0 again when
      it hits the lower limit switch due to configClearPositionOnLimitR above*/
    Liftmotor.setSelectedSensorPosition(0,0,kTimeoutMs);

    /* Ensure sensor is positive when output is positive */
		Liftmotor.setSensorPhase(kSensorPhase);

		/**
		 * Set based on what direction you want forward/positive to be.
		 * This does not affect sensor phase. 
		 */ 
    Liftmotor.setInverted(kMotorInvert);
    
    /* Config the peak and nominal outputs, 12V means full */
		Liftmotor.configNominalOutputForward(0, kTimeoutMs);
		Liftmotor.configNominalOutputReverse(0, kTimeoutMs);
		Liftmotor.configPeakOutputForward(kPeakOutput, kTimeoutMs);
    Liftmotor.configPeakOutputReverse(-kPeakOutput, kTimeoutMs);
    
    /**
		 * Config the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		Liftmotor.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);

		/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		Liftmotor.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
		Liftmotor.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
		Liftmotor.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
		Liftmotor.config_kD(kPIDLoopIdx, kD, kTimeoutMs);

		/**
		 * Grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */
		int absolutePosition = Liftmotor.getSensorCollection().getPulseWidthPosition();

		/* Mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		if (kSensorPhase) { absolutePosition *= -1; }
		if (kMotorInvert) { absolutePosition *= -1; }
		
		/* Set the quadrature (relative) sensor to match absolute */
		Liftmotor.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);

   }

  //uses the PID internal to the Talon as configured in initialization to move the lift to the desired height
  public void moveLiftToPosition(int desiredheight)
  {
		//ref: https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/tree/master/Java/PositionClosedLoop/src/main/java/frc/robot
 		/* Prepare line to print */
		_sb.append("\tout:");
		/* Cast Talon's current output percentageto int to remove decimal places */
		_sb.append((int) (Liftmotor.getMotorOutputPercent() * 100));
		_sb.append("%");	// Percent

		_sb.append("\tpos:");
		_sb.append(Liftmotor.getSelectedSensorPosition(0));
    _sb.append("u"); 	// Native units
		
		//Liftmotor.setSelectedSensorPosition(desiredheight, kPIDLoopIdx, kTimeoutMs);
    //a cimcoder has 20 ticks per revolution, but in this case we know how many ticks we desire
    Liftmotor.set(ControlMode.Position, desiredheight);

    /* If Talon is in position closed-loop, print some more info */
		if (Liftmotor.getControlMode() == ControlMode.Position) {
			/* ppend more signals to print when in speed mode. */
			_sb.append("\terr:");
			_sb.append(Liftmotor.getClosedLoopError(0));
			_sb.append("u");	// Native Units

			_sb.append("\ttrg:");
			_sb.append(desiredheight);
      _sb.append("u");	/// Native Units
      
    /**
		 * Print every ten loops, printing too much too fast is generally bad
		 * for performance.
		 */
		if (++_loops >= 10) {
			_loops = 0;
			if (_printresults) System.out.println(_sb.toString());
		}

		/* Reset built string for next loop */
		_sb.setLength(0);
		}
	
  } 

  public double liftPosition()
  {
   // System.out.println( "Tal:" + Liftmotor.getSelectedSensorPosition(0)+","+Liftmotor.getSelectedSensorVelocity(0));
   // System.out.println();
    return(Liftmotor.getSelectedSensorPosition(0));
  }

  public void resetLiftPosition(){
    LiftSystem.Liftmotor.setSelectedSensorPosition(0);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftWithJoyStick());
  }

  public void report(){
    SmartDashboard.putNumber("Velocity:", Liftmotor.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Position:", liftPosition());
    SmartDashboard.putNumber("Out %", Liftmotor.getMotorOutputPercent());
    

  }
}
