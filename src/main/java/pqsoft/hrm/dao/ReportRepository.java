package pqsoft.hrm.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import pqsoft.hrm.model.Report;

public interface ReportRepository extends PagingAndSortingRepository<Report, Integer> {}
