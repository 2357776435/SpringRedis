package cn.sgw.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 测试五大数据类型List集合,存入其中的数据是有序的, 存入其中的数据可以重复
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class TestList {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置左侧插入(压栈)数据，先进后出，类似于球放在瓶内，最后放的最先拿出
     */
    @Test
    public void testLeftPush(){
        redisTemplate.boundListOps("testList").leftPush("LOL");
        redisTemplate.boundListOps("testList").leftPush("CF");
        redisTemplate.boundListOps("testList").leftPush("DNF");
        redisTemplate.boundListOps("testList").leftPush("QQ");
    }

    /**
     * 设置右侧插入(队列)数据，先进先出，类似于球放在管道内，最后放的最后拿出
     */
    @Test
    public void testRightPush(){
        redisTemplate.boundListOps("testList").rightPush("LOL");
        redisTemplate.boundListOps("testList").rightPush("CF");
        redisTemplate.boundListOps("testList").rightPush("DNF");
        redisTemplate.boundListOps("testList").rightPush("QQ");
    }

    /**
     * 获取数据
     */
    @Test
    public void testRange(){
        //获取从第1个位置开始到第10个位置结束
        // LeftPust-->[QQ, DNF, CF, LOL]
        // RightPush-->[LOL, CF, DNF, QQ]
        System.out.println(redisTemplate.boundListOps("testList").range(0, 10));
    }

    /**
     * 删除全部数据
     */
    @Test
    public void testDelete() {
        redisTemplate.delete("testList");
    }
}
