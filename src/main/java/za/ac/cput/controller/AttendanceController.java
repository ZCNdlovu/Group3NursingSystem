package za.ac.cput.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Attendance;
import za.ac.cput.domain.StatusType;
import za.ac.cput.service.IAttendanceService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final IAttendanceService attendanceService;

    public AttendanceController(IAttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // ✅ Create / Update Attendance
    @PostMapping("/save")
    public ResponseEntity<Attendance> save(@RequestBody Attendance attendance) {
        return ResponseEntity.ok(attendanceService.saveAttendance(attendance));
    }

    // ✅ Get Attendance by ID
    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getById(@PathVariable Integer id) {
        Optional<Attendance> attendance = attendanceService.getAttendanceById(id);
        return attendance.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get all Attendances
    @GetMapping("/all")
    public ResponseEntity<List<Attendance>> getAll() {
        return ResponseEntity.ok(attendanceService.getAllAttendances());
    }

    // ✅ Delete Attendance by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Get by Student ID
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> getByStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(attendanceService.getAttendancesByStudentId(studentId));
    }

    // ✅ Get by Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Attendance>> getByStatus(@PathVariable StatusType status) {
        return ResponseEntity.ok(attendanceService.getAttendancesByStatus(status));
    }

    // ✅ Get by Check-in Date
    @GetMapping("/after/{dateTime}")
    public ResponseEntity<List<Attendance>> getByCheckInAfter(@PathVariable String dateTime) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime);
        return ResponseEntity.ok(attendanceService.getAttendancesByCheckInAfter(parsedDateTime));
    }
}
