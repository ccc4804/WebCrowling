package com.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.entitiy.Disease;
import com.core.entitiy.Keyword;
import com.core.model.ModelDisease;
import com.core.repository.RepositoryDisease;
import com.core.repository.RepositoryKeyword;

@Service
public class ServicefindKeyImpl implements ServicefindKey {
	@Autowired
	RepositoryKeyword repositoryKeyword;
	@Autowired
	RepositoryDisease repositoryDisease;

	@Override
	public String getSearchNaver(String findString) {
		try {
			String url = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=" + findString;
			Document doc = Jsoup.connect(url).get();
			// Elements element = doc.select("http");

			// String title = element.select("h2").text();

			System.out.println(doc.toString());
			return doc.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "false";
		}
	}

	@Override
	public List<String> getSearchGoogle(String findString) {

		List<String> urlList = new ArrayList<String>();
		String exceptionString = "";

		try {
			String url = "https://www.google.com/search?q=" + findString + "&oq=" + findString
					+ "&aqs=chrome..69i57.1587j0j1&sourceid=chrome&ie=UTF-8";
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
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(exceptionString);
			return urlList;
		}
	}

	@Override
	public ModelDisease getDisease(String query) {
		// TODO Auto-generated method stub
		ModelDisease modelDisease;
		try {
			Disease disease = repositoryDisease.findByDisease(query);
			if (disease == null) {
				Keyword keyword = repositoryKeyword.findByKeyword(query);
				if (keyword == null) {
					repositoryKeyword.save(new Keyword(query, "Y"));
				}
				return null;
			} else {
				modelDisease = new ModelDisease(disease);
				return modelDisease;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return null;
		}
	}

}
