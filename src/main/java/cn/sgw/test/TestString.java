package cn.sgw.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试五大数据类型String字符串
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class TestString {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置数据
     */
    @Test
    public void testSet(){
        redisTemplate.boundValueOps("testString").set("LOL");
    }

    /**
     * 获取数据
     */
    @Test
    public void testGet(){
        System.out.println( redisTemplate.boundValueOps("testString").get());
    }

    /**
     * 删除数据
     */
    @Test
    public void testDelete(){
        redisTemplate.delete("testString");
    }
}
