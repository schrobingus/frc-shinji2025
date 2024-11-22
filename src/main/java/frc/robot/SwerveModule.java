package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class SwerveModule {
    private final int driveMotorID;
    private final float driveMotorReversed;
    private final int turnMotorID;
    private final float turnMotorReversed;
    private final int turnEncoderID;
    private boolean driveMotorRunning = false;  // NOTE: temporary
    private boolean turnMotorRunning = false;   // NOTE: temporary

    private final CANSparkMax driveMotor;
    private final CANSparkMax turnMotor;
    private final RelativeEncoder driveMotorEncoder;
    private final AnalogEncoder turnEncoder;

    public SwerveModule(
        int driveMotorID,
        float driveMotorReversed,
        int turnMotorID,
        float turnMotorReversed,
        int turnEncoderID
    ) {
        this.driveMotorID = driveMotorID;
        this.driveMotorReversed = driveMotorReversed;
        this.turnMotorID = turnMotorID;
        this.turnMotorReversed = turnMotorReversed;
        this.turnEncoderID = turnEncoderID;

        this.driveMotor = new CANSparkMax(driveMotorID, MotorType.kBrushless);
        this.turnMotor = new CANSparkMax(turnMotorID, MotorType.kBrushless);
        this.driveMotorEncoder = driveMotor.getEncoder();
        this.turnEncoder = new AnalogEncoder(turnEncoderID);

        pushToShuffleboard();
    }
    
    // NOTE: temporary
    public void toggleDriveMotor() {
        driveMotorRunning = !driveMotorRunning;
        if (driveMotorRunning) {
            driveMotor.setVoltage(3*driveMotorReversed);   // outta 12
        } else {
            driveMotor.setVoltage(0);
        }
    }

    // NOTE: temporary
    public void toggleTurnMotor() {
        turnMotorRunning = !turnMotorRunning;
        if (turnMotorRunning) {
            turnMotor.setVoltage(3*turnMotorReversed);    // outta 12
        } else {
            turnMotor.setVoltage(0);
        }
    }

    public double getDriveMotorEncoderPosition() {
        final double position = driveMotorEncoder.getPosition();
        return position;
    }

    public double getTurnEncoderRawAngle() {
        final double rawAngle = turnEncoder.getAbsolutePosition();
        return rawAngle;
    }

    public void pushToShuffleboard() {
        ShuffleboardTab sbDebug = Shuffleboard.getTab("Debug");

        sbDebug.add("Toggle DM "+driveMotorID, new InstantCommand(() -> toggleDriveMotor()));
        sbDebug.add("Toggle TM "+turnMotorID, new InstantCommand(() -> toggleTurnMotor()));
        sbDebug.addNumber("Position of DME "+driveMotorID, () -> getDriveMotorEncoderPosition());
        sbDebug.addNumber("Raw Angle of TE "+turnEncoderID, () -> getTurnEncoderRawAngle()*360); // in degrees
    }
}
