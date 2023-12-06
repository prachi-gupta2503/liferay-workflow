package com.adj.project.management.dynamic.include;

import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		service = DynamicInclude.class
	)
public class BlogsDynamicInclude implements DynamicInclude {

	@Override
	public void include(HttpServletRequest request, HttpServletResponse response, String key)
			throws IOException {
		PrintWriter printWriter = response.getWriter();

		printWriter.println(
			"<h2>MY Login Page!</h2>"
			+ "<h5>Updated by Dynamic include</h5>"
			+ "<br />");
	}

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
		dynamicIncludeRegistry.register(
				"com.liferay.login.web#/login.jsp#alertPost");
		}
	

}
