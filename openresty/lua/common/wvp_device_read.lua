local start_time = os.clock()
ngx.header.content_type="application/json;charset=utf-8";
local cjson= require("cjson"); --加载cjson模块
local mysql= require("resty.mysql"); --加载mysql模块
local redis= require("resty.redis"); --加载redis模块

--提取参数：wvp设备id
local id= ngx.req.get_uri_args()["id"];
ngx.log(ngx.ERR, "step1--> 请求参数id为 ",id);
--加载本地缓存模块
local ngx_cache= ngx.shared.dis_cache; 
--从nginx本地缓存获取设备数据
local local_cache = ngx_cache:get("device_"..id); 
--ngx.log(ngx.ERR, "step2--> local_cache ",local_cache);
--如果本地缓存没有数据则从redis加载数据到本地缓存
if local_cache == "" or local_cache == nil then 
    --引入redis模块
    local redis= require("resty.redis"); 
    --实例化redis对象
    local red = redis:new(); 
    --超时
    red:set_timeouts(1000,1000,1000);
    --建立连接
    local ok, err = red:connect("172.35.24.7",6377);
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
    --从redis取值
    local redis_data = red:get("device_"..id);
    --ngx.log(ngx.ERR, "step3--> redis_data ",redis_data);
    --如果redis没有数据则从mysql中加载数据到redis
    if redis_data == nil or redis_data == "" or redis_data == ngx.null then
    	local db= mysql:new();
	local props = {
        	 host = "172.35.24.7",
         	 port = 3308,
         	 database ="wvp",
         	 user ="root",
        	 password ="myjskj999666"
 	}
	db:connect(props);
	local select_sql = "select * from wvp_device where id="..id.." order by keepalive_time;";
	local mysql_data= db:query(select_sql);
	local typ = type(mysql_data);
        --ngx.log(ngx.ERR, "step4--> mysql_data_type ",typ);
        --ngx.log(ngx.ERR, "step4--> mysql_data ",cjson.encode(mysql_data));
	if mysql_data == nil or next(mysql_data) == nil or mysql_data == "" or mysql_data == ngx.null then
	    ngx.say("id为:", id, " 的设备不存在");
	    return
	end
	local_cache = cjson.encode(mysql_data);
	--关闭mysql连接
	db:close();
	--输入写入redis
	red:set("device_"..id,local_cache);
	--关闭redis连接
	red:close();	
    else
	local_cache = redis_data;
    end
    --存入数据到本地缓存
    ngx_cache:set("device_"..id , local_cache); 
end
--返回数据给客户端
ngx.say(local_cache);
local end_time = os.clock()
local elapsed_time = end_time - start_time
ngx.log(ngx.ERR,"执行耗时为：" .. elapsed_time*1000," 毫秒")
