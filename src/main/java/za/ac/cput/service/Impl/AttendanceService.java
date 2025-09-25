package za.ac.cput.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Attendance;
import za.ac.cput.domain.StatusType;
import za.ac.cput.repository.IAttendanceRepository;
import za.ac.cput.service.IAttendanceService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService implements IAttendanceService {

    private final IAttendanceRepository repository;

    public AttendanceService(IAttendanceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Attendance saveAttendance(Attendance attendance) {
        return repository.save(attendance);
    }

    @Override
    public Optional<Attendance> getAttendanceById(Integer attendanceId) {
        return repository.findById(attendanceId);
    }

    @Override
    public List<Attendance> getAllAttendances() {
        return repository.findAll();
    }

    @Override
    public void deleteAttendance(Integer attendanceId) {
        repository.deleteById(attendanceId);
    }

    @Override
    public List<Attendance> getAttendancesByStudentId(String studentId) {
        return repository.findByStudentStudentId(studentId);
    }

    @Override
    public List<Attendance> getAttendancesByStatus(StatusType status) {
        return repository.findByStatus(status);
    }

    @Override
    public List<Attendance> getAttendancesByCheckInAfter(LocalDateTime dateTime) {
        return repository.findByCheckInAfter(dateTime);
    }
}
