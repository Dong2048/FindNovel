package top.novel.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.novel.entity.ip;
import top.novel.mapper.IpMapper;

import java.util.List;

/**
 * @ClassName IpService
 * @Description TODO ip池 service
 * @Author dong
 * @Date 2023/3/10
 * @Version 1.0
 */
@Service
public class IpService {
    @Autowired
    private IpMapper ipMapper;
    //查询
    public List<ip> findList(Wrapper<ip> wrapper){
        return ipMapper.selectList(wrapper);
    }
    //更改
    public void modifyList(List<ip> list){
        for (ip p: list) {
            ipMapper.updateById(p);
        }
    }
}
