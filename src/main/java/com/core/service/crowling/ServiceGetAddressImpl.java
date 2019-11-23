package com.core.service.crowling;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class ServiceGetAddressImpl implements ServiceGetAddress {

	@Override
	public List<String> getSearchNaver(String findString) {

		List<String> urlList = new ArrayList<String>();
		String exceptionString = "";

		try {
			String url = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=" + findString;
			Document doc = Jsoup.connect(url).get();

			String[] docSplit = doc.toString().split("\n");

			for (String currentLine : docSplit) {
				exceptionString = currentLine;
				int countAHalf = StringUtils.countMatches(currentLine, "<a href");
				int countHttp = StringUtils.countMatches(currentLine, "http");
				if (countAHalf > 0 && countHttp > 0) {
					for (int i = 0; i < countHttp; i++) {
						int indexHttp = currentLine.indexOf("http");
						if (indexHttp != -1) {
							currentLine = currentLine.substring(indexHttp);
							int indexDobleDot = currentLine.indexOf("\"");
							// 마지막 http 체크
							if (i == countHttp - 1) {
								urlList.add(currentLine.substring(0, indexDobleDot));
								if (indexDobleDot != -1) {
									urlList.add(currentLine.substring(0, indexDobleDot));
								} else {
									urlList.add(currentLine);
								}
							}

							if (indexDobleDot != -1) {
								urlList.add(currentLine.substring(0, indexDobleDot));
							}
						}
					}
				}
			}
			return urlList;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(exceptionString);
			return urlList;
		}
	}

	@Override
	public List<String> getSearchGoogle(String findString) {

		List<String> urlList = new ArrayList<String>();
		// TODO: 없어질 예정
		String exceptionString = "";
		boolean stopFlag = false;

		try {
			for (int page = 0; page <= 50; page += 10) {
				// 검색 url
				String url = "https://www.google.com/search?q=" + findString + "&oq=" + findString
						+ "&aqs=chrome..69i57.1587j0j1&sourceid=chrome&ie=UTF-8&start=" + page;
				Document doc = Jsoup.connect(url).get();

				String[] docSplit = doc.toString().split("\n");

				for (String currentLine : docSplit) {
					// TODO: 삭제 필요 
					exceptionString = currentLine;
					// 검색 결과 페이지가 있는지 확인
					int countStopMessage = StringUtils.countMatches(currentLine, "검색결과가 없습니다.");
					if(countStopMessage > 0) {
						stopFlag = true;
						break;
					}
					
					// url 주소가 들어가 있는 <a 태그 필터
					int countAHalf = StringUtils.countMatches(currentLine, "<a href");
					// http 주소 필터
					int countHttp = StringUtils.countMatches(currentLine, "http");
					if (countAHalf > 0 && countHttp > 0) {
						for (int i = 0; i < countHttp; i++) {
							int indexHttp = currentLine.indexOf("http");
							if (indexHttp != -1) {
								currentLine = currentLine.substring(indexHttp);
								int indexDobleDot = currentLine.indexOf("\"");
								// 마지막 http 체크
								if (i == countHttp - 1) {
									urlList.add(currentLine.substring(0, indexDobleDot));
									if (indexDobleDot != -1) {
										urlList.add(currentLine.substring(0, indexDobleDot));
									} else {
										urlList.add(currentLine);
									}
								}

								if (indexDobleDot != -1) {
									urlList.add(currentLine.substring(0, indexDobleDot));
								}
							}
						}
					}
				}
				
				if(stopFlag) {
					break;
				}
			}
			return urlList;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(exceptionString);
			return urlList;
		}
	}
}
