package com.baizhi.controller;

import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Emp)表控制层
 */
@RestController
@CrossOrigin
public class EmpController {
    /**
     * 服务对象
     */
    @Resource
    private EmpService empService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/emp")
    public Emp emp(Integer id) {
        return this.empService.queryById(id);
    }

    /**
     * 查询所有
     */
    @GetMapping("/emps")
    public List<Emp> emp() {
        return empService.queryAllByLimit(0, Integer.MAX_VALUE);
    }

    /**
     * 添加员工信息
     */
    @PostMapping("/emp")
    public void add(@RequestBody Emp emp) {
        if (StringUtils.isEmpty(emp.getId())) {
            empService.insert(emp);
        } else {
            empService.update(emp);
        }
    }
}