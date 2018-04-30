package cn.itcast.bigdata.hadooprpc.service;

import cn.itcast.bigdata.hadooprpc.protocol.IUserLoginService;

/**
 * @author y15079
 * @create 2018-03-20 15:23
 * @desc
 **/
public class UserLoginServiceImpl implements IUserLoginService{
	public String login(String name, String passwd) {
		return name + " logged in successfully...";
	}
}
