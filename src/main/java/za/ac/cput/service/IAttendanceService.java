package za.ac.cput.service;

import za.ac.cput.domain.Attendance;
import za.ac.cput.domain.StatusType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IAttendanceService {

    Attendance saveAttendance(Attendance attendance);

    Optional<Attendance> getAttendanceById(Integer attendanceId);

    List<Attendance> getAllAttendances();

    void deleteAttendance(Integer attendanceId);

    List<Attendance> getAttendancesByStudentId(String studentId);

    List<Attendance> getAttendancesByStatus(StatusType status);

    List<Attendance> getAttendancesByCheckInAfter(LocalDateTime dateTime);
}
