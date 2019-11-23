package com.core.service.crowling;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class ServiceCrowlingManagerImpl implements ServiceCrowlingManager {
	private final static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

	@SuppressWarnings("finally")
	public List<String> getAddressList(String findUrl) {

		List<String> urlList = new ArrayList<String>();
		// TODO: 삭제 필요
		String exceptionString = "";
		String tempUrl="";
		boolean stopFlag = false;

		try {
			Connection conn = Jsoup.connect(findUrl).header("Content-Type", "application/json;charset=UTF-8")
					.userAgent(USER_AGENT).method(Connection.Method.GET).ignoreContentType(true);

			Document doc = conn.get();

			String[] docSplit = doc.toString().split("\n");

			for (String currentLine : docSplit) {
				// TODO: 삭제 필요
				exceptionString = currentLine;
				// 검색 결과 페이지가 있는지 확인
				int countStopMessage = StringUtils.countMatches(currentLine, "검색결과가 없습니다.");
				if (countStopMessage > 0) {
					stopFlag = true;
					break;
				}

				// url 주소가 들어가 있는 <a 태그 필터
				int countAHalf = StringUtils.countMatches(currentLine, "<a href");
				if (countAHalf > 0) {
					int countH3 = StringUtils.countMatches(currentLine, "LC20lb");
					int indexHttp = currentLine.indexOf("http");
					if (indexHttp != -1) {
						currentLine = currentLine.substring(indexHttp);
						int indexDobleDot = currentLine.indexOf("\"");
						if (indexDobleDot != -1) {
							tempUrl = currentLine.substring(0, indexDobleDot);
						}else {
							tempUrl = currentLine;
						}	
						
						if(countH3 > 0) {
							duplicateCheck(urlList, tempUrl);
						}
					}
				}
			}
			if (stopFlag) {
				urlList = new ArrayList<String>();
				throw new Exception("No search results.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(exceptionString);
		} finally {
			return urlList;
		}
	}

	@Override
	public List<String> getSearchNaver(String findString) {
		String url = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=" + findString;
		return getAddressList(url);
	}

	@Override
	public List<String> getSearchGoogle(String findString) {
		List<String> finalList = new ArrayList<String>();

		for (int page = 0; page <= 50; page += 10) {
			String url = "https://www.google.com/search?q=" + findString + "&oq=" + findString
					+ "&aqs=chrome..69i57.1587j0j1&sourceid=chrome&ie=UTF-8&start=" + page;
			finalList = ListUtils.union(finalList, getAddressList(url));
		}
		return finalList;
	}

	@Override
	public void duplicateCheck(List<String> urlList, String urlElement) {
		// 중복 체크
		if (!urlList.contains(urlElement)) {
			urlList.add(urlElement);
		}
	}

	@SuppressWarnings("finally")
	@Override
	public List<String> getHtml(String query) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		try {
			String url = "https://www.google.com/search?q=" + query + "&oq=" + query
					+ "&aqs=chrome..69i57.1587j0j1&sourceid=chrome&ie=UTF-8&start=0";
			Connection conn = Jsoup.connect(url).header("Content-Type", "application/json;charset=UTF-8")
					.userAgent(USER_AGENT).method(Connection.Method.GET).ignoreContentType(true);

			Document doc = conn.get();
			
			String[] arrayDoc = doc.toString().split("\n");
			
			for(String str:arrayDoc) {
				list.add(str);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			return list;
		}
	}
}
