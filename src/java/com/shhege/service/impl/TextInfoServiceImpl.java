package com.shhege.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shhege.core.util.id.IdGen;
import com.shhege.madel.po.TextInfoPo;
import com.shhege.service.TextInfoService;
import com.shhege.service.dao.TextInfoDao;

@Service("textInfoService")
public class TextInfoServiceImpl implements TextInfoService {
	
	@Autowired
	private TextInfoDao textInfoDao;

	@Override
	public String getText(String textId) {
		return textInfoDao.findContent(textId);
	}

	@Override
	public String insertText(TextInfoPo textInfoPo) {
		textInfoPo.setId(IdGen.uuid());
		textInfoPo.setTextId(IdGen.uulong());
		int i = textInfoDao.insert(textInfoPo);
		if(i == 1) {
			return textInfoPo.getTextId();
		} else {
			return "";
		}
	}
}
