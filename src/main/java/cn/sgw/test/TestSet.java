package cn.sgw.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * 测试五大数据类型Set集合,存入其中的数据是无序的, 存入其中的数据不可以重复
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class TestSet {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加数据，但不可以重复
     */
    @Test
    public void testAdd(){
        redisTemplate.boundSetOps("testSet").add("LOL");
        redisTemplate.boundSetOps("testSet").add("CF");
        redisTemplate.boundSetOps("testSet").add("DNF");
        redisTemplate.boundSetOps("testSet").add("QQ");
    }

    /**
     * 移除并返回集合中的一个随机元素
     */
    @Test
    public void testPop(){
        System.out.println(redisTemplate.boundSetOps("testSet").pop());
    }
    /**
     * 获取弹栈的数据
     */
    @Test
    public void testMembers(){
        // [DNF, LOL, QQ, CF]
        System.out.println( redisTemplate.boundSetOps("testSet").members());
    }

    /**
     * 判断 member 元素是否是集合 key 的成员
     */
    @Test
    public void testIsMembers(){
        System.out.println(redisTemplate.boundSetOps("testSet").isMember("CF"));//true
        System.out.println(redisTemplate.boundSetOps("testSet").isMember("cf"));//false
    }
    /**
     * 删除全部数据
     */
    @Test
    public void testDelete(){
        redisTemplate.delete("testSet");
    }
}
