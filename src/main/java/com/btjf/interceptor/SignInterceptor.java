package com.btjf.interceptor;

import com.btjf.application.util.XaResult;
import com.btjf.common.utils.JSONUtils;
import com.btjf.util.AESUtils;
import com.btjf.util.ParamsUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限过滤器
 *
 * @author dapeng
 *
 */
public class SignInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(SignInterceptor.class);

	public static final String ACCESS_TOKEN = "AccessKey";

	public static final String ACCESS_TOKEN_LLC_SBSB = "cheok-llc-shoubashouba";

    public static final String SECRETKEY = "SecretKey";

    public static final String SECRETKEY_SBSB = "2e6ccaabe2f429bf";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String accessToken = request.getHeader(ACCESS_TOKEN);
		String secretKey = request.getHeader(SECRETKEY);
		Map<String, String> paraMap = getMap(request);
		if(StringUtils.isEmpty(accessToken) || !ACCESS_TOKEN_LLC_SBSB.equals(accessToken)){
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("text/xml;charset=UTF-8");
			response.getOutputStream().write(JSONUtils.toJSONByJackson(XaResult.unlogin()).getBytes("UTF-8"));
			return false;
		}
		if(StringUtils.isEmpty(secretKey) || !secretKey.equals(ParamsUtil.sign(paraMap, SECRETKEY_SBSB))){
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.setContentType("text/xml;charset=UTF-8");
			response.getOutputStream().write(JSONUtils.toJSONByJackson(XaResult.noAuthority()).getBytes("UTF-8"));
			return false;
		}
		return true;
	}

	private Map<String,String> getMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		Enumeration en = request.getParameterNames();
		while (en.hasMoreElements()) {
			String paramName = (String) en.nextElement();
			String paramValue = request.getParameter(paramName);
			//形成键值对应的map
			map.put(paramName, paramValue);
		}
		return map;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}


}
