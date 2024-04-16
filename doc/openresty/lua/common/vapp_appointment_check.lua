--[[
    ԤԼУ��
]]

local cache = require "wangan.common.share_cache"

local json = require "cjson"

--�ȶ�request body
ngx.req.read_body()
--��request body���ȡ����
--local args = ngx.req.get_post_args()


--��ȡrequest body data
local request_body_data = ngx.req.get_body_data()

if not request_body_data then
    ngx.say("request body is nil")
    return
end

ngx.log(ngx.INFO, "request body string", request_body_data)
--��request body data����Ϊjson
local request_body_json = json.decode(request_body_data)

--ngx.log(ngx.INFO, "request body json", request_body_json)


--�������У��
local outletId = request_body_json.outletId

if not outletId then
    ngx.say("����id����Ϊ��")
    return
end


--���ʣ��У��
local stock_key = "outlet:" .. outletId .. ":date:" .. ngx.today() .. ":stock"
ngx.log(ngx.INFO, "stock_key ", stock_key)
local stock = cache.get_from_cache(stock_key);

if not stock then
    ngx.say("δ�鵽��棬��������δ��ʼԤԼ")
    return
end

ngx.log(ngx.INFO, stock_key .. " , ��ǰ���: ", stock)

if tonumber(stock)<=0 then
    ngx.say("����ѿգ���ԤԼ��ϣ���л����");
    return
end