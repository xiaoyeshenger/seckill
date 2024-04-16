local start_time = os.clock()
ngx.header.content_type="application/json;charset=utf-8";
local cjson= require("cjson"); --加载cjson模块
local redis= require("resty.redis"); --加载redis模块
--1.定义统一返回函数
function say_result(code,msg,data)
  local jsonObject = {
    code = code,
    msg = msg,
    data = data
  }
  local json_data = cjson.encode(jsonObject)
  ngx.say(json_data);
  ngx.exit(ngx.HTTP_OK)
end


--提取参数：id
local id= ngx.req.get_uri_args()["id"];
ngx.log(ngx.ERR, "step1--> 请求参数id为 ",id);
--加载本地缓存模块
local ngx_cache= ngx.shared.dis_cache; 

--1.判断是否能够开始预约抢苗(未开始/已结束(库存被抢空))
local start_local_cache = ngx_cache:get("start_"..id);
ngx.log(ngx.ERR, "step2--> start_local_cache ",start_local_cache);
if start_local_cache == 0 or start_local_cache == "" or start_local_cache == nil then
  say_result(400,"暂不能预约，预约未开始或预约已结束",ngx.null)  
end 

--2.从nginx本地缓存获取数据
local local_cache = ngx_cache:get("kucun_"..id); 
ngx.log(ngx.ERR, "step3--> inventory_local_cache ",local_cache);
--如果本地缓存没有数据则从redis加载数据到本地缓存
if local_cache == "" or local_cache == nil then
  say_result(400,"暂不能预约，预约未开始或预约已结束",ngx.null)
end 

local_cache = local_cache - 1;
ngx_cache:set("kucun_"..id,local_cache);

--引入redis模块
local redis= require("resty.redis"); 
--实例化redis对象
local red = redis:new(); 
--超时
red:set_timeouts(1000,1000,1000);
--建立连接
local ok, err = red:connect("192.168.2.241",6379);
if not ok then
ngx.say("failed to connect:", err)
   return
end
--redis认证
local _auth, err = red:auth("root");
if not _auth then
    ngx.say("failed to authenticate:", err)
    return
end
--redis选择指定的数据库
local _select, err = red:select(5);
if not _select then
   ngx.say("failed to select db:", err)
   return
end
--输入写入redis
red:set("kucun_"..id,local_cache);
--关闭redis连接
red:close();	

--返回数据给客户端
local end_time = os.clock()
local elapsed_time = end_time - start_time
ngx.log(ngx.ERR,"执行耗时为：" .. elapsed_time*1000," 毫秒")
say_result(200,"预约成功",ngx.null)

