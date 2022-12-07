package org.team5148.chargedup;

import edu.wpi.first.wpilibj.TimedRobot;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.XboxController;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SerialPort;


import edu.wpi.first.wpilibj.drive.MecanumDrive;


public class Robot extends TimedRobot {

	//defining motor controlers 
	CANSparkMax frontLeft = new CANSparkMax(1, MotorType.kBrushless);
	CANSparkMax frontRight = new CANSparkMax(3, MotorType.kBrushless);
	CANSparkMax backLeft = new CANSparkMax(2, MotorType.kBrushless);
	CANSparkMax backRight = new CANSparkMax(4, MotorType.kBrushless);

	//NAVX sensor
	AHRS ahrs = new AHRS(SerialPort.Port.kMXP); 

	double accelX;
	double accelY;
	double accelZ;
	double angle;

	static double deadzone = 0.10;

	//xbox controller
	XboxController driveController = new XboxController(0);

	//define a drive
	MecanumDrive MDrive = new MecanumDrive(frontLeft , backLeft , frontRight , backRight);

	@Override
	public void autonomousInit() {

	}

	@Override
	public void autonomousPeriodic() {

	}

	@Override
	public void teleopInit() {
		frontRight.setInverted(true);
		backRight.setInverted(true);
		
	}

	@Override
	public void teleopPeriodic() {
		//use controller input to set drive system
		Double ySpeed = driveController.getLeftY();
		Double xSpeed = driveController.getLeftX();
		Double zRotation = driveController.getRightX(); 
		// make conditionals to set deadzones

		if(Math.abs(xSpeed) <= deadzone){
			xSpeed = 0.0;
		}

		if(Math.abs(ySpeed) <= deadzone){
			ySpeed = 0.0;
		}

		if(Math.abs(zRotation) <= deadzone){
			zRotation = 0.0;
		}
		 MDrive.driveCartesian(ySpeed, xSpeed, zRotation); //but add the conditional variables
	
		// sensor code example
		accelX = ahrs.getWorldLinearAccelX();
		accelY = ahrs.getWorldLinearAccelY();
		accelZ = ahrs.getWorldLinearAccelZ();
		angle = ahrs.getAngle();
		SmartDashboard.putNumber("accelX", accelX);
		SmartDashboard.putNumber("angle", angle); 
	}
}


