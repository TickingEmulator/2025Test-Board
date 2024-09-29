package frc.robot.Subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class MotorSubsystem extends SubsystemBase {
    private final CANSparkMax frontRightMotor = new CANSparkMax(MotorConstants.kFrontRightMotor, MotorType.kBrushless);
    private final CANSparkMax frontLeftMotor = new CANSparkMax(MotorConstants.kFrontLeftMotor, MotorType.kBrushless);
    private final SparkPIDController frontRightController = frontRightMotor.getPIDController();
    private final DutyCycle absoluteEncoder = new DutyCycle(new DigitalInput(MotorConstants.kAbsoluteEncoderPort)); // Directly connected into the RIO
    private final RelativeEncoder relativeEncoder = frontRightMotor.getEncoder();
    private final double homePosition = MotorConstants.kHomePosition;
    public enum MotorState {
        kRunning,
        kStopped,
    }
    private MotorState defaultState = MotorState.kStopped;
    public MotorSubsystem(){
        frontRightController.setP(MotorConstants.kRotationP);
        frontRightController.setI(MotorConstants.kRotationI);
        frontRightController.setD(MotorConstants.kRotationD);
        relativeEncoder.setPosition(0);
        frontRightMotor.restoreFactoryDefaults();
        frontLeftMotor.restoreFactoryDefaults();
        relativeEncoder.setPositionConversionFactor(1);
        frontRightMotor.setSmartCurrentLimit(40);
        frontLeftMotor.setSmartCurrentLimit(40);
        frontRightMotor.setIdleMode(IdleMode.kBrake);
        frontLeftMotor.setIdleMode(IdleMode.kBrake);
        frontLeftMotor.follow(frontRightMotor);
        frontLeftMotor.setInverted(false);
        frontRightMotor.setInverted(false);
        frontLeftMotor.burnFlash();
        frontRightMotor.burnFlash();
    }
    public void setMotorState(MotorState type){
        defaultState = type;
        frontRightMotor.set(switch(defaultState){
            case kRunning -> MotorConstants.kRunningSpeed;
            case kStopped -> 0;
        });
    }
}
