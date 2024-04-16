--[[
    预约校验
]]

local cache = require "wangan.common.share_cache"

local json = require "cjson"

--先读request body
ngx.req.read_body()
--从request body里获取参数
--local args = ngx.req.get_post_args()


--获取request body data
local request_body_data = ngx.req.get_body_data()

if not request_body_data then
    ngx.say("request body is nil")
    return
end

ngx.log(ngx.INFO, "request body string", request_body_data)
--将request body data解析为json
local request_body_json = json.decode(request_body_data)

--ngx.log(ngx.INFO, "request body json", request_body_json)


--请求参数校验
local outletId = request_body_json.outletId

if not outletId then
    ngx.say("网点id不可为空")
    return
end


--库存剩余校验
local stock_key = "outlet:" .. outletId .. ":date:" .. ngx.today() .. ":stock"
ngx.log(ngx.INFO, "stock_key ", stock_key)
local stock = cache.get_from_cache(stock_key);

if not stock then
    ngx.say("未查到库存，该网点尚未开始预约")
    return
end

ngx.log(ngx.INFO, stock_key .. " , 当前库存: ", stock)

if tonumber(stock)<=0 then
    ngx.say("库存已空，已预约完毕，感谢参与");
    return
end