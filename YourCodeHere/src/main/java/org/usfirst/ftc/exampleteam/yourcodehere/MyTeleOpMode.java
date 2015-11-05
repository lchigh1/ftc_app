package org.usfirst.ftc.exampleteam.yourcodehere;

import android.provider.OpenableColumns;

import com.qualcomm.robotcore.hardware.*;
import org.swerverobotics.library.*;
import org.swerverobotics.library.interfaces.*;

/**
 * A skeletal example of a first OpMode. Go ahead and change this code
 * to suit your needs, or create sibling OpModes adjacent to this one in the same
 * Java package.
 */
@TeleOp(name="MyBasicTeleOp") //name to appear in Driver Station OpMode selection
//@Disabled  //if you un-comment this, it will keep from showing on DriverStation

public class MyTeleOpMode extends SynchronousOpMode //Special note: this class name must match file name
{
    // Declare variable for all components to be used. Note initial values set to null. */
    DcMotor motorLeft = null;
    DcMotor motorRight = null;
    DcMotor motorArm = null;

    Servo servoArmR = null;
    Servo servoArmL = null;
    //default arm position variable
    double OPEN = 0.2;
    double CLOSE = 0.8;

    @Override public void main() throws InterruptedException
    {
        /* Initialize our hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names you assigned during the robot configuration
         * step you did in the FTC Robot Controller app on the phone.
         */
        this.motorLeft = this.hardwareMap.dcMotor.get("motorL");
        this.motorRight = this.hardwareMap.dcMotor.get("motorR");
        //legacy motor
        this.motorArm = this.hardwareMap.dcMotor.get("motorArm");


        //set motor channel to run without encoders
        motorLeft.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorRight.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorArm.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        //reverse Left motor
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        this.servoArmL = this.hardwareMap.servo.get("ArmL");
        this.servoArmR = this.hardwareMap.servo.get("ArmR");
        //set initial servos to open
        servoArmL.setPosition(OPEN);
        servoArmR.setPosition(OPEN);

        // Wait for the game to start
        waitForStart();

        // telOp Code below...
        while (opModeIsActive())
        {
            if (updateGamepads())
            {
                // tank drive
                motorLeft.setPower(gamepad1.left_stick_y);
                motorRight.setPower(gamepad1.right_stick_y);

                // Arm motor control

                if (gamepad1.right_bumper)                       //if Left bumper+trigger, then Negative arm motor power
                {
                    motorArm.setPower(-gamepad1.right_trigger);
                }
                else                                            //else positive arm motor power
                {
                    motorArm.setPower(gamepad1.right_trigger);
                }


                //servo commands
                if(gamepad1.a)
                {
                    servoArmL.setPosition(CLOSE);
                    servoArmR.setPosition(CLOSE);
                }
                else if (gamepad1.b)
                {
                    servoArmL.setPosition(OPEN);
                    servoArmR.setPosition(OPEN);
                }

            }//if updateGamepads

            telemetry.update();
            idle();
        }//while opModeActive

    }//main
}//MyFirstOpMode