package frc.robot.subsystems;

import java.io.File;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;

import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;

public class SwerveSubsystem extends SubsystemBase {
    public final SwerveDrive swerveDrive;

    double maximumSpeed = Units.feetToMeters(4.5);
    File swerveJsonDirectory = new File(
        Filesystem.getDeployDirectory(), "swerve");
    
    public SwerveSubsystem() {
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
        // SwerveDriveTelemetry.verbosity = TelemetryVerbosity.NONE;

        try {
            swerveDrive = new SwerveParser(swerveJsonDirectory)
                .createSwerveDrive(maximumSpeed);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: implement translative drive

    public Command driveCommand(
        DoubleSupplier translationX, 
        DoubleSupplier translationY,
        DoubleSupplier angularRotationX
    ) {
        return run(() -> {
            swerveDrive.drive(
                new Translation2d(
                    translationX.getAsDouble() * swerveDrive.getMaximumVelocity(),
                    translationY.getAsDouble() * swerveDrive.getMaximumVelocity()
                ),
                angularRotationX.getAsDouble() * swerveDrive.getMaximumAngularVelocity(),
                true, false
            );
        });
    }
}
