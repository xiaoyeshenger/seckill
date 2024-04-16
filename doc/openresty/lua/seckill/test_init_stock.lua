--openresty系统启动时执行该脚本
local cjson= require("cjson"); --加载cjson模块
local redis= require("resty.redis"); --加载redis模块
local ngx_cache= ngx.shared.dis_cache; --加载本地缓存模块
local start_time = os.clock()

--redis连接
function new_redis(ip,port,password,db_index)
  local red = redis:new();
  red:set_timeouts(1000,1000,1000);
  local ok, err = red:connect(ip,port);
    if not ok then
        ngx.say("failed to connect:", err)
        return
    end
  local _auth, err = red:auth("root");
    if not _auth then
        ngx.say("failed to authenticate:", err)
        return
    end
  local _select, err = red:select(db_index);
    if not _select then
        ngx.say("failed to select db:", err)
        return
    end
  return red
end


--1.连接redis
local red = new_redis("192.168.2.241","6379","root",5);

--2.读取redis中的数据并设置到本地内存
--(1).预约活动开启状态
local stock_status = red:get("RuntimeProductStatus");
ngx.log(ngx.ERR, "step1--> stock_status ",stock_status);
if stock_status ~= nil and stock_status ~= "" and stock_status ~= ngx.null then
  ngx_cache:set("start_".. 1 , stock_status);    
end

--(2).预约活动开启状态
local stock_amount = red:get("RuntimeVaccineStock");
ngx.log(ngx.ERR, "step2--> stock_amount ",stock_amount);
if stock_amount ~= nil and stock_amount ~= "" and stock_amount ~= ngx.null then
  ngx_cache:set("stock_".. 1 , stock_amount);
end


local end_time = os.clock()
local elapsed_time = end_time - start_time
ngx.log(ngx.ERR,"初始化同步库存信息执行耗时为：" .. elapsed_time*1000," 毫秒")
