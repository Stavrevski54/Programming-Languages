import java.lang.reflect.Constructor; 
import java.lang.reflect.Method;

public class Main { 
	public static void main(String[] args) { 
		Test test = new Test(); 
		Class<?> cls = test.getClass(); 
		System.out.println("The name of class is " + cls.getName()); 
		Constructor<?> constructor = null; 
		try { 
			constructor = cls.getConstructor(); 
			System.out.println("The name of constructor is " + constructor.getName()); 
		} 
		catch (NoSuchMethodException e) { 
			e.printStackTrace(); 
		} 
		
		System.out.println("The public methods of class are"); 
		Method[] methods = cls.getMethods(); 
		for (Method method : methods) { 
			System.out.println("Method name: " + method.getName()); 
		}
	}
}
