/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.controller.filters;

import com.hallocasa.commons.constants.SystemConstants;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.model.session.WebSessionImpl;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This filter is intended to control access to pages to prevent required
 * authorization page is accessed without user logged session
 *
 * @author David Mantilla
 */
public class AccessFilter implements Filter {

	static Map<String, String> blogSectionMap;

	static {
		createBlogSectionParams();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}
	
	@Inject
	private WebSession webSession;

	private static void createBlogSectionParams() {
		blogSectionMap = new HashMap<String, String>();
		blogSectionMap.put("article-14-en", "how-colombia-has-changed-to-a-stable-investment-opportunity");
		blogSectionMap.put("article-14-es", "como-colombia-ha-cambiado-a-una-oportunidad-de-inversion-estable");
		blogSectionMap.put("article-14-de", "wie-sich-kolumbien-zu-einer-stabilen-investitionsregion-entwickelt-hat");

		blogSectionMap.put("article-18-en", "20-insights-about-launching-a-company-in-medellin-colombia");
		blogSectionMap.put("article-18-es", "20-ideas-sobre-el-lanzamiento-de-una-empresa-en-medellin-colombia");
		blogSectionMap.put("article-18-de", "20-tipps-fur-die-grundung-einer-firma-in-medellin-kolumbien");

		blogSectionMap.put("article-20-en", "how-bogotas-real-estate-skyline-will-transform-in-the-coming-years");
		blogSectionMap.put("article-20-es", "como-la-escala-de-fincaraiz-en-bogota-cambiara-en-los-proximos-anos");
		blogSectionMap.put("article-20-de", "wie-sich-bogotas-skyline-in-den-kommenden-jahren-verandern-wird");

		blogSectionMap.put("article-21-en", "colombia-quiz");
		blogSectionMap.put("article-21-es", "concurso-colombiano-2");
		blogSectionMap.put("article-21-de", "kolumbien-quizz-2");

		blogSectionMap.put("article-22-en", "agriculture-and-real-estate-in-cali-colombia");
		blogSectionMap.put("article-22-es", "agricultura-y-finca-raiz-en-cali-colombia");
		blogSectionMap.put("article-22-de", "agrikultur-und-immobilien-in-cali-kolumbien");

		blogSectionMap.put("article-23-en", "mompox-far-from-everything-or-at-the-center-of-nothing");
		blogSectionMap.put("article-23-es", "mompox-lejos-de-todo-o-en-el-centro-de-nada");
		blogSectionMap.put("article-23-de", "mompox-abseits-von-allem-oder-im-zentrum-von-nichts");

		blogSectionMap.put("article-24-en", "how-to-obtain-high-roi-on-real-estate-in-bogota-colombia");
		blogSectionMap.put("article-24-es",
				"como-obtener-un-alto-retorno-de-inversion-en-inmuebles-en-bogota-colombia");
		blogSectionMap.put("article-24-de", "wie-man-eine-hohe-rendite-auf-immobilien-in-bogota-kolumbien-erhalt");

		blogSectionMap.put("buyprocess-content-en", "buying-process-real-estate-colombia");
		blogSectionMap.put("buyprocess-content-es", "proceso-de-compra-fincaraiz-colombia");
		blogSectionMap.put("buyprocess-content-de", "kaufprozess-immobilien-kolumbien");

		blogSectionMap.put("buyprocess-faq-en", "buying-process-real-estate-colombia");
		blogSectionMap.put("buyprocess-faq-es", "proceso-de-compra-fincaraiz-colombia");
		blogSectionMap.put("buyprocess-faq-de", "kaufprozess-immobilien-kolumbien");

		blogSectionMap.put("links-en", "buying-process-real-estate-colombia");
		blogSectionMap.put("links-es", "proceso-de-compra-fincaraiz-colombia");
		blogSectionMap.put("links-de", "kaufprozess-immobilien-kolumbien");
		
		blogSectionMap.put("buyprocess-downloads-en", "downloads-real-estate-colombia");
		blogSectionMap.put("buyprocess-downloads-es", "descargas-fincaraiz-colombia");
		blogSectionMap.put("buyprocess-downloads-de", "downloads-dokumente-immobilien-kolumbien");
		
		blogSectionMap.put("default-en", "real-estate-marketplace-colombia");
		blogSectionMap.put("default-es", "articulos-fincaraiz-colombia");
		blogSectionMap.put("default-de", "artikel-immobilien-kolumbien");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		StringBuffer requestURL = httpRequest.getRequestURL();
		String contextPath = httpRequest.getContextPath();
		HallocasaViewEnum viewEnum;
		String serverAppUrl = SystemConstants.SERVER_URL + SystemConstants.APP_CONTEXT;
		// if is a resource skip filter
		String urlPath = requestURL.toString().replaceAll(serverAppUrl, "");
		if (isBlogUrl(urlPath) || isBuyProcessUrl(urlPath) || isLinksUrl(urlPath)) {
			redirectToBlogSubDomain(request, response);
		}
		if (!isResource(urlPath) && (!isDefaultView(urlPath))) {
			// look for the page in the enum
			try {
				viewEnum = HallocasaViewEnum.findByUrl(urlPath);
			} catch (IllegalArgumentException e) {
				redirectTo(response, contextPath, HallocasaViewEnum.PAGE_NOT_FOUND);
				return;

			}

			// check if the view requires logged session
			if (viewEnum.isRequiresLogin()) {
				if ((WebSessionImpl.getCurrent() == null) || (!WebSessionImpl.getCurrent().isLogged())) {
					redirectTo(response, contextPath, HallocasaViewEnum.FORBIDDEN);
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	private void redirectToBlogSubDomain(ServletRequest request, ServletResponse response) {
		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		StringBuffer requestURL = httpRequest.getRequestURL();
		String contextPath = "http://blog.hallocasa.com/";
		String serverAppUrl = SystemConstants.SERVER_URL + SystemConstants.APP_CONTEXT;
		// if is a resource skip filter
		String urlPath = requestURL.toString().replaceAll(serverAppUrl, "");

		String lang = request.getParameter("lang");
		lang = (lang == null) ? webSession.getCurrentLanguage().toString() : lang;
		String languageId = (lang == null) ? "en" : lang;
		String pageResoluted = null;

		if (urlPath.contains("blog")) {
			String articleId = request.getParameter("article");
			String strToSearch = "article-" + articleId + "-" + languageId;
			pageResoluted = blogSectionMap.get(strToSearch);
		}
		else if (urlPath.contains("buyprocess")) {
			String optionId = request.getParameter("option");
			optionId = (optionId == null) ? "content" : optionId;
			String strToSearch = "buyprocess-" + optionId + "-" + languageId;
			pageResoluted = blogSectionMap.get(strToSearch);
		}
		else if (urlPath.contains("links")) {
			String strToSearch = "links-" + languageId;
			pageResoluted = blogSectionMap.get(strToSearch);
		}
		if(pageResoluted == null){
			pageResoluted = "";
		}
		try {
			String spageToRedirect =  languageId + "/" + pageResoluted;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(contextPath + spageToRedirect);
		} catch (IOException ex) {
			Logger.getLogger(AccessFilter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private boolean isBlogUrl(String urlPath) {
		return urlPath.contains("pages/blog/");
	}

	private boolean isBuyProcessUrl(String urlPath) {
		return urlPath.contains("pages/buyprocess/");
	}
	
	private boolean isLinksUrl(String urlPath) {
		return urlPath.contains("pages/links/");
	}

	@Override
	public void destroy() {
		//
	}

	/**
	 * Redirects to a Hallocasa view
	 *
	 * @param response
	 * @param hallocasaViewEnum
	 */
	private void redirectTo(ServletResponse response, String contextPath, HallocasaViewEnum hallocasaViewEnum) {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		try {
			httpResponse.sendRedirect(contextPath + hallocasaViewEnum.getUrl());
		} catch (IOException ex) {
			Logger.getLogger(AccessFilter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Evaluates if the URL is a resource
	 *
	 * @param url
	 * @return
	 */
	private boolean isResource(String url) {
		return url.startsWith("/javax.faces.resource");
	}

	/**
	 * Evaluates if the URL is the default view
	 *
	 * @param url
	 * @return
	 */
	private boolean isDefaultView(String url) {
		return url.equals("/") || (url.equals(HallocasaViewEnum.DEFAULT_VIEW.getUrl()));
	}

}
