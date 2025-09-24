package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Attendance;
import za.ac.cput.domain.StatusType;

import java.time.LocalDateTime;
import java.util.List;

public interface IAttendanceRepository extends JpaRepository<Attendance, Integer> {

    //Find all attendances for a specific student
    List<Attendance> findByStudentStudentId(String studentId);


    //Find attendances by status
    List<Attendance> findByStatus(StatusType status);

    //Find attendances checked in after a specific date
    List<Attendance> findByCheckInAfter(LocalDateTime dateTime);

    //Find attendances verified by a specific staff member
    List<Attendance> findByVerifiedByStaffId(String staffId);
}
