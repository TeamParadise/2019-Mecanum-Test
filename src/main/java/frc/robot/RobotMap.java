/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class RobotMap {
    public static final int kJoystickChannel = 0;
    public static final int kJoystickChannel1 = 1;
    //Joystick buttons
    public static final int kJBallShoot = 12; //not used, but refernce in BallShoot
    // public static final int kBallpickup  = 2;
    // public static final int kDiscPlace = 3;
    // public static final int kDiscGet = 4;
    public static final int kJGrabberInOut = 1;
    public static final int kJGrabberUpDown = 2;
    public static final int kJLiftUp = 5;
    public static final int kJLiftDown = 3;
    
    public static final int kJBallLevel1 = 7;
    public static final int kJBallLevel2 = 9;
    public static final int kJBallLevel3 = 11;
    public static final int kJDiscLevel1 = 8;
    public static final int kJDiscLevel2 = 10;
    public static final int kJDiscLevel3 = 12;
    
    //public static final int kGrabIdle = 9;
 
    //public static final int kJConfirmManualLift = 5;
    //public static final int kResetLiftPosition = 12;
    //lift can manually be moved by pressing 6 and moving the throttle
 
    //DIO
    public static final int kRightPingChannel = 0;
    public static final int kRightEchoChannel = 1;
    public static final int kLeftPingChannel = 2;
    public static final int kLeftEchoChannel = 3;
    
    //MotorControllers
    //victors
    public static final int kFrontLeftChannel = 0;//0
    public static final int kRearLeftChannel = 1;//1
    public static final int kFrontRightChannel = 3;//3
    public static final int kRearRightChannel = 2;//2

    //talons
    public static final int kLiftChannel = 0;
    public static final int kBallChannel = 1;

    //lift info
    public static final int kLiftBottom = 0;

    public static final int kLiftDiscLevel1 = -1000;
    public static final int kLiftBallLevel1 = -2000;

    public static final int kLiftDiscLevel2 = -5000;
    public static final int kLiftBallLevel2 = -6000;

    public static final int kLiftDiscLevel3 = -9000;
    public static final int kLiftBallLevel3 = -10000;

    public static final int kLiftTop = -10500;

    //Pneumatics
    public static final int kPcm0 = 0;
    public static final int kGrabExtendChannel = 1;
    public static final int kGrabRetractChannel = 0;
    public static final int kGrabUpwards = 5;
    public static final int kGrabDownwards = 4;
    public static final int kBallExtend = 2;
    public static final int kBallRetract = 3;
    public static final int kBrakeOpen = 6;
    public static final int kBrakeClose = 7;
} 
