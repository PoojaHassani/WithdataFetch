package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.model.Institute;
import com.lti.model.Scheme;
import com.lti.model.Student;

@Repository
public class InstituteDaoImpl implements InstituteDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public int createInstitute(Institute institute) {
		entityManager.persist(institute);
		System.out.println("Hello");
		return 1;
	}

	@Override
	public int loginInstitute(int instituteCode, String setPassword) {

		System.out.println(instituteCode + " " + setPassword);
		String jpql = "from Institute where instituteCode=:instituteCode and setPassword=:password ";
		TypedQuery<Institute> typedquery = entityManager.createQuery(jpql, Institute.class);
		typedquery.setParameter("instituteCode", instituteCode);
		typedquery.setParameter("password", setPassword);
		// Institute institute=null;

		// typedquery.setParameter("setPassword", setPassword);
		// institute =typedquery.getSingleResult();
		List<Institute> list = typedquery.getResultList();

		System.out.println("Size: " + list.size());

		// int code=institute.getInstituteCode();
		// String pass=institute.getSetPassword();
		return list.size();
	}

	@Override
	public int loginStudent(long aadharNo, String password) {
		String jpql = "Select s from Student s where s.aadharNo=:aadharNo ";
		TypedQuery<Student> typedquery = entityManager.createQuery(jpql, Student.class);
		typedquery.setParameter("aadharNo", aadharNo);
		// Student student = null;

		List<Student> list = typedquery.getResultList();
		// long code = student.getAadharNo();
		System.out.println("Size:" + list.size());
		return list.size();

	}

	@Override
	@Transactional
	public int createStudent(Student student) {
		entityManager.persist(student);
		System.out.println("student");
		return 1;
	}

	@Override
	@Transactional
	public int insertStudentScheme(Scheme scheme) {
		entityManager.persist(scheme);
		System.out.println("Scheme");
		return 1;

	}
	
	
	@Override
	public List<Institute> getAllInstitute() {
		String jpql = "From Institute";
		TypedQuery<Institute> typed = entityManager.createQuery(jpql, Institute.class);

		return typed.getResultList();
	}

	@Override
	public List<Student> getAllStudents() {
		String jpql="From Student";
		TypedQuery<Student> typed=entityManager.createQuery(jpql,Student.class);
		return typed.getResultList();
	}
	  
	 

}