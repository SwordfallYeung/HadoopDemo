package cn.itcast.bigdata.log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * @author y15079
 * @create 2018-03-20 22:17
 * @desc
 **/
public class GenerateLog {
	public static void main(String[] args) throws Exception {
		Logger logger = LogManager.getLogger("testlog");
		int i = 0;
		while (true){
			logger.info(new Date().toString() + "--------------------");
			i++;
			Thread.sleep(500);
			if (i > 1000000)
				break;
		}
	}
}
