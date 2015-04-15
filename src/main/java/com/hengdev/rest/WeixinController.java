package com.hengdev.rest;

import com.hengdev.pojo.ReceiveMessage;
import com.hengdev.util.SignUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Writer;

@RestController
@RequestMapping("/weixin")
public class WeixinController {

    @RequestMapping(value = "/receiveMsg", method = RequestMethod.GET)
    public void valid(@RequestParam("signature") String signature,
                        @RequestParam("timestamp") String timestamp,
                        @RequestParam("nonce") String nonce,
                        @RequestParam("echostr") String echostr, Writer writer) throws Exception {
        // 通过检验 signature 对请求进行校验，若校验成功则原样返回 echostr，表示接入成功，否则接入失败
        if(SignUtil.checkSignature(signature, timestamp, nonce)){
            writer.write(echostr);
        }
    }

    @RequestMapping(value = "/receiveMsg", method = RequestMethod.POST,
        consumes = {MediaType.APPLICATION_XML_VALUE})
    public ReceiveMessage responseMsg(@RequestBody ReceiveMessage msg) throws Exception {
        //writer.write(msg.getToUserName());
        return msg;
    }
}
