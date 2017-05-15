package com.shhege.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shhege.core.util.id.IdGen;
import com.shhege.madel.po.CustomerNotePo;
import com.shhege.service.CustomerNoteService;
import com.shhege.service.dao.CustomerNoteDao;

@Service("customerNoteService")
public class CustomerNoteServiceImpl implements CustomerNoteService {
	
	@Autowired
	private CustomerNoteDao customerNoteDao;

	@Override
	public List<CustomerNotePo> getAllNoteByCostomerId(String costomerId) {
		return customerNoteDao.findCustomerNotesByConstomerId(costomerId);
	}

	@Override
	public int insertNote(CustomerNotePo customerNotePo) {
		customerNotePo.setId(IdGen.uuid());
		customerNotePo.setRegisterId("admin");
		customerNotePo.setNoteTime(new Date());
		return customerNoteDao.insertContactInfo(customerNotePo);
	}
	

}
