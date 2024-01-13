package servidor;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import models.Message;

public class SurchParkDespachante {
	
	@SuppressWarnings("deprecation")
	public byte[] selecionaEsqueleto(Message request)  {

		Class<?> objRef = null;
		Method method = null;
		byte[] resposta = null;
		
		try {
			
			objRef = Class.forName("servidor." + request.getObjReference() + "Esqueleto");

			String methodName = request.getMethodId();
			System.out.println("\nExecutando: " + methodName);

			System.out.println("metodo: " + methodName);
			System.out.println("classe: " + request.getObjReference() + "\n\n");

			
			method = objRef.getMethod(methodName, byte[].class);
			resposta = (byte[]) (method.invoke(objRef.newInstance(), request.getArguments()));	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return resposta;
	}
	
	
	
}
