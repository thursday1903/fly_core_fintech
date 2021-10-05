package springboot.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.config.YAMLConfig;
import springboot.logs.Logs;
import springboot.utils.Commons;

/**
 * May 10, 2021-11:37:23 AM
 * @author Vietanh
 * Controller viết các api xử lý nghiệp vụ
 */
@RestController
@RequestMapping("bussiness")
public class Controller {

	@Autowired
	private YAMLConfig myConfig;

	public Controller() {
		// TODO Auto-generated constructor stub
		
	}

	private static final String template = "%s";
	static Logs log = new Logs(Controller.class);

	@RequestMapping(value = "api/", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(HttpServletRequest request, @RequestBody String payload) {
		String clientIp = Commons.getClientIp(request);

		return null;
	}
	
	
		

	public static void main(String[] args) {
		// int partnerId = 100;
		// RequestResponseObj requestResponseObj = new RequestResponseObj();
		// requestResponseObj.setCardPin("1234567890");
		// requestResponseObj.setCardSerial("12345678900");
		// requestResponseObj.setPartnerId(1001);
		// requestResponseObj.setPartner_username("dev_test");
		// requestResponseObj.setProviderCode("VNP");
		// requestResponseObj.setRemark("Thanh toan");
		// requestResponseObj.setRequestId(1001 + "_" +
		// System.currentTimeMillis());
		// String data = GsonUltilities.toJson(requestResponseObj);
		// System.out.println(data);
		// System.out.println(URLEncoder.encode(GsonUltilities.toJson(data)));

	}
}
