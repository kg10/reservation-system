package com.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Model.TimeTable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TimeTableRepository extends CrudRepository<TimeTable, Long> {
    TimeTable findByDayAndPersonnel_Id(Integer day, Long id);

    List<TimeTable> findByPersonnel_Id(Long id);

    @Transactional
    void deleteByPersonnelId(Long personnel_id);
}
