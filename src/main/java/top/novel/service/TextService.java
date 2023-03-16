package top.novel.service;

import top.novel.mapper.TextMapper;
import top.novel.entity.text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TextController
 * @Description TODO 测试 Service
 * @Author dongshuai
 * @Date 2022/8/10 14:12
 * @Version 1.0
 */
@Service
public class TextService {
    @Autowired
    private TextMapper textMapper;
    /**
     * 测试
     * @return
     */
    public List<text> Text(){
        return textMapper.selectList(null);
    }
}
