package com.lcy.cssm.common.core.redis;

import com.alibaba.fastjson.JSON;
import com.lcy.cssm.common.base.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * redis客户端类
 *
 * @auther lcy
 * @create 2017-08-09 13:32
 */
@Service
public class RedisClient {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;



    public boolean set(final String key, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return true;
            }
        });
        return result;
    }

    public boolean isExist(final String key) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return connection.exists(serializer.serialize(key));
            }
        });
        return result;
    }



    public boolean setEx(final String key, final long expireTime, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.setEx(serializer.serialize(key), expireTime, serializer.serialize(value));
                return true;
            }
        });
        return result;
    }

    public boolean setEx2(final String key, final long expireTime, final byte[] bytes){
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.setEx(serializer.serialize(key), expireTime, bytes);
                return true;
            }
        });
        return result;
    }


    public Set<String> like(String key) {
        return redisTemplate.keys(key + "*");

    }

    public String get(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    public byte[] get2(final String key) {
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(key));
                return value;
            }
        });
        return result;
    }

    public List<String> getList2(final String key){
        List<String> list = redisTemplate.execute(new RedisCallback<List<String>>() {
            @Override
            public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                List<byte[]> list = connection.lRange(serializer.serialize(key), 0, -1);
                return list.stream().map(e -> new String(e)).collect(Collectors.toList());
            }
        });
        return list;
    }


    public boolean delete(final String key) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.del(serializer.serialize(key));
                return true;
            }
        });
        return result;
    }


    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }


    public <T> boolean setList(String key, List<T> list) {
        String value = JSON.toJSONString(list);
        return set(key, value);
    }


    public <T> List<T> getList(String key, Class<T> clz) {
        String json = get(key);
        if (json != null) {
            List<T> list = JSON.parseArray(json, clz);
            return list;
        }
        return null;
    }


    public long lpush(final String key, Object obj) {
        final String value = JSON.toJSONString(obj);
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));
                return count;
            }
        });
        return result;
    }


    public long rpush(final String key, Object obj) {
        final String value = JSON.toJSONString(obj);
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long count = connection.rPush(serializer.serialize(key), serializer.serialize(value));
                return count;
            }
        });
        return result;
    }


    public String lpop(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] res = connection.lPop(serializer.serialize(key));
                return serializer.deserialize(res);
            }
        });
        return result;
    }


    /**
     * 返回索引index的值
     * @param index
     * @param key
     * @return
     */
    public String getByIndex(final long index,final String key){
        String res = redisTemplate.execute(new RedisCallback<String>() {

            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long len = connection.lLen(serializer.serialize(key));
                byte[] res = connection.lIndex(serializer.serialize(key), index - len);
                return serializer.deserialize(res);
            }
        });
        return res;
    }

    /**
     * 删除key列表里所有=value的值
     * @param key
     * @param value
     */
    public long rmListValue(final String key,final String value){
        Long res = redisTemplate.execute(new RedisCallback<Long>() {

            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return connection.lRem(serializer.serialize(key), 0, serializer.serialize(value));
            }
        });
        return res;
    }

    public long getLen(final String key){
        Long len = redisTemplate.execute(new RedisCallback<Long>() {

            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long len = connection.lLen(serializer.serialize(key));
                return len;
            }
        });
        return  len;
    }

    /**
     * 在value前面插入value2，若长度超过8，删除最后一个
     * @param value
     * @param value2
     * @return
     */
    public boolean insertBefore(final String key,final String value,final String value2){
        Boolean res = redisTemplate.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.watch(serializer.serialize(key));
                long len = connection.lLen(serializer.serialize(key));
                if(len== CommonConstant.EIGHT){
                    connection.rPop(serializer.serialize(key));
                }
                connection.multi();
                connection.lInsert(serializer.serialize(key), RedisListCommands.Position.BEFORE,
                        serializer.serialize(value), serializer.serialize(value2));
                connection.exec();
                return true;
            }
        });
        return res;
    }


    public long lpushStr(final String key,final String value) {

        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));
                return count;
            }
        });
        return result;
    }

}
