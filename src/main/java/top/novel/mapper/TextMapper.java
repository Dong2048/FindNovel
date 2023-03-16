package top.novel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.novel.entity.text;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName TextController
 * @Description TODO 测试 Dao
 * @Author dongshuai
 * @Date 2022/8/10 14:12
 * @Version 1.0
 */
@Mapper
public interface TextMapper extends BaseMapper<text> {

}
