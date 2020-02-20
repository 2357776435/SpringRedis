package cn.sgw.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Set;

/**
 * 测试五大数据类型Hash键值对,存入的数据是无序的, key不可以重复
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class TestHash {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置数据
     */
    @Test
    public void testPut(){
        redisTemplate.boundHashOps("testHash").put("001","LOL");
        redisTemplate.boundHashOps("testHash").put("002","CF");
        redisTemplate.boundHashOps("testHash").put("003","DNF");
    }

    /**
     * 获取数据单个数据
     */
    @Test
    public void testGetOne(){
        System.out.println(redisTemplate.boundHashOps("testHash").get("002"));
    }
    /**
     * 获取数据多个数据
     */
    @Test
    public void testGetAll(){
        System.out.println(redisTemplate.boundHashOps("testHash").entries());//{002=CF, 003=DNF, 001=LOL}
        Map<String,String> testHash = (Map<String,String>)redisTemplate.boundHashOps("testHash").entries();
        Set<Map.Entry<String, String>> entries = testHash.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println("===key==="+entry.getKey());
            System.out.println("===value==="+entry.getValue());
        }
    }
    /**
     * 删除单个数据
     */
    @Test
    public void testDeleteOne(){
        redisTemplate.boundHashOps("testHash").delete("001");
    }

    /**
     * 删除全部数据
     */
    @Test
    public void testDeleteAll(){
        redisTemplate.delete("testHash");
    }
}
