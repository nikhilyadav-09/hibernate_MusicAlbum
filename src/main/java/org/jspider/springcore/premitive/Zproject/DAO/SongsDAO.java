package org.jspider.springcore.premitive.Zproject.DAO;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jspider.springcore.premitive.Zproject.DTO.SongDTO;

public class SongsDAO {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static Scanner sc = new Scanner(System.in);

	public static void openconnection() {
		factory = Persistence.createEntityManagerFactory("hibernate");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	}

	public static void closeConnection1() {
		factory.close();
		manager.close();
		try {
			transaction.rollback();
		} catch (Exception e) {
			System.out.println("Transaction is committed");
		}
	}
	
	public   void addSong() {
		System.out.println("How many song do you add:");
		
		int a=sc.nextInt();
		
		for(int i=1; i<=a; i++) {
			transaction.begin();
			SongDTO song=new SongDTO();
			
			System.out.println("Add a song name:"+i);
			sc.nextLine();
			song.setName(sc.nextLine());
			
			System.out.println("Add a song duration:");
			song.setLength(sc.nextDouble());
			
			System.out.println("Add a singer name:");
			song.setSinger(sc.next());
			
			manager.persist(song);
			transaction.commit();
		}
		
		
		System.out.println("Songs updated Successfully");
	}

	public void viewAllSongs() {
		transaction.begin();
		String jpql="select name from SongDTO";
		
		Query query=manager.createQuery(jpql);
		List resultList=query.getResultList();
		for(Object object:resultList) {
			System.out.println(object);
		}
		System.out.println("{View all songs name...!!!}");
	}
	
	public void playAllSonds() {
		transaction.begin();
		//String jpql="select count(*) from SongDTO";
		String jpql1="select id from SongDTO";
		
		//Query query=manager.createQuery(jpql);
		Query query1=manager.createQuery(jpql1);
		
		//List resultList=query.getResultList();
		List resultList1=query1.getResultList();
		
		for(Object object:resultList1) {
			SongDTO song=manager.find(SongDTO.class, object);
			System.out.println(object);
			
			String s=song.getName();
			for(int i=0; i<s.length(); i++) {
				char c=s.charAt(i);
				try {
					Thread.sleep(500);
				}catch(InterruptedException e){
					Thread.currentThread().interrupt();
				}
				
				System.out.print(c);
			}
			System.out.println("..is playing!!");
		}
	}

	public static void main(String[] args) {
	
		openconnection();
		SongsDAO s2=new SongsDAO();
		//s2.addSong();
		//s2.viewAllSongs();
		s2.playAllSonds();
		
		
	}
}
