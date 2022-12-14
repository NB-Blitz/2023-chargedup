package org.team5148.chargedup;

import edu.wpi.first.wpilibj.TimedRobot;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

	//defining motor controlers 
	CANSparkMax frontLeft = new CANSparkMax(1, MotorType.kBrushless);
	CANSparkMax frontRight = new CANSparkMax(3, MotorType.kBrushless);
	CANSparkMax backLeft = new CANSparkMax(2, MotorType.kBrushless);
	CANSparkMax backRight = new CANSparkMax(4, MotorType.kBrushless);

	//xbox controller
	XboxController driveController = new XboxController(0);

	//define a drive
	MecanumDrive MDrive = new MecanumDrive(frontLeft , backLeft , frontRight , backRight);
	//setup
	NetworkTable table;
	@Override
	public void robotInit() {
		table = NetworkTableInstance.getDefault().getTable("Microsoft_LifeCam_HD-3000");
	  }
	  

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
		MDrive.driveCartesian(ySpeed, xSpeed, zRotation);


		//get network table value 
		SmartDashboard.putString("targetarea", table.getEntry("targetarea").getValue().toString());

	}
}


