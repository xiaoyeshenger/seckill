local redis = require "wangan.common.redis_iresty"
local cache = require "wangan.common.share_cache"

local red = redis:new({
    ip = "192.168.2.241",
    port = 6379,
    password = "root",
    timeout = 2000,
    db_index = 5,
    max_idle_ms = 60000,
    pool_size = 32
})



local handlerRepeat = function ( ... )
   --ngx.log(ngx.ERR, "从redis加载接种点和库存到本地内存...")
    local len = red:llen("outlets:ids")
	
    local outlets = red:lrange("outlets:ids", 0, len)

    local today = ngx.today()

    for _, v in pairs(outlets) do
        local stock_key = "outlet:" .. v .. ":date:" ..today .. ":stock"

        local stock = red:get(stock_key) --从redis查到当日这个网点的库存

        if stock then
            local cache_v = cache.get_from_cache(stock_key)

            local ok, err = cache.set_to_cache(stock_key, stock, 30) --缓存在本地内存shared_dict

            if not ok then
                ngx.log(ngx.ERR , "写入本地缓存失败：", err)
            end
        end
    end
end

--执行每delay秒(1秒)跟redis同步一次数据
local delay = 1
local ok, err = ngx.timer.every(delay, handlerRepeat)
if not ok then
    ngx.log(ngx.ERR, "创建timer.every(delay, handlerRepeat)失败：", err)
    return
end
