package springboot.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.logs.Logs;
import springboot.utils.Commons;

@RestController
@RequestMapping("auth")
public class AuthController {

//	@Autowired
//	private YAMLConfig myConfig;

	public AuthController() {
		// TODO Auto-generated constructor stub
		
	}

	private static final String template = "%s";
	static Logs log = new Logs(AuthController.class);

	@RequestMapping(value = "api/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(HttpServletRequest request, @RequestBody String payload) {
		String clientIp = Commons.getClientIp(request);

		return null;
	}
	
	@RequestMapping(value = "api/changepass", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String changePass(HttpServletRequest request, @RequestBody String payload) {
		String clientIp = Commons.getClientIp(request);

		return null;
	}


	@RequestMapping(value = "api/resetpassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String resetPass(HttpServletRequest request, @RequestBody String payload) {
		String clientIp = Commons.getClientIp(request);

		return null;
	}

	public Map<String, String> parsePostBody(String input) {
		try {
			Map<String, String> mapParams = new HashMap<>();
			String[] nameValuePairs = input.split("&");
			for (String nameValuePair : nameValuePairs) {
				String[] entity = nameValuePair.trim().split("=");
				mapParams.put(entity[0], entity[1]);
			}
			return mapParams;
		} catch (Exception e) {
			// TODO: handle exception
			log.fatal("parsePostBody", e);
		}
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
