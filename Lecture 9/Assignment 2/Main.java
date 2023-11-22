import java.lang.reflect.Constructor; 
import java.lang.reflect.Field; 
import java.lang.reflect.Method; 

public class Main { 
	public static void main(String[] args) 
throws Exception { 
		Test test = new Test(); 
		
		Method methodCall1 = test.getClass().getDeclaredMethod("method1"); 
		methodCall1.invoke(test); 
		
		Method methodCall2 = test.getClass().getDeclaredMethod("method2", int.class); 
		methodCall2.invoke(test, 15); 
		
		Field field = test.getClass().getDeclaredField("s"); 
		field.setAccessible(true); 
		System.out.println("Printing the value of s: " + field.get(test)); 
		
		System.out.println("Print the changed value of s"); 
		field.set(test, "JAVA"); 
		methodCall1.invoke(test); 
		
		Method methodCall3 = test.getClass().getDeclaredMethod("method3");
		methodCall3.setAccessible(true); 
		System.out.println("Printing the result of method3:"); 
		methodCall3.invoke(test); } }
	}
}

