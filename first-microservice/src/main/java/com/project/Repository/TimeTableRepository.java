package com.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Model.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
	public TimeTable findByDayAndPersonnel_Id(Integer day, Long id);

}
