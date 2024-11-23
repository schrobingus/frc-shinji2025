package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SwerveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final int translationAxis = XboxController.Axis.kLeftY.value;
  private final int strafeAxis = XboxController.Axis.kLeftX.value;
  private final int rotationAxis = XboxController.Axis.kRightX.value;

  private final CommandXboxController pilot = new CommandXboxController(0);
  private final CommandXboxController copilot = new CommandXboxController(1);

  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    Command swerveCommand = swerveSubsystem.driveCommand(
      () -> Math.abs(pilot.getRawAxis(translationAxis)) > 0.06
        ? pilot.getRawAxis(translationAxis) * -1
        : 0, 
      () -> Math.abs(pilot.getRawAxis(strafeAxis)) > 0.06
        ? pilot.getRawAxis(strafeAxis) * -1
        : 0, 
      () -> Math.abs(pilot.getRawAxis(rotationAxis)) > 0.06
        ? pilot.getRawAxis(rotationAxis) * -0.9
        : 0);
    swerveSubsystem.setDefaultCommand(swerveCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
