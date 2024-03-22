local cjson= require("cjson"); --加载cjson模块
local ngx_cache= ngx.shared.dis_cache;  --加载本地内存
--从nginx本地缓存获取设备数据
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

--2.获取请求体函数
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

--3.json判断函数
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

--4.解析请求参数并更新本地内存
local badyParams = get_post_body_data()
local json_data = cjson.encode(badyParams)
ngx.log(ngx.ERR, "step1--> json_data ",json_data)
if isJsonEmpty(json_data) then
   say_result(400,"请求方法不正确,请按文档传入参数",ngx.null)
end

--(1).疫苗活动id
local vaccineReleaseId = badyParams.vaccineReleaseId
if vaccineReleaseId == nil then
   say_result(400,"vaccineReleaseId不能为空",ngx.null)
end

--(2).数量
local amount = badyParams.amount
if amount == nil then
   say_result(400,"amount不能为空",ngx.null)
end
local amount = badyParams.amount

--5.更新数据到内存
local start = 0
if amount > 0 then
  start = 1
end
ngx_cache:set("start_"..vaccineReleaseId,start)
ngx_cache:set("stock_"..vaccineReleaseId,amount)

--6.返回结果
say_result(200,"更新库存状态到nginx内存成功",ngx.null)

