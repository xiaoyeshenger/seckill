local jwt = require "resty.jwt"
local cjson= require("cjson")
local ngx_cache= ngx.shared.dis_cache
local cache_code = ngx.shared.checkcode

--1.常用函数
--(1).统一返回函数
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

--(2).请求体参数获取函数
function get_post_body_data()
  ngx.req.read_body()
  local data = ngx.req.get_body_data()
  if data then
    local params = cjson.decode(data)
    return params
  else
    return nil
  end
end

--(3).json判断函数
local function isJsonEmpty(json)
    if json == nil then
        return true
    elseif type(json) == "table" then
        for k, v in pairs(json) do
            return false
        end
        return true 
    else
        return false
    end
end


--2.解析并校验请求头中的toekn(authToken为是否开启jwt token校验)
local authToken = 1
if authToken == 1 then
  --(1).判断token是否存在
  local token = ngx.req.get_headers()["Authorization"]
  ngx.log(ngx.ERR, "token = ",token)
  if token == nil then
    say_result(400,"token不能为空",ngx.null)
  end

  --(2).校验token
  --注意:后端项目比如springboot中jwt使用的是io.jsonwebtoken.jjwt-api包的话.springboot中配置的secret为base64加密后的字符串,而此处需要decode变回原文字符串
  local secret = "VappYmNjNzZjODhmMzk2ZDk0NTFiNzA5MmYxNzhhZGI0ZGM2NDdmNzBlMzE3Nzg2NzE1NWE3MGQ5ZWFmMzQ4ODk1MDcyNzA3ZTkyM2VlOTdhZGJkNDYxM2QwYzU3NjnVapp"
  local jwt_obj = jwt:verify(secret, token)
  if jwt_obj.verified == false then
    say_result(400,"token校验失败",ngx.null)
  end
end


--3.解析并校验请求参数(authParam为是否开启请求参数校验)
local badyParams = get_post_body_data()
local authParam = 1
if authParam == 1 then
  --解析post body json请求--
  local json_data = cjson.encode(badyParams)
  ngx.log(ngx.ERR, "step1--> json_data ",json_data)
  if isJsonEmpty(json_data) then
    say_result(400,"请求参数不能为空",ngx.null)
  end

  --(1).疫苗活动id是否为空
  local vaccineReleaseId = badyParams.vaccineReleaseId
  if vaccineReleaseId == nil then
   say_result(400,"vaccineReleaseId不能为空",ngx.null)
  end

  --(2).openId是否为空
  local openId = badyParams.openId
  if openId == nil then
    say_result(400,"openId不能为空",ngx.null)
  end

  --(3).记录类型是否为空
  local recordType = badyParams.recordType
  if recordType == nil then
    say_result(400,"recordType不能为空",ngx.null)
  end

end

--4.校验图形验证码(checkCode为是否开启图形验证码校验)
local checkCode = 0;
if checkCode == 1 then

  local codeKey = badyParams.codeKey
  if codeKey == nil then
    say_result(400,"codeKey不能为空",ngx.null)
  end
        
  local codeValue = badyParams.codeValue
  if codeValue == nil then
    say_result(400,"codeValue不能为空",ngx.null)
  end
  
  --本地内存获取到验证码
  local value = cache_code:get(codeKey)
  if value == nil then
    say_result(400,"验证码已失效",ngx.null)
  end
  --校验失败，直接返回错误信息到请求端
  if string.lower(value) ~= string.lower(codeValue) then
    say_result(400,"验证码校验失败",ngx.null)    
  end
  --校验成功，将该验证码设置为空，即校验成功后不支持再次校验
  cache_code:set(codeKey, nil)
end

--5.判断预约活动是否开始和是否还有库存(checkStock为是否开启库存校验)
local checkStock = 1;
if checkStock == 1 then
  --(1).判断本地内存中预约活动开始标识是否不为空或0
  local vaccineReleaseId = badyParams.vaccineReleaseId
  local start_local_cache = ngx_cache:get("start_"..vaccineReleaseId);
  ngx.log(ngx.ERR, "step2--> start_local_cache ",start_local_cache);
  if start_local_cache == 0 or start_local_cache == "" or start_local_cache == nil then
    say_result(400,"暂不能预约，预约未开始或预约已结束!",ngx.null)
  end
  
  --(2).本地库存是否为空
  local stock_local_cache = ngx_cache:get("stock_"..vaccineReleaseId);
    ngx.log(ngx.ERR, "step3--> stock_local_cache ",stock_local_cache);
  if stock_local_cache == "" or stock_local_cache == nil or stock_local_cache == 0 then
    say_result(400,"暂不能预约，预约未开始或预约已结束!!",ngx.null)
  end
  
  --(3).本地内存库存减1,redis中和mysql中的库存由后端代码完成,此处只做库存感知处理，只判断预约活动是否开始和库存数量是否小于0
  stock_local_cache = stock_local_cache - 1;
  if stock_local_cache < 0 then
    ngx_cache:set("start_"..vaccineReleaseId,0);
    say_result(400,"预约失败,库存已被抢空...",ngx.null)
  end

  --(4).如果扣减后的库存为0，则说明库存被抢空，将预约活动开始标识设置为0(停用)
  if stock_local_cache == 0 then
    ngx_cache:set("start_"..vaccineReleaseId,0);
  end
  
  --(5).保存扣减后的最新库存数量到本地内存
  ngx_cache:set("stock_"..vaccineReleaseId,stock_local_cache);

end



