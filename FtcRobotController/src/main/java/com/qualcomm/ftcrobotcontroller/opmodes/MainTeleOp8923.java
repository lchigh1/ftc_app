package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import org.swerverobotics.library.SynchronousOpMode;
import org.swerverobotics.library.interfaces.IFunc;

/**
 * Main TeleOp file for 8923 bot
 */
public class MainTeleOp8923 extends SynchronousOpMode
{
    // Declare motors and servos
    DcMotor motorLeft = null;
    DcMotor motorRight = null;
    DcMotor motorCollector = null;

    @Override protected void main() throws InterruptedException
    {
        // Initialize motors and servos
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorCollector = hardwareMap.dcMotor.get("motorCollector");

        // Set motor channel modes
        motorLeft.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorRight.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorCollector.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

        // Reverse left motors so we don't spin in a circle
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // Configure dashboard
        telemetry.dashboard.line
            (
                this.telemetry.dashboard.item("Left:", new IFunc<Object>()
                {
                    @Override public Object value()
                    {
                        return motorLeft.getPower();
                    }
                }),
                this.telemetry.dashboard.item("Right: ", new IFunc<Object>()
                {
                    @Override public Object value()
                    {
                        return motorLeft.getPower();
                    }
                })
            );

        // Wait for the game to begin
        this.waitForStart();
        
        // Loop until the game is finished
        while(this.opModeIsActive())
        {
            if(this.newGamePadInputAvailable())
            {
                motorLeft.setPower(gamepad1.left_stick_y());
                motorRight.setPower(gamepad1.right_stick_y());

                motorCollector.setPower(gamepad2.right_trigger());
            }
            
            // Emit the latest telemetry and wait, letting other things run
            this.telemetry.update();
            this.idle();
        }
    }
}
