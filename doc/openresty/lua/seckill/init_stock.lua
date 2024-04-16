--openresty系统启动时执行该脚本,由于openresty启动时还无任何上下文信息，不能操作redis、mysql\cjson等任何模块
--所以通过shell命令操作redis,需要在openresty容器内部安装redis以便能够执行redis-cli命令
local ngx_cache= ngx.shared.dis_cache; --加载本地缓存模块
local start_time = os.clock()

--1.疫苗预约活动ID
local vaccineReleaseId = 1

--2.获取活动启动状态(redis中为string类型,需要tostring转为string类型,不对应类型转换会出现值为nil)
local cmd = "redis-cli -h 192.168.2.241 -p 6379 -a root -n 5 get RuntimeVaccineStatus"
local f = io.popen(cmd)
--local stock_status = tostring(f:read())
local stock_status = f:read("*a")
f:close()
ngx.log(ngx.ERR, "step1--> stock_status ",stock_status);
if stock_status ~= nil and stock_status ~= "" and stock_status ~= ngx.null then
  ngx_cache:set("start_"..vaccineReleaseId,tonumber(stock_status))
end

--3.获取疫苗库存(redis中为number类型,所以需要tonumber转为number类型,不对应类型转换会出现值为nil)
local cmd1 = "redis-cli -h 192.168.2.241 -p 6379 -a root -n 5 get RuntimeVaccineStock"
local f1 = io.popen(cmd1)
local stock_amount = f1:read("*a")
f1:close()
ngx.log(ngx.ERR, "step2--> stock_amount ",stock_amount);
if stock_amount ~= nil and stock_amount ~= "" and stock_amount ~= ngx.null then
  ngx_cache:set("stock_"..vaccineReleaseId,tonumber(stock_amount))
end

local end_time = os.clock()
local elapsed_time = end_time - start_time
ngx.log(ngx.ERR,"step3--> 初始化同步库存信息执行耗时为：" .. elapsed_time*1000," 毫秒")
