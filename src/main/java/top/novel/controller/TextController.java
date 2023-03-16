package top.novel.controller;

import org.springframework.web.bind.annotation.RequestParam;
import top.novel.entity.ip;
import top.novel.entity.text;
import top.novel.mapper.IpMapper;
import top.novel.pool.IpPool;
import top.novel.service.IpService;
import top.novel.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName TextController
 * @Description TODO 测试 Controller
 * @Author dongshuai
 * @Date 2022/8/10 14:12
 * @Version 1.0
 */
@Controller
@RestController
@RequestMapping("/Text")
public class TextController {

    @Autowired
    private TextService textService;
    @Autowired
    private IpService ipService;
    @Autowired
    private IpPool ipPool;

    /**
     * 测试
     * @return
     */
    @GetMapping("/findText")
    public void findText(@RequestParam(value = "url") String url){
        List<ip>list =ipService.findList(null);
        List<ip>resultlist =ipPool.activeIp(list,"https://www.baidu.com");
        ipService.modifyList(resultlist);
    }


}
