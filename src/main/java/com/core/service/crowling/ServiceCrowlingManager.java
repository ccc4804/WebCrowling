package com.core.service.crowling;

import java.util.List;

public interface ServiceCrowlingManager {
	public List<String> getSearchGoogle(String findString);
	public List<String> getSearchNaver(String findString);
	public List<String> getAddressList(String findUrl);
	public void duplicateCheck(List<String> urlList, String urlElement);
	public List<String> getHtml(String query);
}
