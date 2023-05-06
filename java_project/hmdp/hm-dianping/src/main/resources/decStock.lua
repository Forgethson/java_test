-- 库存是否大于0
if(tonumber(redis.call('get', KEYS[1])) <= 0) then
    -- 已经抢完
    return 1;
end
-- 库存-1
redis.call('incrby', KEYS[1], -1)
return 0