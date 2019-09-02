package pqsoft.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pqsoft.hrm.dao.EmployeeRepository;
import pqsoft.hrm.dao.ReportRepository;
import pqsoft.hrm.dao.TaskRepository;
import pqsoft.hrm.dto.ReportDto;
import pqsoft.hrm.dto.ReportSearchDto;
import pqsoft.hrm.model.Report;
import pqsoft.hrm.service.ReportService;
import pqsoft.hrm.util.SecurityUtils;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ReportController {
  private final ReportRepository reportRepos;
  private final EmployeeRepository employeeRepos;
  private final TaskRepository taskRepos;
  private final ReportService reportService;

  @Autowired
  public ReportController(
      ReportRepository reportRepos, EmployeeRepository employeeRepos, TaskRepository taskRepos, ReportService reportService) {
    this.reportRepos = reportRepos;
    this.employeeRepos = employeeRepos;
    this.taskRepos = taskRepos;
    this.reportService = reportService;
  }

  @GetMapping("/reports")
  public String index(
      Model model,
      @PageableDefault(page = 0, size = 2)
          @SortDefault.SortDefaults({
            @SortDefault(sort = "date", direction = Sort.Direction.DESC)
          })
          Pageable pageable) {

    prepareDataList(model, pageable, new ReportSearchDto());
    return "reports";
  }

  private void prepareDataList(
      Model model,
      @SortDefault.SortDefaults({
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
            @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
          })
          @PageableDefault(page = 0, size = 2)
          Pageable pageable,
      final ReportSearchDto searchDto) {
    final Page<Report> reports = reportService.search(pageable, searchDto);
    model.addAttribute("reports", reports);
    model.addAttribute("creators", employeeRepos.findByStatus(1));
    model.addAttribute("tasks", taskRepos.findByCreator(employeeRepos.findOne(SecurityUtils.getEmployeeId())));

    // dto for search/add/edit
    model.addAttribute("searchDto", searchDto);
    model.addAttribute("newDto", new ReportDto());
    model.addAttribute("admin", SecurityUtils.getAdmin());

    // paging
    model.addAttribute(
        "pageNumbers",
        IntStream.rangeClosed(1, reports.getTotalPages()).boxed().collect(Collectors.toList()));
    model.addAttribute("currentPage", pageable.getPageNumber());
    model.addAttribute("totalPages", reports.getTotalPages());
  }

  @RequestMapping(
    value = "/reports/search",
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  public String search(
      Model model,
      @PageableDefault(page = 0, size = 10)
          @SortDefault.SortDefaults({
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
            @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
          })
          Pageable pageable,
      @ModelAttribute("searchDto") ReportSearchDto searchDto) {
    prepareDataList(model, pageable, searchDto);
    return "reports";
  }

  @RequestMapping(
    value = "/reports/delete",
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  public RedirectView delete(@RequestParam Integer id, @RequestParam Integer creator) {
    checkReportOwner(creator);

    reportRepos.delete(id);
    return new RedirectView("/reports");
  }

  @RequestMapping(
    value = "/reports/add",
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  public RedirectView create(@ModelAttribute("newDto") ReportDto input) {
    final Report report = new Report();
    report.setTitle(input.getTitle());
    report.setDate(new Date());
    report.setDescription(input.getDescription());

    int creator = SecurityUtils.getEmployeeId();
    report.setCreator(employeeRepos.findOne(creator));

    reportRepos.save(report);
    return new RedirectView("/reports");
  }

  @RequestMapping(
    value = "/reports/update",
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  public String update(@ModelAttribute("updateDto") ReportDto input) {
    return "reports";
  }

  private void checkReportOwner(int creator) {
    boolean isAdmin = SecurityUtils.getAdmin() == 1;
    int employeeId = SecurityUtils.getEmployeeId();
    if (employeeId != creator && !isAdmin) {
      throw new IllegalArgumentException(
          "Don't have permission to update the report because you are not report owner or admin");
    }
  }
}
