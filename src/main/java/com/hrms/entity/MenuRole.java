package com.hrms.entity;

import java.io.Serializable;
import javax.persistence.Id;
import lombok.Data;

/**
 * Created by QSJ on 2019/7/17.
 */
@Data
public class MenuRole implements Serializable{

  @Id
  private Long id; //主键id

  private Long mid; //菜单id

  private Long rid; //角色id

}
