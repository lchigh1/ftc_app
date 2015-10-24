package org.usfirst.ftc.exampleteam.yourcodehere;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import org.swerverobotics.library.SynchronousOpMode;
import org.swerverobotics.library.interfaces.IFunc;
import org.swerverobotics.library.interfaces.TeleOp;

/**
 * Main TeleOp file for 8923 bot
 */
@TeleOp(name="8923 Main TeleOp")
public class MainTeleOp8923 extends SynchronousOpMode
{
    // Declare motors and servos
    DcMotor motorLeft = null;
    DcMotor motorRight = null;
    DcMotor motorCollector = null;
    DcMotor motorScorer = null;

    // Variable declarations
    double POWER_FULL = 1.0;
    double POWER_STOP = 0.0;
    double POWER_SCORER = 0.4;

    @Override protected void main() throws InterruptedException
    {
        // Initialize motors and servos
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorCollector = hardwareMap.dcMotor.get("motorCollector");
        motorScorer = hardwareMap.dcMotor.get("motorScorer");

        // Set motor channel modes
        motorLeft.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorRight.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorCollector.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorScorer.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

        // Reverse left motors so we don't spin in a circle
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // Configure dashboard
        telemetry.addLine
                (
                        this.telemetry.item("Left:", new IFunc<Object>() {
                            @Override
                            public Object value() {
                                return motorLeft.getPower();
                            }
                        }),
                        this.telemetry.item("Right: ", new IFunc<Object>() {
                            @Override
                            public Object value() {
                                return motorLeft.getPower();
                            }
                        })
                );

        // Wait for the game to begin
        this.waitForStart();

        // Loop until the game is finished
        while(this.opModeIsActive())
        {
            if(this.updateGamepads())
            {
                // Main drive code
                motorLeft.setPower(gamepad1.left_stick_y);
                motorRight.setPower(gamepad1.right_stick_y);

                // Move collector based on triggers
                if(gamepad2.right_trigger > 0)
                    motorCollector.setPower(POWER_FULL);
                else if(gamepad2.left_trigger > 0)
                    motorCollector.setPower(-POWER_FULL);
                else
                    motorCollector.setPower(POWER_STOP);

                // Move scorer based on D-pad
                if(gamepad2.dpad_left)
                    motorScorer.setPower(-POWER_SCORER);
                else if(gamepad2.dpad_right)
                    motorScorer.setPower(POWER_SCORER);
                else
                    motorScorer.setPower(POWER_STOP);
            }

            // Emit the latest telemetry and wait, letting other things run
            this.telemetry.update();
            this.idle();
        }
    }
}
