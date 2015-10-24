package org.swerverobotics.library.examples;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.LED;

import org.swerverobotics.library.SynchronousOpMode;
import org.swerverobotics.library.interfaces.Disabled;
import org.swerverobotics.library.interfaces.IFunc;
import org.swerverobotics.library.interfaces.TeleOp;

/**
 * SynchLEDDemo is a short demo on using the Adafruit Color Sensor module.
 * It assumes that you have an Adafruit Color Sensor connected to a Core Device Interface
 * called "ada" and the led of the device is connected to a digital pin called "adaLED"
 */
@TeleOp(name="AdaColorSensor", group="Swerve Examples")
public class SynchAdaColorSensorDemo extends SynchronousOpMode {

    ColorSensor sensorRGB = null;
    LED led = null;
    boolean led_state = false;

    @Override public void main() throws InterruptedException {

        // get a reference to our ColorSensor object.
        sensorRGB = hardwareMap.colorSensor.get("ada");

        // get a regerence to the LED on the color sensor board
        led = hardwareMap.led.get("adaLED");


        // Set up our dashboard computations
        composeDashboard();

        // Wait until we're told to go
        waitForStart();
               
        // Loop and update the dashboard
        while (this.opModeIsActive()) {
            if (this.updateGamepads()) {
                if (this.gamepad1.a) {
                    led_state = true;
                    led.enable(led_state);
                    sensorRGB.enableLed(led_state);
                } else if (this.gamepad1.b) {
                    led_state = false;
                    led.enable(led_state);
                    sensorRGB.enableLed(led_state);
                }
            }
            telemetry.update();

            idle();
        }
    }
    
    void composeDashboard() {
        telemetry.addLine(
                telemetry.item("loop count: ", new IFunc<Object>() {
                    @Override
                    public Object value() {
                        return getLoopCount();
                    }
                }));
        telemetry.addLine(
                telemetry.item("controls: ", new IFunc<Object>() {
                    @Override
                    public Object value() {
                        return "a:on, b:off";
                    }
                }));
        telemetry.addLine(
                telemetry.item("led state: ", new IFunc<Object>() {
                    @Override
                    public Object value() {
                        //return led.getState(); //unlike DigitalChannel, leds don't let you read their state, so use the variable here instead.
                        return led_state;
                    }
                }));
        telemetry.addLine(
                telemetry.item("color: ", new IFunc<Object>() {
                            @Override
                            public Object value() {
                                //return led.getState(); //unlike DigitalChannel, leds don't let you read their state, so use the variable here instead.
                                return "r: " + sensorRGB.red() + " g: " + sensorRGB.green() + " b: " + sensorRGB.blue();
                            }
                        })
        );
    }

}
