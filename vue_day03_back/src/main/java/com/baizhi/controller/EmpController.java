package com.baizhi.controller;

import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Emp)表控制层
 *
 * @author zhuchifeng
 * @since 2021-07-29 08:54:04
 */
@RestController
@CrossOrigin
public class EmpController {
    private static final Logger log = LoggerFactory.getLogger(EmpController.class);
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
    @GetMapping("/emp/{id}")
    public Emp emp(@PathVariable("id") Integer id) {
        return this.empService.queryById(id);
    }

    //查询所有员工信息
    @GetMapping("/emp")
    public List<Emp> emps() {
        List<Emp> emps = empService.queryAllByLimit(0, Integer.MAX_VALUE);
        log.info("查询数据库员工列表条数：{}", emps.size());
        return emps;
    }

    //保存员工信息
    @PostMapping("/emp")
    public void update(@RequestBody Emp emp) {
        log.info("当前保存或修改员工信息：{}", emp.toString());
        if (StringUtils.isEmpty(emp.getId())) {//保存
            empService.insert(emp);
        } else {
            empService.update(emp);
        }
    }

    //根据员工id删除员工信息
    @DeleteMapping("/emp/{id}")
    public void delete(@PathVariable("id") Integer id) {
        log.info("要删除的员工id：{}", id);
        empService.deleteById(id);
    }
}
