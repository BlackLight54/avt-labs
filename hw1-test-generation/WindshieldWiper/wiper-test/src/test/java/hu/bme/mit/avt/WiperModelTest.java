package hu.bme.mit.avt;

import hu.bme.mit.avt.impl.WiperControllerImpl;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.mockito.verification.VerificationMode;

import static org.mockito.Mockito.*;


@GraphWalker(start = "init")
public class WiperModelTest extends ExecutionContext implements WiperModel {
    WiperMotor motor = mock(WiperMotor.class);
    WiperController controller = new WiperControllerImpl(motor);
    RainMode rainMode = RainMode.NO;

    @Override
    public void init() {
    }

    @Override
    public void e_turnOff() {
        controller.setWiperSwitch(WiperMode.OFF);
    }

    @Override
    public void e_setSpeedOne() {
        controller.setWiperSwitch(WiperMode.SPEED_ONE);
    }

    @Override
    public void e_setSpeedTwo() {
        controller.setWiperSwitch(WiperMode.SPEED_TWO);
    }

    @Override
    public void e_setAuto() {
        controller.setWiperSwitch(WiperMode.AUTO);
    }

    @Override
    public void e_detectNoRain() {
        rainMode = RainMode.NO;
        controller.rainIntensityChanged(RainMode.NO);
    }

    @Override
    public void e_detectLightRain() {
        rainMode = RainMode.LIGHT;
        controller.rainIntensityChanged(RainMode.LIGHT);
    }

    @Override
    public void e_detectHeavyRain() {
        rainMode = RainMode.HEAVY;
        controller.rainIntensityChanged(RainMode.HEAVY);
    }

    @Override
    public void v_Off() {
        verify(motor).setInterval(0);
        reset(motor);
    }

    @Override
    public void v_SpeedOne() {
        verify(motor).setInterval(1);
        reset(motor);
    }

    @Override
    public void v_SpeedTwo() {
        verify(motor).setInterval(0.5);
        reset(motor);
    }

    @Override
    public void v_Auto() {
        if (rainMode == RainMode.NO) {
            verify(motor).setInterval(0);
        } else if (rainMode == RainMode.HEAVY) {
            verify(motor).setInterval(0.5);
        } else if (rainMode == RainMode.LIGHT) {
            verify(motor).setInterval(1);
        }
        reset(motor);
    }


}
