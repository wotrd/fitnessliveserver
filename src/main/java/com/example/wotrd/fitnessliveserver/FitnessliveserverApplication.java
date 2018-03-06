package com.example.wotrd.fitnessliveserver;

import com.example.wotrd.fitnessliveserver.manager.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.socket.WebSocketSession;
import java.util.HashMap;
import java.util.Map;

@ServletComponentScan
@SpringBootApplication
public class FitnessliveserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessliveserverApplication.class, args);
	}
	//存放直播用户和观众会话
	public static Map<String,Map<String,WebSocketSession>> sessionMap = new HashMap();
	//存放观众信息
	public static Map<String,Map<String ,User>> watchUserInfo = new HashMap();
}
