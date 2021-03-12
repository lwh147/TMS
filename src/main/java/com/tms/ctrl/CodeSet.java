package com.tms.ctrl;

import javax.servlet.*;
import java.io.IOException;

public class CodeSet implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 在这里设置response的内容类型可能会导致引用的外部css文件加载失败并且还可能与action中返回的json数据冲突
		// response.setContentType("text/html;charset=UTF-8");
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
