package me.xiongxuan.xoj.controller;

import me.xiongxuan.xoj.service.JudgeStatusService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author XiongXuan
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Resource
    private JudgeStatusService judgeStatusService;


}
