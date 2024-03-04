local key = KEYS[1]               -- 获取第一个参数作为键名(库存的key)
local incrementBy = tonumber(ARGV[1])  -- 获取第二个参数(扣减数量)作为增量值，并将其转换为数字类型
local stock = redis.call("GET", key)  -- 通过GET命令获取键的当前库存
if nil == stock or not stock then
    return -2  -- 库存还未初始化
elseif tonumber(stock) >= incrementBy then
    return redis.call('DECRBY', key, incrementBy)  -- 库存充足
else
    return -1  -- 库存不足
end