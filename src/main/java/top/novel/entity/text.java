package top.novel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName TextController
 * @Description TODO 测试 实体
 * @Author dongshuai
 * @Date 2022/8/10 14:12
 * @Version 1.0
 */
@Data
@TableName("test")
public class text {
   @TableId(value = "id",type = IdType.AUTO)
   private Integer id;
   private String text;
}
