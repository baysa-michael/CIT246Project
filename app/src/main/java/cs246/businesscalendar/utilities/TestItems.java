package cs246.businesscalendar.utilities;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import cs246.businesscalendar.model.Appointment;

public class TestItems {
    public static List<Appointment> testAppointments() {
        List<Appointment> testAppointments = new ArrayList<>();
        testAppointments.add(new Appointment(
                new LocalDate(2019, 3, 16)
                , false
                , new LocalTime(8,0)
                , new LocalTime(9,0)
                , "Test 1"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new Appointment(
                new LocalDate(2019, 3, 16)
                , false
                , new LocalTime(10,30)
                , new LocalTime(11,30)
                , "Test 2"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new Appointment(
                new LocalDate(2019, 3, 16)
                , false
                , new LocalTime(12,0)
                , new LocalTime(13,0)
                , "Test 3"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new Appointment(
                new LocalDate(2019, 3, 16)
                , false
                , new LocalTime(14,0)
                , new LocalTime(15,0)
                , "Test 4"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new Appointment(
                new LocalDate(2019, 3, 16)
                , false
                , new LocalTime(16,0)
                , new LocalTime(17,0)
                , "Test 5"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new Appointment(
                new LocalDate(2019, 3, 16)
                , false
                , new LocalTime(18,0)
                , new LocalTime(19,0)
                , "Test 6"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new Appointment(
                new LocalDate(2019, 3, 16)
                , false
                , new LocalTime(20,0)
                , new LocalTime(21,0)
                , "Test 7"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new Appointment(
                new LocalDate(2019, 3, 17)
                , false
                , new LocalTime(8,0)
                , new LocalTime(9,0)
                , "Test 8"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new Appointment(
                new LocalDate(2019, 3, 17)
                , false
                , new LocalTime(10,0)
                , new LocalTime(11,0)
                , "Test 9"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new Appointment(
                new LocalDate(2019, 3, 17)
                , false
                , new LocalTime(12,0)
                , new LocalTime(13,0)
                , "Test 10"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));

        return testAppointments;
    }
}
