package com.softdev.system.feign.controller;

import com.softdev.system.feign.util.ApiReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
	@Autowired
	DemoRemoteClient demoRemoteClient;
	
	@GetMapping("/remote/demo/getData/{uid}")
	public ApiReturnObject  basePath(@PathVariable String uid ,String data){
		return demoRemoteClient.getData(uid, data);
	}
}
