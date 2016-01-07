package org.usfirst.ftc.exampleteam.yourcodehere;

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

public class TeleOpMode extends SynchronousOpMode //Special note: this class name must match file name
{
    // Declare variable for all components to be used. Note initial values set to null. */
    DcMotor motorL = null;
    DcMotor motorR = null;
    DcMotor motorArm = null;
    Servo servo = null;


    //default arm position variable
    double OPEN = 0.2;
    double CLOSE = 0.8;

    @Override public void main() throws InterruptedException
    {
        /* Initialize our hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names you assigned during the robot configuration
         * step you did in the FTC Robot Controller app on the phone.
         */
        this.motorL = this.hardwareMap.dcMotor.get("motorL");
        this.motorR = this.hardwareMap.dcMotor.get("motorR");
        //legacy motor
        this.motorArm = this.hardwareMap.dcMotor.get("motorArm");


        //set motor channel to run without encoders
        motorL.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorR.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorArm.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        //reverse Left motor
        motorL.setDirection(DcMotor.Direction.REVERSE);

        this.servo = this.hardwareMap.servo.get("servo");
       // this.servoArmR = this.hardwareMap.servo.get("ArmR");
        //set initial servos to open
       servo.setPosition(OPEN);
       // servoArmR.setPosition(OPEN);

        // Wait for the game to start
        waitForStart();

        // telOp Code below...
        while (opModeIsActive())
        {
            if (updateGamepads())
            {
                // tank drive
                motorL.setPower(gamepad1.left_stick_y);
                motorR.setPower(gamepad1.right_stick_y);

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
                    servo.setPosition(CLOSE);
                    //servoArmR.setPosition(CLOSE);
                }
                else if (gamepad1.b)
                {
                    servo.setPosition(OPEN);
                    //servoArmR.setPosition(OPEN);
                }

            }//if updateGamepads

            telemetry.update();
            idle();
        }//while opModeActive

    }//main
}//MyFirstOpMode