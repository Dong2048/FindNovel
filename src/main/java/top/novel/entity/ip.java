package top.novel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName ip
 * @Description TODO ip池实体
 * @Author dong
 * @Date 2023/3/10
 * @Version 1.0
 */
@TableName("t_novel_ip")
@Data
public class ip {
    //唯一标识
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Integer id;
    //ip
    private String ip;
    //端口号
    private Integer port;
    //等级
    private Integer level;
    //状态0无效，1有效
    private Integer state;
    //类型
    private String type;
    //代理地址
    private String address;
    //运营商
    private String operator;
    //最后验证时间
    private String last_test_time;

    public ip() {
    }

    public ip(Integer id, String ip, Integer port, Integer level, Integer state, String type, String address, String operator, String last_test_time) {
        this.id = id;
        this.ip = ip;
        this.port = port;
        this.level = level;
        this.state = state;
        this.type = type;
        this.address = address;
        this.operator = operator;
        this.last_test_time = last_test_time;
    }

    @Override
    public String toString() {
        return "ip{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", level=" + level +
                ", state=" + state +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", operator='" + operator + '\'' +
                ", last_test_time='" + last_test_time + '\'' +
                '}';
    }
}
