package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

import org.swerverobotics.library.SynchronousOpMode;

/**
 * This file was made for the BasicTeleOp tutorial video
 */
public class TutorialTeleOp extends SynchronousOpMode
{
    // Declare motors
    DcMotor leftMotor = null;
    DcMotor rightMotor = null;

    // Declare servos
    Servo armServo = null;

    // Servo positions
    double SERVO_MIN = 0.2;
    double SERVO_MAX = 0.8;

    @Override protected void main() throws InterruptedException
    {
        // Initialize motors
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");

        //Initialize servos
        armServo = hardwareMap.servo.get("armServo");

        // Set motor channels
        leftMotor.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rightMotor.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

        // Reverse one side of drive motors
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to begin
        this.waitForStart();
        
        // Loop until the game is finished
        while (this.opModeIsActive())
        {
            if (this.newGamePadInputAvailable())
            {
                // Tank drive
                leftMotor.setPower(gamepad1.left_stick_y());
                rightMotor.setPower(gamepad1.right_stick_y());

                // Move arm servo
                if(gamepad2.a())
                {
                    armServo.setPosition(SERVO_MIN);
                }
                else if(gamepad2.b())
                {
                    armServo.setPosition(SERVO_MAX);
                }
            }
            
            // Emit the latest telemetry and wait, letting other things run
            this.telemetry.update();
            this.idle();
        }
    }
}
