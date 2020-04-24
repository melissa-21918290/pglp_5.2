package pglp_5.exo5_1;

import java.time.LocalDate;


import pglp_5.exo5_1.Personnel.Builder;



public enum Singleton {
	ENVIRONNEMENT;
	
	public void run(String[] args) {
		NumeroTel portable = new NumeroTel("portable","0651624519");
		Builder b = new Builder("bafdel","melissa", "secrétaire", LocalDate.of(1995,4,1));
		b.numTelephones(portable);
		Personnel secretaire = b.build();
		
		NumeroTel portable2 = new NumeroTel("portable","0651424519");
		Builder b2 = new Builder("ailam","lyza", "chef de service", LocalDate.of(1994,8,27));
		b2.numTelephones(portable2);
		Personnel chefDeService = b2.build();
		
		NumeroTel portable3 = new NumeroTel("portable","0631624519");
		Builder b3 = new Builder("hadj","amel", "employe", LocalDate.of(1993,6,15));
		b3.numTelephones(portable3);
		Personnel employe = b3.build();
		
		NumeroTel portable4 = new NumeroTel ("portable","0699624519");
		Builder b4 = new Builder("amroun","samy", "employe", LocalDate.of(1997,5,13));
		b4.numTelephones(portable4);
		Personnel employe2 = b4.build();
		
		NumeroTel portable5 = new NumeroTel("portable","0611624519");
		Builder b5 = new Builder("allache","rabah", "chef du departement", LocalDate.of(1990,05,03));
		b5.numTelephones(portable5);
		Personnel chefDepartement =  b5.build();
		
		NumeroTel portable6 = new NumeroTel("portable","0611624919");
		Builder b6 = new Builder("alim","amine", "chef d'équipe", LocalDate.of(1989,07,16));
		b6.numTelephones(portable6);
		Personnel chefEquipe = b6.build();
		
		GroupePersonnel Departement = new GroupePersonnel("\n==Departement==");
		GroupePersonnel Service = new GroupePersonnel("==Service==");
		GroupePersonnel Equipe1 = new GroupePersonnel("==Equipe1==");
		GroupePersonnel Equipe2 = new GroupePersonnel("==Equipe2==");
		
		Equipe1.add(chefEquipe);
		Equipe1.add(employe);
		Equipe2.add(employe2);
		
		Service.add(chefDeService);
		Service.add(chefEquipe);
		Service.add(employe);
		Service.add(employe2);
		Service.add(Equipe1);
		Service.add(Equipe2);
		
		Departement.add(chefDeService);
		Departement.add(secretaire);
		Departement.add(employe);
		Departement.add(employe2);
		Departement.add(chefDepartement);
		Departement.add(chefEquipe);
		Departement.add(Service);
		
		Departement.print();
		
	}
	
	public static void main(String[] args) {
		ENVIRONNEMENT.run(args);
	}
}