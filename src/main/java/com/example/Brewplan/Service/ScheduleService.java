//package com.example.Brewplan.Service;
//
//import com.example.Brewplan.Model.Schedule;
//import com.example.Brewplan.Repository.ScheduleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ScheduleService {
//    @Autowired
//    private ScheduleRepository scheduleRepository;
//
//    public List<Schedule> getAllSchedules() {
//        return scheduleRepository.findAll();
//    }
//
//    public Optional<Schedule> getScheduleById(Long id) {
//        return scheduleRepository.findById(id);
//    }
//
//    public Schedule saveSchedule(Schedule schedule) {
//        return scheduleRepository.save(schedule);
//    }
//
//    public void deleteSchedule(Long id) {
//        scheduleRepository.deleteById(id);
//    }
//}
