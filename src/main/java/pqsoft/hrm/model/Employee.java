package pqsoft.hrm.model;

import java.util.*;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "employees")
public class Employee {

  @Id
  @Column(name = "employee_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int admin;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  private String phone;
  private String email;
  private String alias;
  private int status;

  @ManyToMany(mappedBy = "employees")
  private List<Task> tasks;
}
