package com.hrms.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * Created by QSJ on 2019/7/17.
 */
@Data
public class MenuRole implements Serializable{

  private Integer id; //主键id

  private Integer mid; //菜单id

  private Integer rid; //角色id

}
