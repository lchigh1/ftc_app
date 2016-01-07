package org.usfirst.ftc.exampleteam.yourcodehere;

import com.qualcomm.robotcore.hardware.*;
import org.swerverobotics.library.*;
import org.swerverobotics.library.interfaces.*;

/**

 */
@TeleOp(name="Autororamp")
public class AutoToRamp extends SynchronousOpMode
    {
    /* Declare here any fields you might find useful. */
    // DcMotor motorLeft = null;
    // DcMotor motorRight = null;

    @Override public void main() throws InterruptedException
        {
        /* Initialize our hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names you assigned during the robot configuration
         * step you did in the FTC Robot Controller app on the phone.
         */
        // this.motorLeft = this.hardwareMap.dcMotor.get("motorLeft");
        // this.motorRight = this.hardwareMap.dcMotor.get("motorRight");

        // Wait for the game to start
        waitForStart();

        // Go go gadget robot!
        while (opModeIsActive())
            {
            if (updateGamepads())
                {
                // The game pad state has changed. Do something with that!
                }

            telemetry.update();
            idle();
            }
        }
    }//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorController;
//import com.qualcomm.robotcore.hardware.Servo;

//import com.qualcomm.robotcore.hardware.DcMotor;

        //import org.swerverobotics.library.SynchronousOpMode;
       // import org.swerverobotics.library.interfaces.Autonomous;
//        import org.swerverobotics.library.internal.ThreadSafeAnalogInput;

/**
 * Linear Autonomous program made following example "Basic Autonomous" video by SwerveRobotics
 * Uses power/time based motor movement to achieve desired distances and turns.
 * Robot configuration includes: tank drive motors, 1 servo for arm positioning
 *
 * You can add your configuration and replicate desired code.
 */
@Autonomous(name="Auto to parking+") //name to appear in Driver Station OpMode selection
//@Disabled  //if you un-comment this, it will keep from showing on DriverStation
class AutoToramp extends SynchronousOpMode {
    /* Declare variable for all components to be used. Note initial values set to null. */

    //Declare Motors
    DcMotor motorLeft = null;
    DcMotor motorRight = null;

    // Declare servos
    //Servo armServo = null;

    @Override
    public void main() throws InterruptedException {
        // Initialize motors to match DS configuration names
        motorLeft = hardwareMap.dcMotor.get("motorL");
        motorRight = hardwareMap.dcMotor.get("motorR");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // Initialize servos
        // armServo = hardwareMap.servo.get("servoHandL");

        // Wait for the game to start
        waitForStart();

        /************************
         * Autonomous Code Below://
         *************************/
        DriveForwardTime(DRIVE_POWER, 3050);
        TurnRight(DRIVE_POWER, 1100);
        // StopDrivingTime(1000);

        DriveForwardTime(DRIVE_POWER, 4000);
        // TurnRight(DRIVE_POWER, 3000);
        StopDrivingTime(2000);

    }//Main

    /**
     * Below: Basic Drive Methods used in Autonomous code...
     **/
    //set Drive Power variable
    double DRIVE_POWER = 9.0;

    public void DriveForward(double power) {
        motorLeft.setPower(power);
        motorRight.setPower(power);
    }

    public void DriveForwardTime(double power, long time) throws InterruptedException {
        DriveForward(power);
        Thread.sleep(time);
    }

    public void StopDriving() {
        DriveForward(0);
    }

    public void StopDrivingTime(long time) throws InterruptedException {
        DriveForwardTime(0, time);
    }

    public void TurnLeft(double power, long time) throws InterruptedException {
        motorLeft.setPower(-power);
        motorRight.setPower(power);
        Thread.sleep(time);
    }

    public void TurnRight(double power, long time) throws InterruptedException {
        TurnLeft(-power, time);
    }
}