package cs246.businesscalendar;

import org.junit.*;

import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.model.UserData;
import cs246.businesscalendar.utilities.Authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void hasUsername() {
        UserData testData = new UserData();
        assertTrue(testData.getUsername().length() > 0);
    }

    @Test
    public void hashesMatch() {
        UserData testData = new UserData();
        try {
            assertEquals(Authentication.calculateHash("123"), testData.getHashedPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isAllDay() {
        Appointment testAppointment = new Appointment();
        assertTrue(testAppointment.isAllDay());
    }
}