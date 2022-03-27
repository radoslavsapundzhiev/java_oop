package p06_TirePressureMonitoringSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AlarmTest {
    private Alarm alarm;
    private Sensor mockedSensor;

    @Before
    public void setUp() {
        mockedSensor = Mockito.mock(Sensor.class);
        alarm = new Alarm(mockedSensor);
    }

    @Test
    public void testCheckWithValueLessThanLowPressureThreshold() {
        Mockito.when(mockedSensor.popNextPressurePsiValue()).thenReturn(16.9);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testCheckWithValueMoreThanHighPressureThreshold() {
        Mockito.when(mockedSensor.popNextPressurePsiValue()).thenReturn(21.1);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testCheckWithValueEqualToLowPressureTreshold() {
        Mockito.when(mockedSensor.popNextPressurePsiValue()).thenReturn(17.0);
        alarm.check();
        Assert.assertFalse(alarm.getAlarmOn());
    }

    @Test
    public void testCheckWithValueEqualToHighPressureTreshold() {
        Mockito.when(mockedSensor.popNextPressurePsiValue()).thenReturn(21.0);
        alarm.check();
        Assert.assertFalse(alarm.getAlarmOn());
    }
}