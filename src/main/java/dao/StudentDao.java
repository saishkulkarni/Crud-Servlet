package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.Student;

public class StudentDao 
{
EntityManagerFactory factory=Persistence.createEntityManagerFactory("dev");
EntityManager manager=factory.createEntityManager();
EntityTransaction transaction=manager.getTransaction();

public void saveStudent(Student student)
{
	transaction.begin();
	manager.persist(student);
	transaction.commit();
}

public List<Student> fetchAll()
{
	return manager.createQuery("select x from Student x").getResultList();
}

public Student fetchById(int id)
{
	return manager.find(Student.class,id);
}

public List<Student> fetchByMobile(long mobile)
{
	return manager.createQuery("select x from Student x where mobile=?1").setParameter(1, mobile).getResultList();		
}
}
