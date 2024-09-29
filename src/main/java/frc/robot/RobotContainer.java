// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Commands.MotorCommands;
import frc.robot.Constants.ButtonBindingConstants;
import frc.robot.Constants.JoystickPortConstants;
import frc.robot.Subsystems.MotorSubsystem;

public class RobotContainer {
  private final MotorSubsystem m_MotorSubsystem = new MotorSubsystem();
  private final Joystick operatorJoystick = new Joystick(JoystickPortConstants.kOperatorJoystick);
  public RobotContainer() {
    configureBindings();
    
  }

  private void configureBindings() {
    new JoystickButton(operatorJoystick, ButtonBindingConstants.kSpoolMotorButton).onTrue(new MotorCommands(m_MotorSubsystem).spoolMotorCommand());
    new JoystickButton(operatorJoystick, ButtonBindingConstants.kStopMotorButton).onTrue(new MotorCommands(m_MotorSubsystem).ceaseMotorCommand());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
