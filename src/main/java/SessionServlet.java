/*
 * Copyright 2014-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// tag::class[]
@WebServlet("/session")
public class SessionServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		/**
		 * 将tomcat关闭掉一次后,再重新启动下,看能否拿到数据,如果能,就说明将Session存入redis中成功,
		 * 如果没有则说明还没有成功
		 */
		System.out.println("redis中的信息是: " + req.getSession().getAttribute("hello"));

		String attributeName = req.getParameter("p");
		String attributeValue = req.getParameter("v");

		System.out.println(attributeName);
		System.out.println(attributeValue);

		req.getSession().setAttribute(attributeName, attributeValue);
//		resp.sendRedirect(req.getContextPath() + "/");

		req.getRequestDispatcher("/").forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	private static final long serialVersionUID = 2878267318695777395L;
}

