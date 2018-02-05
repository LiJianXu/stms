package com.jtdd.dao.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtdd.entity.Major;

@Repository
@Transactional
public class MajorDaoImpl extends BaseDAOImpl<Major> {

}
