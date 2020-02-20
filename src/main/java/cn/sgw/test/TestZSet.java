package cn.sgw.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试五大数据类型ZSet集合,存入其中的数据是有序的, 存入其中的数据不可以重复
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class TestZSet {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testAdd(){
        redisTemplate.boundZSetOps("testZSet").add("DNF",1);
        redisTemplate.boundZSetOps("testZSet").add("CF",5);
        redisTemplate.boundZSetOps("testZSet").add("LOL",9);
        redisTemplate.boundZSetOps("testZSet").add("QQ",15);
    }
    @Test
    public void testRange(){
        // [DNF, CF, LOL, QQ]
        System.out.println(redisTemplate.boundZSetOps("testZSet").range(0,10));
    }
    @Test
    public void testDelete(){
        redisTemplate.delete("testZSet");
    }
}

