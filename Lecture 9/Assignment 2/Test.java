import java.lang.reflect.Field; 

public class Test { 
	String s; 
	public Test() { 
		this.s = "Programming languages"; 
	} 
	
	public void method1() { 
		System.out.println("The value of the string is " + s); 
	} 
	
	public void method2(int n) { 
		System.out.println("The number is " + n); 
	} 
	
	private void method3() { 
		System.out.println("Private method invoked"); 
	} 
}
