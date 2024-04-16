package com.zy.seckill.sys.controller;

import com.zy.seckill.common.bo.vo.ResultVo;
import com.zy.seckill.common.utils.ConvertCode;
import com.zy.seckill.common.utils.Crc16Util;
import com.zy.seckill.common.utils.ObjUtil;
import com.zy.seckill.common.utils.StrUtils;
import com.zy.seckill.sys.bo.dto.ModbusOrderReqDto;
import com.zy.seckill.sys.bo.dto.ModusReadReqDto;
import com.zy.seckill.sys.nettySocket.comon.ChannelMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/inventory/modbus")
@Api(tags = "通过modbus协议和设备实现交互")
@Validated
@RequiredArgsConstructor
@Slf4j
public class ModbusController {


    @ApiOperation("读取指定设备寄存器中的数据")
    @PostMapping(value = "getDataBySerialNum", produces = { "application/json" })
    public ResultVo<Map<String, Object>> getDataBySerialNum(
            @Validated @RequestBody ModusReadReqDto modusReadReqDto
    ){
        //1.从机地址
        String address = "01";
        //2.功能码，读取寄存器
        String code = "03";
        //3.根据设备寄存器定义表, 0000 代表距离/物位瞬时值(2字节(1个位16进制等于4位2进制数，所以0000*4=16位二进制数，1字节=8位二进制数，所以0000=2字节),高位在前低位在后)，即高位为00(前两位)，低位为00(后两位)
        //寄存器开始位置(高位在前(00)，低位在后(00))
        String[] start = {modusReadReqDto.getRegisterAddrHigh(), modusReadReqDto.getRegisterAddrLow()};
        //4.要读取的寄存器数量为1位，即0001，高位在前(00)，低位在后(01)
        String[] length = {"00", "01"};
        //5.计算CRC，得出完整消息
        byte[] data = Crc16Util.getData(address, code, start[0], start[1], length[0], length[1]);
        String msg = Crc16Util.byteTo16String(data).replaceAll(" ", "");

        log.info("msg=====> {}",msg);

        String serialNum = modusReadReqDto.getSerialNum();
        Channel channel = ChannelMap.getChannelByName(serialNum);
        if(ObjUtil.isEmpty(channel)){
            throw new IllegalArgumentException("序列号为:"+serialNum+"的设备不在线!");
        }
        if(!channel.isActive()){
            throw new IllegalArgumentException("序列号为:"+serialNum+"的设备不在线!!");
        }

        //向设备读取数据
        writeToClient(msg,channel);


        Map<String, Object> map = new HashMap<>();
        map.put("msg","success");
        return ResultVo.ok(map);
    }


    @ApiOperation("向设备下发指令")
    @PostMapping(value = "setOrder", produces = { "application/json" })
    public ResultVo<Map<String, Object>> setOrder(
            @Validated @RequestBody ModbusOrderReqDto modbusOrderReqDto
    ){

        String serialNum = modbusOrderReqDto.getSerialNum();
        Channel channel = ChannelMap.getChannelByName(serialNum);
        if(ObjUtil.isEmpty(channel)){
            throw new IllegalArgumentException("序列号为:"+serialNum+"的设备不在线!");
        }
        if(!channel.isActive()){
            throw new IllegalArgumentException("序列号为:"+serialNum+"的设备不在线!!");
        }
        String order = modbusOrderReqDto.getOrder();

        //将指令按每2位分割
        List<String> stringList = StrUtils.digitSegStr(order, 2);

        //将列表转为数组
        String[] array = stringList.toArray(new String[stringList.size()]);

        //进行crc16加密
        byte[] data = Crc16Util.getData(array);
        String msg = Crc16Util.byteTo16String(data).replaceAll(" ", "");

        //下发指令
        writeToClient(msg,channel);


        Map<String, Object> map = new HashMap<>();
        map.put("msg","success");
        return ResultVo.ok(map);
    }

    private void writeToClient(final String receiveStr, Channel channel) {
        try {
            // netty需要用ByteBuf传输
            ByteBuf bufff = Unpooled.buffer();
            // 对接需要16进制
            bufff.writeBytes(ConvertCode.hexString2Bytes(receiveStr));
            channel.writeAndFlush(bufff).addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    log.info("向modbus设备写入数据成功,Channel_Id:{}, data:{}" ,channel.id().asLongText(), receiveStr);
                } else {
                    log.info("向modbus设备写入数据失败,Channel_Id:{}, data:{}" ,channel.id().asLongText(), receiveStr);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用通用writeToClient()异常" + e.getMessage());
        }
    }
}