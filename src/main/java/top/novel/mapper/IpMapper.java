package top.novel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.novel.entity.ip;

/**
 * @ClassName IpMapper
 * @Description TODO ip池 dao
 * @Author dong
 * @Date 2023/3/10
 * @Version 1.0
 */
@Mapper
public interface IpMapper extends BaseMapper<ip> {
}
