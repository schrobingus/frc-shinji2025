package frc.robot.subsystems;

import static frc.robot.Constants.*;
import frc.robot.SwerveModule;

public class SwerveSubsystem {
    private final SwerveModule moduleFL;
    private final SwerveModule moduleFR;
    private final SwerveModule moduleBL;
    private final SwerveModule moduleBR;

    public SwerveSubsystem() {
        moduleFL = new SwerveModule(
            FL_DRMO,
            FL_DRMO_REVERSED,
            FL_TRMO,
            FL_TRMO_REVERSED,
            FL_TREN
        );

        moduleFR = new SwerveModule(
            FR_DRMO,
            FR_DRMO_REVERSED,
            FR_TRMO,
            FR_TRMO_REVERSED,
            FR_TREN
        );

        moduleBL = new SwerveModule(
            BL_DRMO,
            BL_DRMO_REVERSED,
            BL_TRMO,
            BL_TRMO_REVERSED,
            BL_TREN
        );
        
        moduleBR = new SwerveModule(
            BR_DRMO,
            BR_DRMO_REVERSED,
            BR_TRMO,
            BR_TRMO_REVERSED,
            BR_TREN
        );
    }
}
