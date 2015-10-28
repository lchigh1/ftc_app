package org.usfirst.ftc.exampleteam.yourcodehere;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.swerverobotics.library.SynchronousOpMode;
import org.swerverobotics.library.interfaces.Autonomous;
import org.swerverobotics.library.interfaces.TeleOp;

/**
 * Robot starts on blue side, goes to beacon,
 * presses beacon button, and scores climber
 */
@Autonomous(name="BlueBeaconClimber")
public class BlueBeaconClimber extends SynchronousOpMode
{
    // Declare motors and servos
    DcMotor motorLeft = null;
    DcMotor motorRight = null;
    DcMotor motorCollector = null;
    DcMotor motorScorer = null;

    @Override public void main() throws InterruptedException
    {
        // Initialize motors and servos
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorCollector = hardwareMap.dcMotor.get("motorCollector");
        motorScorer = hardwareMap.dcMotor.get("motorScorer");

        // Set motor channel modes
        motorLeft.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorRight.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorCollector.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorScorer.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

        // Reverse left motors so we don't spin in a circle
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start
        waitForStart();

        /*
         * drive to beacon
         * turn to face beacon
         * follow line to wall
         * determine beacon color
         * press correct button
         * dump climbers
         */
    }

    public void DriveForward(double power)
    {
        motorLeft.setPower(power);
        motorRight.setPower(power);
    }

    public void DriveForwardDistance(double power, int distance)
    {
        DriveForward(power);
        while(motorLeft.getCurrentPosition() < distance)
        {
            // Wait until distance is reached
        }
        StopDriving();
    }

    public void TurnLeft(double power)
    {
        motorLeft.setPower(-power);
        motorRight.setPower(power);
    }

    public void TurnRight(double power)
    {
        TurnLeft(-power);
    }

    public void StopDriving()
    {
        DriveForward(0);
    }

    public void TurnLeftDistance(double power, int distance)
    {
        TurnLeft(power);
        while(motorLeft.getCurrentPosition() < distance)
        {
            // Wait until distance is reached
        }
        StopDriving();
    }

    public void TurnRightDistance(double power, int distance)
    {
        TurnLeftDistance(-power, distance);
    }
}
