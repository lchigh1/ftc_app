package org.swerverobotics.library.examples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.swerverobotics.library.SynchronousOpMode;
import org.swerverobotics.library.interfaces.Autonomous;

/**
 * This is a very simple placeholder. It doesn't actually *do* anything. Rather, it merely 
 * shows you the basic skeletal structure of what a SynchronousOpMode should look like. 
 */
@Autonomous(name="SynchAuto1")
public class SynchAuto1 extends SynchronousOpMode
{
    DcMotor motorLeft = null;
    DcMotor motorRight = null;
    @Override protected void main() throws InterruptedException
    {
        // Initialize your program state, your robot, and the telemetry dashboard (if you want it).
        //    (not shown)
        this.motorLeft = this.hardwareMap.dcMotor.get("motorLeft");
        this.motorRight = this.hardwareMap.dcMotor.get("motorRight");

        // Configure the knobs of the hardware according to how you've wired your
        // robot. Here, we assume that there are no encoders connected to the motors,
        // so we inform the motor objects of that fact.
        this.motorLeft.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        this.motorRight.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        // One of the two motors (here, the left) should be set to reversed direction
        // so that it can take the same power level values as the other motor.
        this.motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to begin
        this.waitForStart();

        // Loop until the game is finished
        while (this.opModeIsActive())
        {
            motorLeft.setPower(1.0);
            motorRight.setPower(1.0);
            Thread.sleep(500);
            motorLeft.setPower(0.0);
            motorRight.setPower(0.0);
            Thread.sleep(500);
            motorLeft.setPower(-1.0);
            motorRight.setPower(-1.0);
            Thread.sleep(500);
            motorLeft.setPower(0.0);
            motorRight.setPower(0.0);
            Thread.sleep(500);

            // Emit the latest telemetry and wait, letting other things run
            this.telemetry.update();
            this.idle();
        }
    }
}
