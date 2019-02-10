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
    //Joystick buttons
    public static final int kGrabExtend = 7;
    public static final int kGrabRetract = 8;
    public static final int kGrabIdle = 9;

    //DIO
    public static final int kRightPingChannel = 0;
    public static final int kRightEchoChannel = 1;
    public static final int kLeftPingChannel = 2;
    public static final int kLeftEchoChannel = 3;
    
    //MotorControllers
    
    public static final int kFrontLeftChannel = 0;//0
    public static final int kRearLeftChannel = 1;//1
    public static final int kFrontRightChannel = 3;//3
    public static final int kRearRightChannel = 2;//2
    public static final int kLiftChannel = 4;
}
