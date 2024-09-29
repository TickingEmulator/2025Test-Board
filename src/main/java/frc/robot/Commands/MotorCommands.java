package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Subsystems.MotorSubsystem;
import frc.robot.Subsystems.MotorSubsystem.MotorState;

public class MotorCommands {
    private MotorSubsystem m_MotorSubsystem;
    public MotorCommands(MotorSubsystem m_MotorSubsystem){
        this.m_MotorSubsystem = m_MotorSubsystem;
    }
    public Command spoolMotorCommand(){
        return Commands.runOnce(() -> m_MotorSubsystem.setMotorState(MotorState.kRunning));
    }
    public Command ceaseMotorCommand(){
        return Commands.runOnce(() -> m_MotorSubsystem.setMotorState(MotorState.kStopped));
    }
    
}
