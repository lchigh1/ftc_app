package org.swerverobotics.library.examples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.swerverobotics.library.SynchronousOpMode;
import org.swerverobotics.library.interfaces.EulerAngles;
import org.swerverobotics.library.interfaces.IBNO055IMU;
import org.swerverobotics.library.interfaces.IFunc;
import org.swerverobotics.library.interfaces.Position;
import org.swerverobotics.library.interfaces.TeleOp;

/**
 * An example of a synchronous opmode that implements a simple drive-a-bot. 
 */
@TeleOp(name="DrivabotSimple", group="Swerve Examples")
public class SynchDrivabotDemoSimple extends SynchronousOpMode {
    // All hardware variables can only be initialized inside the main() function,
    // not here at their member variable declarations.
    DcMotor Motor1 = null;
    DcMotor Motor2 = null;
    DcMotor Motor3 = null;
    DcMotor Motor4 = null;
    DcMotor Motor5 = null;
    DcMotor Motor6 = null;


    @Override
    protected void main() throws InterruptedException {
        // Initialize our hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names you assigned during the robot configuration
        // step you did in the FTC Robot Controller app on the phone.
        this.Motor1 = this.hardwareMap.dcMotor.get("Motor1");
        this.Motor2 = this.hardwareMap.dcMotor.get("Motor2");
        this.Motor3 = this.hardwareMap.dcMotor.get("Motor3");
        this.Motor4 = this.hardwareMap.dcMotor.get("Motor4");
        this.Motor5 = this.hardwareMap.dcMotor.get("Motor5");
        this.Motor6 = this.hardwareMap.dcMotor.get("Motor6");

        // Configure the knobs of the hardware according to how you've wired your
        // robot. Here, we assume that there are no encoders connected to the motors,
        // so we inform the motor objects of that fact.
        this.Motor1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        this.Motor2.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        this.Motor3.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        this.Motor4.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        this.Motor5.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        this.Motor6.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

        // One of the two motors (here, the left) should be set to reversed direction
        // so that it can take the same power level values as the other motor.
        this.Motor5.setDirection(DcMotor.Direction.REVERSE);
        this.Motor6.setDirection(DcMotor.Direction.REVERSE);
        this.Motor1.setDirection(DcMotor.Direction.REVERSE);

        // Configure the dashboard however we want it
        //this.configureDashboard();

        // Wait until we've been given the ok to go
        this.waitForStart();

        while (!this.opModeIsActive()) {
            //do nothing until the opmode is active
        }

        // angles     = imu.getAngularOrientation();

        telemetry.addData("here", "starting");
        telemetry.update();
        // Enter a loop processing all the input we receive
        while (this.opModeIsActive()) {

            Motor1.setPower(.8);
            Motor2.setPower(.8);
            Motor3.setPower(.8);
            Motor4.setPower(.8);
            Motor5.setPower(.8);
            Motor6.setPower(.8);

            telemetry.addData("there", "done");
            telemetry.update();
            while (this.opModeIsActive()) {
                this.idle();
            }

        }
    }
}