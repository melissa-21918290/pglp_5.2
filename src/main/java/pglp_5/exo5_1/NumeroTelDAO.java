package pglp_5.exo5_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;



public class NumeroTelDAO extends DAOApp<NumeroTel> {

	public NumeroTelDAO(int id) throws IOException {
		super(id);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public NumeroTel create(NumeroTel obj) throws IOException {
		// TODO Auto-generated method stub
		objOut.writeObject(obj);
		return obj;
	}

	@Override
	public void delete(NumeroTel obj) {
		// TODO Auto-generated method stub
		f.delete();

	}

	@Override
	public NumeroTel update(NumeroTel obj) throws IOException {
		// TODO Auto-generated method stub
		f.delete();
		this.create(obj);
		return obj;
	}

	@Override
	public NumeroTel find(String string) throws FileNotFoundException, ClassNotFoundException, IOException {
		File search = new File( ".txt");
        Object deserialized = null;
        if (search.exists()) {
            byte[] fileContent = Files.readAllBytes(search.toPath());
            deserialized = deserialize(fileContent);
        }
        NumeroTel numero = (NumeroTel) deserialized;
        System.out.println(numero.toString());
        return numero;

}}
